package com.khalil.rain;

import java.util.Objects;

public class pair implements Comparable<pair>{


    int first;
    int second;
    public pair(){

    }
    public pair(int x,int y){
        this.first=x;
        this.second=y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof pair)) return false;
        pair pair = (pair) o;
        return first == pair.first &&
                second == pair.second;
    }



    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public  int compareTo(pair other) {
        if(this.first>other.first)return -1;
        else if(this.first<other.first)return 1;
        else return 0;
    }


    public static int compare(pair o1, pair o2) {
        if (o1.first == o2.first) {
            return 0;
        } else if (o1.first>o2.first) {
            return 1;
        } else if (o1.first<o2.first) {
            return -1;
        } else {
           return  0;
        }
    }
}
