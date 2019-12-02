package com.company;


import DataStructure.Sequence.List;
import DataStructure.Sequence.Queue;
import DataStructure.Sequence.Stack;


import static DataStructure.Utils.StringOf;

public class Main {

    public static class Node{
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    public static void main(String[] args) {
	// write your code here
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
//        System.out.println(st.top());

        Queue<Integer> que = new Queue<>();
        que.add(1);
        que.add(2);
//        System.out.println(que.peek());

        List<Integer> a = new List<>(new Integer[]{1, 2, 2, 3, 3, 2, 2});
    }
}
