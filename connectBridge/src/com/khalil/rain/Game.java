package com.khalil.rain;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
    public Grid bord;
    private State state;
    static int gridWidth,gridHight;
    private ArrayList<Block> blocks;

    public Game(Grid bord) {
        this.bord = bord;
        state = new State();
        gridWidth = bord.getWidth();
        gridHight = bord.getHight();
        String temp=new String();
        temp=temp.concat(bord.getState());
        state.addToGraph(temp);
        blocks =bord.getBlocks();
    }

    private boolean CanMove(int i, int j, Direction d){//throws cantMoveException, canMoveException {
        Cell cell=null;
        try {
             cell = bord.grid[i][j];
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        if (cell.getcanMove()) {
            switch (d) {
                case UP: i--;break;
                case DOWN: i++;break;
                case LEFT: j--;break;
                case RIGHT: j++;break;
            }
            if (checkbounds(i, j) && bord.grid[i][j].getType() == Type.Empty) return true;
            return false;
        }

        return false;
    }

    private void canMoveBlock(int i,int j,Direction d) throws cantMoveException,canMoveException{
        Block tmp=getBlock(i,j);
        if(d==Direction.RIGHT ||d==Direction.LEFT){
            for (pair c: tmp.getCellindex()){
                if(!CanMove(c.first,c.second,d))throw new cantMoveException();
            }
            throw new canMoveException();
        }
        else if(d==Direction.UP){
           pair t=tmp.getCellindex().get(0);
           if(CanMove(t.first,t.second,d))throw new canMoveException();
           throw new cantMoveException();
        }
        else if(d==Direction.DOWN){
            pair t=tmp.getCellindex().get(tmp.getPiceNumber()-1);
            if(CanMove(t.first,t.second,d))throw new canMoveException();
            throw new cantMoveException();

        }
    }

    public boolean canMoveBlockbyD(int id,Direction d){
        Block tmp=bord.getBlockWithID(id);
        pair s=tmp.getCellindex().get(0);
        try{ canMoveBlock(s.first,s.second,d);}
        catch (canMoveException e){
            return true;
        }catch (cantMoveException e){
            return false;
        }
        return true;
    }

    private Block getBlock(int i,int j){
        pair s=new pair(i,j);

        for(Block v:blocks){
            if(v.getIDForIndex(s)!=-1)return v;
        }
        return null;
    }

    public  boolean checkbounds(int x, int y) {
        if (x < 0 || x >= bord.getHight() || y < 0 || y >= bord.getWidth()) return false;
        return true;
    }

    public static boolean checkboundsOfGrid(int x, int y) {
        if (x < 0 || x >= gridHight || y < 0 || y >= gridWidth) return false;
        return true;
    }

    public void makeMove(int i, int j, Direction d) throws winTheGame {
        try {
            canMoveBlock(i, j, d);
        } catch (cantMoveException e) {
            System.out.println(e.getMessage());
            System.out.println();
            DisplayGridOnConsole.Display(bord.getDisplay(), bord.getWidth(), bord.getHight());
        } catch (canMoveException e) {
            bord = update(i, j, d,true);
            String temp=new String();
            temp=temp.concat(bord.getState());
            state.addToGraph(temp);
            if(checkWin())throw new winTheGame();

        }
    }

    public String makMoveByDirct(int id,Direction d){
        Cell[][] temp=copyGrid();
        updateGrid(temp,id,d);
        String t=convertGridToString(temp);
        return t;
    }

    public String convertGridToString(Cell[][] a){
        String s=new String();
        for(int i=0;i<gridHight;i++){
            for(int j=0;j<gridWidth;j++){
                s=s.concat(a[i][j].getChar());
            }
        }

        return s;
    }

    public void updateGrid(Cell[][] ngrid,int id,Direction d){
        Block tmp=bord.getBlockWithID(id);
        int nx=0,ny=0;
        if(d==Direction.DOWN){
            pair s=tmp.getCellindex().get(0);
            nx=s.first + tmp.getPiceNumber();
            ny=s.second;
            swap(ngrid,s.first,s.second,nx,ny);

        }else if(d==Direction.UP){
            pair s = tmp.getCellindex().get(tmp.getPiceNumber()-1);
            nx=s.first-tmp.getPiceNumber();
            ny=s.second;
            swap(ngrid,s.first,s.second,nx,ny);
        }else if(d==Direction.LEFT){
            for(pair s:tmp.getCellindex()){
                int ni=s.first,nj=s.second-1;
                swap(ngrid,s.first,s.second,ni,nj);
            }
        }
        else {
            for(pair s:tmp.getCellindex()){
                int ni=s.first,nj=s.second+1;
                swap(ngrid,s.first,s.second,ni,nj);
            }
        }

    }

    private Cell[][] copyGrid(){
        Cell[][] temp=new Cell[bord.getHight()][bord.getWidth()];
        for(int i=0;i<gridHight;i++){
            for(int j=0;j<gridWidth;j++){
                temp[i][j]=new Cell(bord.grid[i][j].getType());
            }
        }
        return temp;
    }

    public Grid getBord() {
        return this.bord;
    }

    private Grid update(int i, int j, Direction d,boolean activ) {
        Cell[][] ngrid = bord.getGrid();
        Block tmp=getBlock(i,j);
        int nx=0,ny=0;
        if(d==Direction.DOWN){
            pair s=tmp.getCellindex().get(0);
            nx=s.first + tmp.getPiceNumber();
            ny=s.second;
            swap(ngrid,s.first,s.second,nx,ny);
            for(pair c : tmp.getCellindex()){
                c.first++;
            }
        }else if(d==Direction.UP){
            pair s = tmp.getCellindex().get(tmp.getPiceNumber()-1);
            nx=s.first-tmp.getPiceNumber();
            ny=s.second;
            swap(ngrid,s.first,s.second,nx,ny);
            for(pair c:tmp.getCellindex()){
                c.first--;
            }
        }else if(d==Direction.LEFT){
            for(pair s:tmp.getCellindex()){
                int ni=s.first,nj=s.second-1;
                swap(ngrid,s.first,s.second,ni,nj);
                s.first=ni;
                s.second=nj;
            }
        }
        else {
            for(pair s:tmp.getCellindex()){
                int ni=s.first,nj=s.second+1;
                swap(ngrid,s.first,s.second,ni,nj);
                s.first=ni;
                s.second=nj;
            }
        }

        bord.setGrid(ngrid);
        if(activ) { DisplayGridOnConsole.Display(bord.getDisplay(), bord.getWidth(), bord.getHight()); }
        return bord;
    }

    private void swap(Cell[][] arr, int i, int j, int ni, int nj) {
        Cell temp = arr[i][j];
        arr[i][j] = arr[ni][nj];
        arr[ni][nj] = temp;
    }

    public boolean checkWin() {
        int[] xdir = new int[4];
        int[] ydir = new int[4];
        xdir[0] = 0;xdir[1] = 0;xdir[2] = 1;xdir[3] = -1;
        ydir[0] = 1;ydir[1] = -1;ydir[2] = 0;ydir[3] = 0;
        int m = bord.getWidth();
        int n = bord.getHight();
        if(DFS(n,m,xdir,ydir))return true;
        else return false;
    }

    //this BFS is The Function That Check if I Have Path From Up To Down OR From Right To Left
    public boolean DFS(int n, int m, int[] xdir, int[] ydir) {
        Stack<pair> S = new Stack<>();
        boolean[][] visited = new boolean[n][m];
        for (int i=0;i<n;i++)for(int j=0;j<m;j++)visited[i][j]=false;

            for (int i = 0; i < m; i++) {
                if (bord.grid[0][i].getType() == Type.Brige) {
                    visited[0][i] = true;
                    S.push(new pair(0, i));
                }
                pair p;
                while (!S.empty()) {
                    p = S.peek();
                    S.pop();
                    if (p.first == n - 1) return true;
                    for (int k = 0; k < 4; k++) {
                        int ni = p.first + xdir[k];
                        int nj = p.second + ydir[k];
                        if (checkbounds(ni, nj) && !visited[ni][nj] && bord.grid[ni][nj].getType()==Type.Brige) {
                            visited[ni][nj] = true;
                            S.push(new pair(ni, nj));
                        }
                    }
                }
            }

            return false;

    }

    public State getState() {
        return state;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks=blocks;
        this.bord.setBlocks(blocks);
    }

    public void uppdateBlocks(int id, Direction d){
        Block tmp=blocks.get(id);
        switch (d){
            case UP:
                for(pair c:tmp.getCellindex()){ c.first=c.first-1; } break;
            case DOWN:
                for(pair c:tmp.getCellindex()){ c.first=c.first+1; }break;
            case LEFT:
                for(pair c:tmp.getCellindex()){ c.second=c.second-1; }break;
            case RIGHT:
                for(pair c:tmp.getCellindex()){ c.second=c.second+1; }break;
        }

        bord.setBlocks(blocks);
    }

    public void updateGrid(Cell[][] gr ){
        this.bord.setGrid(gr);
    }


}
