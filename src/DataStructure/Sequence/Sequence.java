package DataStructure.Sequence;

public interface Sequence<E> {
    int size();

    void put(int r, E e);

    int insert(int r, E e);

    E get(int r);

    E remove(int r);

    int deduplicate();

    Object[] traverse(); // 返回一个对象数组，由用户自己实现遍历操作
}