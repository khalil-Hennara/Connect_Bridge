package com.khalil.rain;

import java.util.ArrayList;
import java.util.Objects;

public class Block {
    static int counter=0;
    private int ID=counter;
    private int piceNumber;
    private ArrayList<pair> cellindex;

    public Block(){
        counter++;
        cellindex =new ArrayList<>();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public int getPiceNumber() {
        return getCellindex().size();
    }

    public ArrayList<pair> getCellindex() {
        return cellindex;
    }

    public void addToBlock(pair s){
        this.cellindex.add(s);
    }

    public int getIDForIndex(pair i){
        if(this.cellindex.contains(i))return this.ID;
        return -1;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return ID == block.ID &&
                piceNumber == block.piceNumber &&
                Objects.equals(cellindex, block.cellindex);
    }
}
