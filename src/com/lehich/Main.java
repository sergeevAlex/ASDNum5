package com.lehich;

/**
 * @author AlexSergeev
 * Module: Graphs and Warshall algorithm
 */
public class Main {

    public static void main(String[] args) {
        Graph gr = new Graph();
        gr.addVertex(1);//0
        gr.addVertex(2);//1
        gr.addVertex(3);//2
        gr.addVertex(4);//3
        gr.addVertex(5);//4
        gr.addVertex(6);//5
        gr.addVertex(7);//6
        gr.addVertex(8);//7
        gr.addVertex(9);//8
        gr.addVertex(10);//9

        long addedge_starttime = System.nanoTime();
        gr.addEdge(1,0,1);
        gr.addEdge(2,1,1);
        gr.addEdge(3,0,1);
        gr.addEdge(3,2,1);
        gr.addEdge(4,0,1);
        gr.addEdge(4,1,1);
        gr.addEdge(4,2,1);
        gr.addEdge(4,3,1);
        gr.addEdge(5,3,1);
        gr.addEdge(6,5,1);
        gr.addEdge(7,2,1);
        gr.addEdge(7,6,1);
        gr.addEdge(8,7,1);
        gr.addEdge(9,7,1);
        long addEdge_endtime = System.nanoTime() - addedge_starttime;
        System.out.println("Время добавление связей(матрицы): " + addEdge_endtime );

        long listaddedge_starttime = System.nanoTime();

        gr.list_addEdge(1,0);
        gr.list_addEdge(2,1);
        gr.list_addEdge(3,0);
        gr.list_addEdge(3,2);
        gr.list_addEdge(4,0);
        gr.list_addEdge(4,1);
        gr.list_addEdge(4,2);
        gr.list_addEdge(4,3);
        gr.list_addEdge(5,3);
        gr.list_addEdge(6,5);
        gr.list_addEdge(7,2);
        gr.list_addEdge(7,6);
        gr.list_addEdge(8,7);
        gr.list_addEdge(9,7);
        long listaddedge_endtime = System.nanoTime() - listaddedge_starttime;
        System.out.println("Время добавление связей(список): " + listaddedge_endtime);


        System.out.print("Visits dfs: ");
        gr.dfs();
        System.out.println();
        System.out.print("DFS by lists: ");
        long ldfs_starttime = System.nanoTime();
        gr.list_dfs(4);
        long ldfs_endtime = System.nanoTime() - ldfs_starttime;
        System.out.println("Time of dfs_list: " + ldfs_endtime);
        System.out.println();
        System.out.println();
        System.out.print("Visits bfs: ");
        gr.bfs();
        System.out.println();
        System.out.println("BFS by lists: ");
        long lbfs_starttime = System.nanoTime();
        gr.list_bfs(7);
        long lbfs_endtime = System.nanoTime() - lbfs_starttime;
        System.out.println("Time of lbfs: " + lbfs_endtime);
        System.out.println();

        Graph shortway = new Graph();

        shortway.addVertex(1); //A - 0
        shortway.addVertex(2); //B - 1
        shortway.addVertex(3); //C - 2
        shortway.addVertex(4); //D - 3
        shortway.addVertex(5); //E - 4

        shortway.addEdge(0, 1, 50);  // AB 50
        shortway.addEdge(0, 3, 80);  // AD 80
        shortway.addEdge(1, 2, 60);  // BC 60
        shortway.addEdge(1, 3, 90);  // BD 90
        shortway.addEdge(2, 4, 40);  // CE 40
        shortway.addEdge(3, 2, 20);  // DC 20
        shortway.addEdge(3, 4, 70);  // DE 70
        shortway.addEdge(4, 1, 50);  // EB 50
        System.out.println("Shortest paths: ");
        shortway.path();
        System.out.println();
        shortway.uorshell();
    }
}
