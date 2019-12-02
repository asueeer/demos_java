package DataStructure.Sequence;

public class Stack<E> extends Vector<E>{
    public void push (E e){
        append(e);
    }

    public E pop(){
        return remove(size() - 1);
    }

    public E top(){
        return this.get(size() - 1);
    }

}
