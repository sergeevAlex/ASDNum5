package com.lehich;

public class Vertex {
    public int label;
    public boolean wasVisited;
    public boolean isInTree;
    public Vertex(int lab){
        label = lab;
        wasVisited = false;
        isInTree = false;
    }
}
