package com.company;

import DataStructure.Graph.Graph;

public class graphTest {

    public static void main(String[] args) {
        Graph<String, Integer> G = new Graph<String, Integer>();
        G.insert("A");
        G.insert("B");
        G.insert("C");
        G.insert("D");

        G.insert(3,10,0,2);
        G.insert(7,10,1,0);
        G.insert(9,10,1,2);
        G.insert(2,10,1,3);
        G.insert(4,10,2,0);
        G.insert(5,10,3,2);


//        System.out.println(G.E);
//        System.out.println(G.E.get(0));
        G.remove(0);
//        System.out.println(G.E.get(0));

        System.out.println(G.E);


    }
}
