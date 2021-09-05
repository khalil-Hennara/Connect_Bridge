package com.khalil.rain;

import java.util.ArrayList;
import java.util.Set;

public class RealBordState {
   private ArrayList<Cell[][]> graph;
   private State state;
    public RealBordState(State s){
        graph=new ArrayList<>();
        this.state=s;
    }
    public static Cell[][] convertStringToCell(String t){
        int width=Game.gridWidth;
        int higth=Game.gridHight;
        Cell [][] temp=new Cell[higth][width];
        for(int i=0;i<higth;i++) {
            for (int j = 0; j < width; j++) {
                Type type=RealBordState.covertchar(t.charAt(j+(i*width)));
                temp[i][j]=new Cell(type);
            }
        }
    return temp;
    }
    private static  Type covertchar(char c){
        Type t=Type.Brige;
        switch (c){
            case 'B':return Type.Brige;
            case 'R':return Type.Rock;
            case 'G':return Type.GRASE;
            case 'E':return Type.Empty;
        }
    return t;
    }
    private void ConvertAllGraph(){
        Set<String>gr=state.getGraph();
        for(String s:gr){
            Cell[][] tf=convertStringToCell(s);
            graph.add(tf);
        }
    }
    public ArrayList<Cell[][]> getGraph(){
        ConvertAllGraph();
        return this.graph;
    }

}
