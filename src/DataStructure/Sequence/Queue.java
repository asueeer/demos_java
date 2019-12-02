package DataStructure.Sequence;

public class Queue<E> extends List<E>{
    public void add(E e){
        insertAsFirst(e);
    }

    public E dequeue(){
        return remove(last());
    }

    public E peek(){
        return last().data;
    }

}
