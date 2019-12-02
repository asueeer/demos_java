package com.company;

import DataStructure.Dictionary.HashTable;

public class HashTest {



    public static void main(String[] args) {
        HashTable<Integer, Integer> dic = new HashTable<Integer,Integer>(30);

        for (int i = 0; i < 10; i++) {
            dic.put(i,i);
        }
        dic.remove(1);
        System.out.println(dic.get(1));

    }

}
