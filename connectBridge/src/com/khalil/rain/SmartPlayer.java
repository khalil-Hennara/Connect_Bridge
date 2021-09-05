package com.khalil.rain;

import java.util.*;

public class SmartPlayer {

    private Game game;
    private ArrayList<Block> blocks;
    private int blockNumber;
    private State states;
    private Set<String> graph;
    private int counter;
    private int RealMoveCounter;

    public int getRealMoveCounter() {
        return RealMoveCounter;
    }

    public SmartPlayer(Game g) {
        this.game = g;
        blocks = g.getBlocks();
        blockNumber = blocks.size();
        this.states = g.getState();
        this.graph = states.getGraph();
    }

    public String getFirststate() {
        String temp = new String("-1");
        for (String c : graph) {
            return c;
        }
        return temp;
    }

    public void playwithDFS() throws winTheGame, ThisGameHaveNoSolve {
        Stack<tupele> st = new Stack<>();
        counter = 0;
        RealMoveCounter=0;
        st.push(new tupele(getFirststate(), -1, Direction.UP, blocks,0));
        while (!st.empty()) {
            tupele t = st.peek();
            st.pop();
            String a = t.getState();
            int i = t.getId();
            Direction D = t.getD();
            ArrayList<Block> blo = t.getBlocks();
            Cell[][] tm = RealBordState.convertStringToCell(a);
            if (i != -1) {
                game.updateGrid(tm);
                game.setBlocks(blo);
                game.uppdateBlocks(i, D);
                blocks = game.bord.getBlocks();
               // DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
            }
            RealMoveCounter++;
            for (int id = 0; id < blockNumber; id++) {
                for (int k = 0; k < 4; k++) {

                    Direction d = wormTheOutput.returnDirection(k);
                    if (game.canMoveBlockbyD(id, d)) {
                        String tmp = game.makMoveByDirct(id, d);
                        if (graph.contains(tmp)) continue;
                        graph.add(tmp);
                        Cell[][] bord = RealBordState.convertStringToCell(tmp);
                        if (DFs(bord, Game.gridWidth, Game.gridHight)) {
                            game.bord.setGrid(bord);
                            DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
                            throw new winTheGame();
                        }
                        ArrayList<Block> R = copyBlock(blocks);
                        st.push(new tupele(tmp, id, d, R,0));
                        counter++;
                    }

                }
            }
        }

        throw new ThisGameHaveNoSolve();
    }

    public void playwithBFS() throws winTheGame, ThisGameHaveNoSolve {
        Queue<tupele> st = new LinkedList<>();
        counter = 0;
        RealMoveCounter=0;
        ((LinkedList<tupele>) st).addLast(new tupele(getFirststate(), -1, Direction.UP, blocks,0));
        while (!st.isEmpty()) {
            tupele t = st.peek();
            ((LinkedList<tupele>) st).pop();
            String a = t.getState();
            int i = t.getId();
            Direction D = t.getD();
            ArrayList<Block> blo = t.getBlocks();
            Cell[][] tm = RealBordState.convertStringToCell(a);
            if (i != -1) {
                game.updateGrid(tm);
                game.setBlocks(blo);
                game.uppdateBlocks(i, D);
                blocks = game.bord.getBlocks();
      //          DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
            }
            RealMoveCounter++;
            for (int id = 0; id < blockNumber; id++) {
                for (int k = 0; k < 4; k++) {

                    Direction d = wormTheOutput.returnDirection(k);
                    if (game.canMoveBlockbyD(id, d)) {
                        String tmp = game.makMoveByDirct(id, d);
                        if (graph.contains(tmp)) continue;
                        graph.add(tmp);
                        Cell[][] bord = RealBordState.convertStringToCell(tmp);
                        if (DFs(bord, Game.gridWidth, Game.gridHight)) {
                            game.bord.setGrid(bord);
                            DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
                            throw new winTheGame();
                        }
                        ArrayList<Block> R = copyBlock(blocks);
                        ((LinkedList<tupele>) st).addLast(new tupele(tmp, id, d, R,0));
                        counter++;
                    }

                }
            }
        }

        throw new ThisGameHaveNoSolve();
    }

    public int getCounter() {
        return this.counter;
    }

    private ArrayList<Block> copyBlock(ArrayList<Block> e) {
        ArrayList<Block> t = new ArrayList<>();
        for (Block c : e) {
            Block v = new Block();
            v.setID(c.getID());
            for (pair u : c.getCellindex()) {
                pair r = new pair(u.first, u.second);
                v.addToBlock(r);
            }
            t.add(v);
        }
        return t;
    }

    private boolean DFs(Cell[][] a, int width, int hight) {
        Stack<pair> S = new Stack<>();
        boolean[][] visited = new boolean[hight][width];
        for (int i = 0; i < hight; i++) for (int j = 0; j < width; j++) visited[i][j] = false;
        int xdir[] = {0, 0, 1, -1};
        int ydir[] = {1, -1, 0, 0};
        for (int i = 0; i < width; i++) {
            if (a[0][i].getType() == Type.Brige) {
                visited[0][i] = true;
                S.push(new pair(0, i));
            }
            pair p;
            while (!S.empty()) {
                p = S.peek();
                S.pop();
                if (p.first == hight - 1) return true;
                for (int k = 0; k < 4; k++) {
                    int ni = p.first + xdir[k];
                    int nj = p.second + ydir[k];
                    if (Game.checkboundsOfGrid(ni, nj) && !visited[ni][nj] && a[ni][nj].getType() == Type.Brige) {
                        visited[ni][nj] = true;
                        S.push(new pair(ni, nj));
                    }
                }
            }
        }

        return false;

    }

    public void playwithDijkstra() throws winTheGame, ThisGameHaveNoSolve {
        PriorityQueue<tupele> st=new PriorityQueue<tupele>(tupele::compareTo);
        // PriorityQueue<pair> q = pq(blocks);
        counter=0;
        RealMoveCounter=0;
        st.add(new tupele(getFirststate(), -1, Direction.UP, blocks,0));
        while (!st.isEmpty()) {
            tupele t = st.poll();
            String a = t.getState();
            int i = t.getId();
            Direction D = t.getD();
            ArrayList<Block> blo = t.getBlocks();
            Cell[][] tm = RealBordState.convertStringToCell(a);
            if (i != -1) {
                game.updateGrid(tm);
                game.setBlocks(blo);
                game.uppdateBlocks(i, D);
                blocks = game.bord.getBlocks();
                // DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
            }
            RealMoveCounter++;
            for(int id=0;id<blockNumber;id++){
                for (int k = 0; k < 4; k++) {
                    Direction d = wormTheOutput.returnDirection(k);
                    if (game.canMoveBlockbyD(id, d)) {
                        String tmp = game.makMoveByDirct(id, d);
                        if (graph.contains(tmp)) continue;
                        graph.add(tmp);
                        counter++;
                        Cell[][] bord=RealBordState.convertStringToCell(tmp);
                        //int cost=computCost(bord,Game.gridWidth,Game.gridHight);
                        if(DFs(bord,Game.gridWidth,Game.gridHight)){
                            game.bord.setGrid(bord);
                            DisplayGridOnConsole.Display(game.bord.getDisplay(),Game.gridWidth,Game.gridHight);
                            throw new  winTheGame();
                        }

                        int cost=5-blocks.get(id).getPiceNumber();
                        ArrayList<Block> R = copyBlock(blocks);
                        st.add(new tupele(tmp, id, d, R,cost));
                    }

                }
            }
        }

        throw new ThisGameHaveNoSolve();
    }

    public void playwithAStar() throws winTheGame, ThisGameHaveNoSolve {
        PriorityQueue<tupele> st=new PriorityQueue<tupele>(tupele::compareTo);
       // PriorityQueue<pair> q = pq(blocks);
        counter=0;
        RealMoveCounter=0;
        st.add(new tupele(getFirststate(), -1, Direction.UP, blocks,0));
        while (!st.isEmpty()) {
            tupele t = st.poll();
            String a = t.getState();
            int i = t.getId();
            Direction D = t.getD();
            ArrayList<Block> blo = t.getBlocks();
            Cell[][] tm = RealBordState.convertStringToCell(a);
            if (i != -1) {
                game.updateGrid(tm);
                game.setBlocks(blo);
                game.uppdateBlocks(i, D);
                blocks = game.bord.getBlocks();
               // DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
            }
            RealMoveCounter++;
         for(int id=0;id<blockNumber;id++){
                for (int k = 0; k < 4; k++) {
                    Direction d = wormTheOutput.returnDirection(k);
                    if (game.canMoveBlockbyD(id, d)) {
                        String tmp = game.makMoveByDirct(id, d);
                        if (graph.contains(tmp)) continue;
                        graph.add(tmp);
                        counter++;
                        Cell[][] bord=RealBordState.convertStringToCell(tmp);
                        int cost=computCost(bord,Game.gridWidth,Game.gridHight);
                        if(DFs(bord,Game.gridWidth,Game.gridHight)){
                            game.bord.setGrid(bord);
                            DisplayGridOnConsole.Display(game.bord.getDisplay(),Game.gridWidth,Game.gridHight);
                            throw new  winTheGame();
                        }
                        cost=Game.gridHight-cost;
                        cost+=5-blocks.get(id).getPiceNumber();
                        ArrayList<Block> R = copyBlock(blocks);
                        st.add(new tupele(tmp, id, d, R,cost));
                    }

                }
            }
        }

        throw new ThisGameHaveNoSolve();
    }

    public void playwithHillClime() throws winTheGame, ThisGameHaveNoSolve {
        PriorityQueue<tupele> st=new PriorityQueue<tupele>(tupele::compareTo);
        // PriorityQueue<pair> q = pq(blocks);
        counter=0;
        RealMoveCounter=0;
        st.add(new tupele(getFirststate(), -1, Direction.UP, blocks,0));
        while (!st.isEmpty()) {
            tupele t = st.poll();
            String a = t.getState();
            int i = t.getId();
            Direction D = t.getD();
            ArrayList<Block> blo = t.getBlocks();
            Cell[][] tm = RealBordState.convertStringToCell(a);
            if (i != -1) {
                game.updateGrid(tm);
                game.setBlocks(blo);
                game.uppdateBlocks(i, D);
                blocks = game.bord.getBlocks();
                // DisplayGridOnConsole.Display(game.bord.getDisplay(), Game.gridWidth, Game.gridHight);
            }
            RealMoveCounter++;
            for(int id=0;id<blockNumber;id++){
                for (int k = 0; k < 4; k++) {
                    Direction d = wormTheOutput.returnDirection(k);
                    if (game.canMoveBlockbyD(id, d)) {
                        String tmp = game.makMoveByDirct(id, d);
                        if (graph.contains(tmp)) continue;
                        graph.add(tmp);
                        counter++;
                        Cell[][] bord=RealBordState.convertStringToCell(tmp);
                        int cost=computCost(bord,Game.gridWidth,Game.gridHight);
                        if(DFs(bord,Game.gridWidth,Game.gridHight)){
                            game.bord.setGrid(bord);
                            DisplayGridOnConsole.Display(game.bord.getDisplay(),Game.gridWidth,Game.gridHight);
                            throw new  winTheGame();
                        }
                        cost=Game.gridHight-cost;
                        ArrayList<Block> R = copyBlock(blocks);
                        st.add(new tupele(tmp, id, d, R,cost));
                    }

                }
            }
        }

        throw new ThisGameHaveNoSolve();
    }


    private PriorityQueue<pair> pq(ArrayList<Block> e){
        PriorityQueue<pair> q=new PriorityQueue<pair>(pair::compareTo);
        for (Block c:e){
            q.add(new pair(c.getPiceNumber(),c.getID()));
        }
        return q;
    }

    public int computCost(Cell[][] a,int width,int hight){
        Stack<pair> S = new Stack<>();
        int counter=0,ret=-1;
        boolean[][] visited = new boolean[hight][width];
        for (int i = 0; i < hight; i++) for (int j = 0; j < width; j++) visited[i][j] = false;
        int xdir[] = {0, 0, 1, -1};
        int ydir[] = {1, -1, 0, 0};
        for (int i = 0; i < width; i++) {
            if (a[0][i].getType() == Type.Brige) {
                visited[0][i] = true;
                S.push(new pair(0, i));
                counter=1;
            }
            pair p;
            while (!S.empty()) {
                p = S.peek();
                S.pop();
                if (p.first == hight - 1) return hight;
                for (int k = 0; k < 4; k++) {
                    int ni = p.first + xdir[k];
                    int nj = p.second + ydir[k];
                    if (Game.checkboundsOfGrid(ni, nj) && !visited[ni][nj] && a[ni][nj].getType() == Type.Brige) {
                        visited[ni][nj] = true;
                        S.push(new pair(ni, nj));
                        counter++;
                    }
                }
            }
            ret=Math.max(ret,counter);
        }

    return ret;
    }

}



