package com.company;

import DataStructure.Tree.BinTree;
import DataStructure.Tree.TreeNode;

public class treeTest2 {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("A");
        root.insertAsLeftChild("B");
        root.insertAsRightChild("C");
        TreeNode<String> temp = root.leftChild;
        temp.insertAsLeftChild("D");
        temp.insertAsRightChild("F");
        temp = temp.rightChild;
        temp.insertAsLeftChild("K");
        temp.insertAsRightChild("I");
        temp = root.rightChild;
        temp.insertAsLeftChild("E");
        temp.insertAsRightChild("G");
        temp = temp.leftChild;
        temp.insertAsLeftChild("H");
        temp.insertAsRightChild("J");

        System.out.println(root.traverse_Pre());
        System.out.println(root.traverse_In());
        System.out.println(root.traverse_After());

    }
}
