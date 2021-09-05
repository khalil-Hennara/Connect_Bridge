package com.khalil.rain;

import java.awt.*;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class CounstructBord {
    private Grid grid;

    public CounstructBord() {
        Scanner input = new Scanner(new BufferedInputStream(System.in));
        int width = input.nextInt();
        int hight = input.nextInt();
        int[] types = new int[hight * width];
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                types[j + i * width] = input.nextInt();
            }
        }
        grid = new Grid(width, hight, types);
        grid.inilize_grid();
        grid.makeBlock();
    }

    public Grid getGrid() {
        return this.grid;
    }
}

