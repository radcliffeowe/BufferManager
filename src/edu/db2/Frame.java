package edu.db2;

public class Frame {

    private final int FRAME_SIZE = 4000;

    private byte[] content;
    private boolean dirty;
    private boolean pinned;
    private int blockId;

    public Frame(boolean dirty, boolean pinned, int blockId){
        this.content = new byte[FRAME_SIZE];
        this.dirty = dirty;
        this.pinned = pinned;
        this.blockId = blockId;
    }

    public byte[] getRecord(int recordNumber){
        return new byte[40];
    }

    public boolean updateRecord(int recordNumber, byte[] newContent){
        this.dirty = true;
        return false;
    }

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
