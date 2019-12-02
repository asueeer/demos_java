package DataStructure.Sequence;

public class ListNode<E> {
    public E data; // 数值
    public ListNode<E> pre = null; // 前驱
    public ListNode<E> next = null; // 后继

    public ListNode(E data) {
        this.data = data;
    }

    public ListNode() {
    }

    public ListNode(E data, ListNode<E> pre, ListNode<E> next) {
        this.data = data;
        this.pre = pre;
        this.next = next;
    }

    // 两个函数写的还挺精巧的
    public ListNode<E> insertAsPre(E e){ // 紧靠当前节点之前插入新节点
        ListNode<E> temp = new ListNode<E>(e,pre,this); // 创建新节点
        pre.next = temp;
        pre = temp; // 设置正向连接
        return pre;
    }

    public ListNode<E> insertAsNext(E e){ // 紧靠当前节点之后插入新节点
        ListNode<E> temp = new ListNode<E>(e, this, next);
        next.pre = temp;
        next = temp;
        return next;
    }
}
