package com.khalil.rain;

import java.util.ArrayList;

public class tupele implements Comparable<tupele>{
    private String state;

    @Override
    public int compareTo(tupele tupele) {
        if(this.cost>tupele.cost)return 1;
        else if(this.cost<tupele.cost)return -1;
        else return 0;
    }

    private int id;
    private   Direction d;
    private ArrayList<Block> blocks;
    private int cost;

    public tupele(String state, int id, Direction d,ArrayList<Block> blocks,int cost) {
        this.state = state;
        this.id = id;
        this.d = d;
        this.blocks=blocks;
        this.cost=cost;
    }

    public ArrayList<Block> getBlocks() {
        return this.blocks;
    }

    public String getState() {
        return state;
    }


    public int getCost(){
        return cost;
    }

    public int getId() {
        return id;
    }


    public Direction getD() {
        return d;
    }

}
