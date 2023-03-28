package edu.db2;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Frame {

    private byte[] content;
    private boolean dirty;
    private boolean pinned;
    private int blockId;

    public Frame(){
        this.intialize();
    }

    /**
     * Method to initialize the Frame at startup, sets content to a 4000 byte array, the dirty and pinned flags to false, and the blockId to -1, so the program knows the Frame is empty.
     */
    public void intialize(){
        this.content = new byte[4000];
        this.dirty = false;
        this.pinned = false;
        this.blockId = -1;
    }

    /**
     * Method to get a record from a Frame. Uses the 40-byte size of each record to fetch the record using its index.
     * @param recordNumber is the number of the record being fetched.
     * @return a 40 byte array of the record associated with the recordNumber.
     */
    public byte[] getRecord(int recordNumber){
        byte[] record;
        if(recordNumber == 0){
            record = Arrays.copyOfRange(content, 99*40, 99*40+40);
        }
        else {
            record = Arrays.copyOfRange(content, (recordNumber - 1) * 40, (recordNumber - 1) * 40 + 40);
        }
        return record;
    }

    /**
     * Method to update a record in the Frame. Checks if the new String is equal to the old String.
     * If it is, no changes have been made, so the Frame does not need to be marked dirty.
     * If the content has been modified, sets the dirty flag to true, then writes the new content to the Frame in the appropriate index.
     * @param recordNumber is the record number being updated.
     * @param newContent is the 40-byte String the record is being updated to.
     * @return true if the record is modified, false if the original record is equal to the new record, so no modification made.
     */
    public boolean updateRecord(int recordNumber, String newContent){
        String currentRecord = new String(getRecord(recordNumber), StandardCharsets.US_ASCII);
        if(!currentRecord.equals(newContent)) {
            this.dirty = true;
            byte[] newContentBytes = newContent.getBytes();
            if(recordNumber == 0){
                for(int i = 99*40; i<99*40+40; i++){
                    content[i] = newContentBytes[i%40];
                }
            }
            else {
                for(int i = (recordNumber -1)*40; i<(recordNumber-1)*40+40; i++){
                    content[i] = newContentBytes[i%40];
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    /*
        Getters and setters for the attributes of Frame
     */
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public boolean getDirty() {
        return dirty;
    }

    public void setDirty(Boolean dirty) {
        this.dirty = dirty;
    }

    public boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
