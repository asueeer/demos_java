package com.company;

import DataStructure.Graph.Edge;

import java.util.ArrayList;

public class arraylistTest {


    public static void main(String[] args) {
        ArrayList<Edge<Integer>> arr = new ArrayList<>();
        arr.add(new Edge<Integer>(10,10));

        Edge<Integer> e = arr.get(0);
        e.weight++;
        System.out.println(arr.get(0).weight);
    }
}
