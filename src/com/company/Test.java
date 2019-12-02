package com.company;

import DataStructure.Sequence.List;

import static DataStructure.Utils.StringOf;

public class Test {
    static class Dog{
        String name = "dog0";

        public Dog(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        List<Integer> a = new List<Integer>(new Integer[]{3,2,1,112,2,3,3,32,2,33});
        List<Integer> b = new List<Integer>(new Integer[]{100,12});
//        System.out.println(a);

        List<Integer> c = a;
        System.out.println(c);
        a = b;
        System.out.println(c);
    }
}
