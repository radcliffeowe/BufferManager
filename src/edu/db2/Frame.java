package edu.db2;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Frame {

    private final int FRAME_SIZE = 4000;

    private byte[] content;
    private boolean dirty;
    private boolean pinned;
    private int blockId;

    public Frame(boolean dirty, boolean pinned, int blockId){
        this.content = new byte[4000];
        this.dirty = dirty;
        this.pinned = pinned;
        this.blockId = blockId;
    }

    public byte[] getRecord(int recordNumber){
        byte[] record = new byte[40];
        if(recordNumber == 0){
            record = Arrays.copyOfRange(content, 99*40, 99*40+40);
        }
        else {
            record = Arrays.copyOfRange(content, (recordNumber - 1) * 40, (recordNumber - 1) * 40 + 40);
        }
        return record;
    }

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
