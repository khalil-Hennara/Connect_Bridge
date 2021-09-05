package com.khalil.rain;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.Vector;

public class wormTheOutput {

    public static int[] print_The_construction() {
        int[] res;
        while (true) {
            System.out.println("Please Enter The index Of Cell You Wont Move in Form (i,j)");
            res = wormTheOutput.getIndex();
            if (Game.checkboundsOfGrid(res[0], res[1])) {
                System.out.println("Please Enter The Direction Of Cell You Wont Move");
                System.out.println(" 0:Left\n 1:Right\n 2:Up\n 3:Down");
                return res;
            } else {
                System.out.println("Please Enter Valid Index");
                continue;
            }
        }

    }

    public static int[] getIndex() {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int[] res = new int[2];
        res[0] = in.nextInt();
        res[1] = in.nextInt();
        res[0]--;
        res[1]--;
        return res;
    }

    public static int getDirection() {
        int res;
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        res = in.nextInt();
        return res;
    }

    public static Direction returnDirection(int dir) {
        Direction d = Direction.DOWN;
        switch (dir) {
            case 0: d = Direction.RIGHT;break;
            case 1: d = Direction.DOWN;break;
            case 2: d = Direction.LEFT;break;
            case 3: d = Direction.UP;break;
        }
        return d;
    }

}
