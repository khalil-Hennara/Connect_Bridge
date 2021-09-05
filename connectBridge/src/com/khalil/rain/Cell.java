package com.khalil.rain;

public class Cell {
    private int width, hight;//default  fixed width and length will take the dimantion from cell dimantions
    private Type type;//the type of cell
    private color col;//the color my use if we make grafic user interface
    private boolean canMove;
    int indexi, indexj;

    public Cell(Type type) {
        this.type = type;
        switch (type) {
            case Rock:
                this.col = color.gray;
                this.canMove = false;
                break;
            case Brige:
                this.col = color.broun;
                this.canMove = true;
                break;
            case Empty:
                this.col = color.blue;
                this.canMove = false;
                break;
            case GRASE:
                this.col = color.green;
                this.canMove = true;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return width == cell.width &&
                hight == cell.hight &&
                canMove == cell.canMove &&
                indexi == cell.indexi &&
                indexj == cell.indexj &&
                type == cell.type &&
                col == cell.col;
    }


    public void setIndexi(int indexi) {
        this.indexi = indexi;
    }

    public void setIndexj(int indexj) {
        this.indexj = indexj;
    }

    public int getIndexi() {
        return this.indexi;
    }

    public int getIndexj() {
        return this.indexj;
    }

    public Cell(int indexi, int indexj, Type type) {
        this(type);
        this.indexi = indexi;
        this.indexj = indexj;

    }

    public String getChar() {
        String c = new String();
        switch (this.getType()) {
            case Brige:
                c = "B";
                break;
            case Rock:
                c = "R";
                break;
            case GRASE:
                c = "G";
                break;
            case Empty:
                c = "E";
                break;
        }
        return c;
    }

    public boolean getcanMove() {
        return this.canMove;
    }


    public Type getType() {
        return this.type;
    }
}
