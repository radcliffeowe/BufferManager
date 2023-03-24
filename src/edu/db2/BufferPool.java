package edu.db2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class BufferPool {

    private Frame[] buffers;
    private int lastEvictedFrameNumber;

    public BufferPool(int poolSize){
        this.buffers = new Frame[poolSize];
        this.initialize(poolSize);
        this.lastEvictedFrameNumber = -1;
    }

    /**
     * Initialize the buffer pool at startup, set the frames to empty
     * @param size is the size of the buffer pool in number of Frames
     */
    public void initialize(int size){
        for(int i = 0; i< size; i++){
            buffers[i] = new Frame(false, false, -1);
        }
    }

    /**
     * Method to get a record from the file. Calculates which block contains the file, then checks if that block is already in memory.
     * If it is, the record is read from memory. If it is not, and there is an empty frame in the buffer, or a frame can be evicted,
     * then the record is read from the disk into the buffer.
     * If the block must be read from the disk and there are no empty frame or evictable frames, then an error message is printed on screen.
     * @param recordNumber is the number of the record the method is reading.
     */
    public void GET(int recordNumber){
        Double blockFloor = Math.floor((recordNumber-1)/100);
        int blockId = blockFloor.intValue() + 1;
        int recordId = recordNumber%100;
        String wasInMemory = "In memory, no I/O necessary";
        int bufferNumber = isInPool(blockId);
        if(bufferNumber == -1){
            bufferNumber = fetchBlock(blockId);
            wasInMemory = "Not in memory, I/O necessary";
        }
        if(bufferNumber == -1){
            System.out.println("The corresponding block# " + blockId + " cannot be accessed from disk because the memory buffers are full\n");
            return;
        }
        else {
            System.out.println(new String(buffers[bufferNumber].getRecord(recordId), StandardCharsets.US_ASCII));
            System.out.println(wasInMemory);
            System.out.println("Block stored in frame number " + (bufferNumber+1));
        }
    }

    public void PIN(int blockId){
        int alreadyInMemory = isInPool(blockId);
        String alreadyPinned = "";
        if(alreadyInMemory == -1){
            int bufferNumber = fetchBlock(blockId);
            if(bufferNumber >= 0){
                buffers[bufferNumber].setPinned(true);
                alreadyInMemory = bufferNumber;
                alreadyPinned = "not ";
            }
            else{
                System.out.println("The corresponding block " + blockId + " cannot be pinned because the memory buffers are full\n");
                return;
            }
        }
        else{
            if(!buffers[alreadyInMemory].getPinned()){
                buffers[alreadyInMemory].setPinned(true);
                alreadyPinned = "not ";
            }
        }
        System.out.println("The block is pinned in frame " + (alreadyInMemory+1));
        System.out.println("The block was "+ alreadyPinned + "already pinned\n");
    }

    public void UNPIN(int blockId){
        String alreadyUnpinned = "";
        int alreadyInMemory = isInPool(blockId);
        if(alreadyInMemory != -1){
            if(buffers[alreadyInMemory].getPinned()){
                buffers[alreadyInMemory].setPinned(false);
                alreadyUnpinned = "not";
            }
        }
        else{
            System.out.println("The corresponding block " + blockId + " cannot be unpinned because it is not in memory\n");
            return;
        }
        System.out.println("Frame number " + (alreadyInMemory+1) + " unpinned");
        System.out.println("Pinned flag was " + alreadyUnpinned + " already false");
    }

    /**
     * Search the buffer pool for the block containing the record.
     * @param blockId is the block containing the record
     * @return the buffer number holding the block if the block is in the pool. Else return -1.
     */
    public int isInPool(int blockId){
        for(int i = 0; i < buffers.length; i++){
            if(buffers[i].getBlockId() == blockId){
                return i;
            }
        }
        return -1;
    }

    public byte[] returnBlockContent(int blockId){
        int bufferNumber = isInPool(blockId);
        byte[] blockContent = buffers[bufferNumber].getContent();
        return blockContent;
    }

    /**
     * Fetches block from disk, and stores it in empty or evictable frame in buffer if available.
     * @param blockId is the id of the block being fetched
     * @return the buffer number the block is stored in if the block could be brought from the disk into an empty of evictable frame.
     * If there were no evictable frames, the method returns -1.
     */
    public int fetchBlock(int blockId){
        //block is not in memory, must be read from disk
        byte[] blockContent = new byte[4000];
        File block = new File("Project1/F" + blockId +".txt");
        int inFrame = -1;

        try(FileInputStream stream = new FileInputStream(block)){
            stream.read(blockContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        inFrame = findEmptyFrame();
        if(inFrame == -1){
            inFrame = evictFrame();
        }
        if(inFrame != -1){
            buffers[inFrame].setContent(blockContent);
            buffers[inFrame].setBlockId(blockId);
        }
        return inFrame;
    }

    /**
     * Scans buffer pool for empty frame
     * @return the buffer number of the empty frame if found. If no empty frame is found, returns -1.
     */
    public int findEmptyFrame(){
        for(int i = 0; i< buffers.length; i++){
            if(buffers[i].getBlockId() == -1){
                return i;
            }
        }
        return -1;
    }

    /**
     * Evicts frame from buffer pool. Writes frame back to disk if dirty flag is true.
     * @return the buffer number of the evictable frame. If no frames are evictable, returns -1.
     */
    public int evictFrame(){
        int frameNum = (lastEvictedFrameNumber + 1)% buffers.length;
        int startingFrame = frameNum;
        do{
            if(!buffers[frameNum].getPinned()){
                if(buffers[frameNum].getDirty()){
                    //write back to disk
                    File blockOnDisk = new File("Project1/F" + buffers[frameNum].getBlockId() + ".txt");
                    try(FileOutputStream stream = new FileOutputStream(blockOnDisk)){
                        stream.write(buffers[frameNum].getContent());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.exit(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
                lastEvictedFrameNumber = frameNum;
                return frameNum;
            }
            if(lastEvictedFrameNumber == -1){
                lastEvictedFrameNumber = 0;
            }
            frameNum = (frameNum + 1)% buffers.length;
        } while(frameNum != startingFrame);
        return -1;
    }

    public Frame[] getBuffers() {
        return buffers;
    }

    public void setBuffers(Frame[] buffers) {
        this.buffers = buffers;
    }
}
