package DataStructure.Sequence;

import java.util.Stack;

public class GetMinStack {
    Stack<Integer> st1 = new Stack<Integer>();
    Stack<Integer> st_min = new Stack<Integer>();

    public int pop(){
        int temp = st1.pop();
        if (temp>getMin()){
            return temp;
        }else{
            return st_min.pop();
        }
    }

    public void push(int i){
        st1.push(i);
        if(getMin()>=i){
            st_min.push(i);
        }
    }

    public int getMin(){
        if (st_min.empty()){
            return 99999;
        }
        return st_min.peek();
    }

    public static void main(String[] args) {
        GetMinStack stack = new GetMinStack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 20; i > -20; i--) {
            stack.push(i);
        }
        System.out.println(stack.getMin());
    }
}
