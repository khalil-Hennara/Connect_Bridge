package com.khalil.rain;

import java.io.BufferedInputStream;
import java.io.SequenceInputStream;
import java.util.*;

public class Main {

    public static void PlayGame(Game game) {
        Scanner in = new Scanner(System.in);
        char c;
        while (true) {
            try {
                System.out.println("Please Enter E If You Wont Exit or Any Thing To Continue");
                c = in.next().charAt(0);
                if (c == 'E') throw new YouEndTheGame();
            } catch (YouEndTheGame e) {
                System.out.println(e.getMessage());
                break;
            }
            int[] index = wormTheOutput.print_The_construction();
            int z = wormTheOutput.getDirection();
            Direction d = wormTheOutput.returnDirection(z);
            try {
                game.makeMove(index[0], index[1], d);

            } catch (winTheGame e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public static void main(String[] args) {
      CounstructBord c = new CounstructBord();
        Grid g = c.getGrid();
        Game game = new Game(g);
        SmartPlayer p1=new SmartPlayer(game);
        try {
            p1.playwithDFS();
        }catch (winTheGame e){
            System.out.println(Color.PURPLE_BOLD_BRIGHT+e.getMessage());
            System.out.println("Bord Generater "+p1.getCounter());
            System.out.print("Real Move Counter"+p1.getRealMoveCounter());

        }catch (ThisGameHaveNoSolve e){
            System.out.println(Color.PURPLE_BOLD_BRIGHT+e.getMessage());
            System.out.print(p1.getCounter());
        }
    }
}


