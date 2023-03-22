package edu.db2;

public class BufferPool {

    private Frame[] buffers;

    public BufferPool(int poolSize){
        this.buffers = new Frame[poolSize];
    }

    public void initialize(int size){

    }

    public int isInPool(int blockId){
        return -1; //return buffer number
    }

    public byte[] returnBlockContent(int blockId){
        return new byte[4000];
    }

    public int fetchBlock(int blockId){
        return -1;
    }

    public int findEmptyFrame(){
        return -1;
    }

    public int evictFrame(){
        return -1;
    }

    public Frame[] getBuffers() {
        return buffers;
    }

    public void setBuffers(Frame[] buffers) {
        this.buffers = buffers;
    }
}
