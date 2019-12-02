package DataStructure.Sequence;

import java.util.Random;

public class SortedList<E extends Comparable> extends List<E> {

    public void sort() {
        Random ra = new Random();
        int n = ra.nextInt();
        ListNode<E> p = first();
        if(n%3==0){
            mergeSort(p,last());
            insetionSort(p,_size); // 我个人不喜欢这种写法
        }else if(n%3==1){
            mergeSort(p,last());
            selectionSort(p,last());
        }else{
            mergeSort(p,last());
        }
    }

    private void mergeSort(ListNode<E> p1, ListNode<E> p2) { // 对[p1,p2]进行归并排序
        if (p1==p2) return ;
        int n = len(p1,p2);
        int m = n/2;
        // 均分列表
        ListNode<E> mi = p1;
        for (int i = 0; i < m-1; i++) {
            mi = mi.next;
        }
        mergeSort(p1, mi);
        mergeSort(mi.next, p2);
        List<E> sorted_L = merge(p1,mi,mi.next,p2);

        // 在这卡了两个小时，疯了！
        Object [] objs = sorted_L.traverse();
        for (int i = 0; i < sorted_L.size(); i++) {
            p1.data = (E) objs[i];
            p1 = p1.next;
        }
    }

    private int len(ListNode<E> p1, ListNode<E> p2) { // [p1,p2]的长度
        int n = 1;
        while(p1!=p2){
            p1 = p1.next;
            n++;
        }
        return n;
    }

    public List<E> merge(ListNode<E> p_start,ListNode<E> p_end, ListNode<E> q_start, ListNode<E> q_end) { // 对[p_start,p_end],[q_start,q_end]进行二路归并
        List<E> A = new List<>();
        while ((p_start!=p_end.next)|(q_start!=q_end.next)){
            if(p_start==p_end.next){
                A.append(q_start.data);
                q_start = q_start.next;
                continue;
            }
            if(q_start==q_end.next){
                A.append(p_start.data);
                p_start = p_start.next;
                continue;
            }
            if(q_start.data.compareTo(p_start.data)==-1){
                A.append(q_start.data);
                q_start = q_start.next;
            }else{
                A.append(p_start.data);
                p_start = p_start.next;
            }
        }
        return A;
    }

    public ListNode<E> selectMax(ListNode<E> p1, ListNode<E> p2){  // [p1,p2]的最大节点
        ListNode<E> max = p1;
        while (p1!=p2) {
            if (max.data.compareTo(p1.next.data)==1){   // max.data>p.pre.data
                p1 = p1.next;
            }else{
                p1 = p1.next;
                max = p1;
            }
        }
        return max;
    }

    private void selectionSort(ListNode<E> p1, ListNode<E> p2) { // 对[p1,p2]进行选择排序
        ListNode<E> head = p1.pre;
        ListNode<E> tail = p2.next;
        List<E> A = new List<>();
        while(head.next!=tail) {
            ListNode<E> MaxPos = selectMax(head.next,tail.pre);
            A.insertAsFirst(MaxPos.data);
            this.remove(MaxPos);
        }
        copyNodes(A.first(), A.size());
    }

    private void insetionSort(ListNode<E> p, int n) { // 列表区间排序
        for (int i = 0; i < n; i++) {
            ListNode insetPos = search(p.data,i,p); // 待插入的位置
            insertAfter(insetPos, p.data);
            p = p.next;
            remove(p.pre);
        }
    }

    public int uniquify() {
        if (_size<2){
            return 0;
        }

        int oldSize = _size;
        ListNode<E> p = first();
        ListNode<E> q = p.next;
        while(q!=tail){
            if(!p.data.equals(q.data)){
                p = q;
            }else{
                remove(q);
            }
            q = q.next;
        }
        return oldSize-_size; // 列表规模变化量，即被删除元素总数
    }

    public ListNode<E> search(E e, int n, ListNode<E> p) {
        // 由于列表的物理地址和逻辑次序毫无关系，有序列表的查找不得不延续无序列表的查找策略
        p = p.pre;
        for (int i = 0; i < n; i++) {
            if(e.compareTo(p.data)==1){
                break;
            }else{
                p = p.pre;
            }
        }
        return p; // 返回查找的终止位置，如果查找失败，返回区间左边界的前驱（可能是head）
    }
}
