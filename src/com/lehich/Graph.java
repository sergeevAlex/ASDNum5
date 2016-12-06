package com.lehich;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private final int MAX_VERTEX = 20;
    private final int INF = 10000000;
    private Vertex vertexList[];
    private int adjMat[][];
    private int uorMat[][];
    private int nVerts;
    private int nTree;
    private int currentVert;
    private int startToCurrent;
    private DistPar sPath[];
    private ArrayList<Integer> adj[]; //список смежности
    private Queuee theQueue;
    private Queue<Integer> queue;
    private boolean used[];
    private Stack verStack;

    public Graph() {
        vertexList = new Vertex[MAX_VERTEX];
        adjMat = new int[MAX_VERTEX][MAX_VERTEX];
        uorMat = new int[MAX_VERTEX][MAX_VERTEX];
        nVerts = 0;
        nTree = 0;
        verStack = new Stack(MAX_VERTEX);
        theQueue = new Queuee();
        queue = new LinkedList<Integer>();
        adj = new ArrayList[MAX_VERTEX];
        used = new boolean[MAX_VERTEX];
        for (int j = 0; j < MAX_VERTEX; j++) {
            adj[j] = new ArrayList<Integer>();
            for (int k = 0; k < MAX_VERTEX; k++) {
                adjMat[j][k] = 0;
                uorMat[j][k] = 0;
            }
        }

        sPath = new DistPar[MAX_VERTEX];
    }

    public void addVertex(int lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }
    public void list_addEdge(int start,int end){
        adj[start].add(end);
        adj[end].add(start);
    }
    public void printVertex(int v) {
        System.out.print(vertexList[v].label + " ");
    }
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }

    public void dfs() {
        long starttime = System.nanoTime();
        vertexList[0].wasVisited = true;
        printVertex(0);
        verStack.push(0);
        while (!verStack.isEmpty()) {
            int v = getAdjUnvisitedVertex(verStack.readTop());
            if (v == -1) {
                verStack.pop();
            } else {
                vertexList[v].wasVisited = true;
                printVertex(v);
                verStack.push(v);
            }
        }
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].wasVisited = false;
        }
        long dfstime = System.nanoTime() - starttime;
        System.out.println();
        System.out.println("Time of dfs: " + dfstime);
    }

    public void list_dfs(int v) {
        if (used[v]) {
            return;
        }
        used[v] = true;
        System.out.print((v + 1) + " ");
        for (int i = 0; i < adj[v].size(); ++i) {
            int w = adj[v].get(i);
            list_dfs(w);
        }


    }
    public void list_bfs(int v) {
        Arrays.fill(used,false);

        if (used[v]) {
            return;
        }
        queue.add(v);
        used[v] = true;
        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.print((v + 1) + " ");
            for (int i = 0; i < adj[v].size(); ++i) {
                int w = adj[v].get(i);
                if (used[w]) {
                    continue;
                }
                queue.add(w);
                used[w] = true;
            }
        }
    }


    public void bfs() {
        long starttime = System.nanoTime();

        vertexList[0].wasVisited = true;
        printVertex(0);
        theQueue.insert(0);
        int v2;
        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                printVertex(v2);
                theQueue.insert(v2);
            }
        }
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].wasVisited = false;
        }

        long bfstime = System.nanoTime() - starttime;
        System.out.println();
        System.out.println("Time of bfs: " + bfstime);


    }

    public void path() {
        int startTree = 3;
        vertexList[startTree].isInTree = true;
        nTree = 1;
        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }
        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist == INF) {
                System.out.println("There are unreachable vertices");
                break;
            } else {
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }
        displayPaths();
        nTree = 0;
        for (int j = 0; j < nTree; j++) {
            vertexList[j].isInTree = false;
        }
    }


    public int getMin() {
        int minDist = INF;
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++) {
            if (!vertexList[j].isInTree && sPath[j].distance < minDist) {
                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    public void displayPaths() {
        for (int j = 0; j < nVerts; j++) {
            System.out.print(vertexList[j].label + "=");
            if (sPath[j].distance == INF) {
                System.out.print("inf");
            } else System.out.print(sPath[j].distance);
            int parent = vertexList[sPath[j].parentVert].label;

            System.out.print("(" + parent + ") ");
        }
        System.out.println("");
    }

    public void adjust_sPath() {
        int column = 1;
        while (column < nVerts) {
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = sPath[column].distance;
            if (startToFringe < sPathDist) {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }


    public void uorshell() {
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                if (adjMat[i][j] == 0) {
                    adjMat[i][j] = INF;
                }
            }
        }
        for (int k = 0; k < nVerts; k++) {
            for (int i = 0; i < nVerts; i++) {
                for (int j = 0; j < nVerts; j++) {
                    uorMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                }
            }
        }
        System.out.println("Warshall algorithm: ");
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                if(uorMat[i][j] == INF){
                    System.out.printf("%4d", 0);
                }
                else{
                    System.out.printf("%4d", uorMat[i][j]);
                }
            }
            System.out.println();
        }

    }
}


