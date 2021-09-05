package com.khalil.rain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class State {
    private  Set<String> statGraph;
    public State(){
        statGraph=new TreeSet<>();

    }

    public void addToGraph(String g){
        statGraph.add(g);
    }

    public Set<String> getGraph() {
        return this.statGraph;
    }
}
