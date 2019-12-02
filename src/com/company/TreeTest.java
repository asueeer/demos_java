package com.company;

import DataStructure.Tree.*;

public class TreeTest {

    public static void main(String[] args) {
        TreeNode<Integer> node = new TreeNode<>(1);

        SplayTree<Integer> T = new SplayTree<>(node);
        for (int i = 0; i < 10; i++) {
            T.insert(i);
        }

        System.out.println(T._root.traverse_In());
        System.out.println(T._root.traverse_Level());
        System.out.println(T._root.traverse_In());
        System.out.println(T.height(T._root));
    }
}
