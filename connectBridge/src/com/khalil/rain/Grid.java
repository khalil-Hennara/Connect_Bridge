package com.khalil.rain;

import java.util.ArrayList;

public class Grid {
    private int width,hight;
    private int[] types;
    private char[] display;
    public  Cell[][] grid;
    private String state;
    private ArrayList<Block> Blocks;

    public Grid(int width,int hight,int[] types){
        this.width=width;
        this.hight=hight;
        this.types=types;
        grid =new Cell[hight][width];
        state=new String();
        Blocks =new ArrayList<>();

    }

    public void inilize_grid(){
       for(int i=0;i<hight;i++){
           for(int j=0;j<width;j++){
               switch(this.types[j+i*width]){
                   case 0:grid[i][j]=new Cell(i,j,Type.Empty);state=state.concat("E");break;
                   case 1:grid[i][j]=new Cell(i,j,Type.Rock);state=state.concat("R");break;
                   case 2:grid[i][j]=new Cell(i,j,Type.GRASE);state=state.concat("G");break;
                   case 3:grid[i][j]=new Cell(i,j,Type.Brige);state=state.concat("B");break;
               }
           }
       }
    }

    public void makeBlock(){
        for(int j=0;j<width;j++){
            for(int i=0;i<hight;i++){
                if(grid[i][j].getType()==Type.Brige){
                    Block temp=new Block();
                    int counter=0;
                    while (i<hight && counter<4 &&grid[i][j].getType()==Type.Brige ){
                        temp.addToBlock(new pair(i,j));
                        counter++;
                        i++;
                    }
                   if( i!=hight && (grid[i][j].getType()==Type.Brige ||grid[i][j].getType()==Type.GRASE))i--;
                 Blocks.add(temp);
                }else if(grid[i][j].getType()==Type.GRASE){
                    Block temp=new Block();
                    int counter=0;
                    while (i<hight &&  counter<2 && grid[i][j].getType()==Type.GRASE ){
                        temp.addToBlock(new pair(i,j));
                        counter++;
                        i++;
                    }
                    if(i!=hight && (grid[i][j].getType()==Type.Brige ||grid[i][j].getType()==Type.GRASE))i--;
                    Blocks.add(temp);
                }
            }
        }
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHight() {
        return this.hight;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    private void updateDisplay(){
       display=new char[width*hight];
       state=new String();
        for(int i=0;i<hight;i++){
            for(int j=0;j<width;j++){
                switch(grid[i][j].getType()){
                    case Empty:display[j+i*width]='E';state=state.concat("E");break;
                    case Rock:display[j+i*width]='R';state=state.concat("R");break;
                    case GRASE:display[j+i*width]='G';state=state.concat("G");break;
                    case Brige:display[j+i*width]='B';state=state.concat("B");break;

                }
            }
        }

    }

    public char[] getDisplay(){
        updateDisplay();
        return this.display;
    }

    public String getState(){return this.state;}

    public ArrayList<Block> getBlocks(){
        return this.Blocks;
    }

    public Block getBlockWithID(int id){
        for(Block v:Blocks){
            if(v.getID()==id)return v;
        }
        return null;
    }

    public Grid(Grid c){
        this.grid=c.grid;
    }

    public Cell getCell(int i,int j){
        return grid[i][j];
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.Blocks = blocks;
    }
    public void uppdateBlocks(int id,Direction d){
        Block tmp=Blocks.get(id);
        switch (d){
            case UP:
                for(pair c:tmp.getCellindex()){
                    c.first--;
                }
                break;
            case DOWN:
                for(pair c:tmp.getCellindex()){
                    c.first++;
                }
                break;
            case LEFT:
                for(pair c:tmp.getCellindex()){
                    c.second--;
                }
                break;
            case RIGHT:
                for(pair c:tmp.getCellindex()){
                    c.second++;
                }
                break;
        }
    }
}
