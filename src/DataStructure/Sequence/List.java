package DataStructure.Sequence;

import static DataStructure.Utils.StringOf;
import static DataStructure.Utils.index;

public class List<E> implements Sequence<E>{
    protected int _size; // 规模
    protected ListNode<E> head; // 头哨兵
    protected ListNode<E> tail; // 尾哨兵

    // 头、尾、首、末节点是有明确定义的，注意区分

    private void init(){ // 列表初始化，在创建列表对象时统一调用
        head = new ListNode<E>();
        tail = new ListNode<E>();
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
        _size = 0; // 记录规模
    }

    void copyNodes(ListNode<E> p, int n){ // 复制列表中自位置p起的n项
        init();
        for (int i = 0; i < n; i++) {
            insertAsLast(p.data);
            p = p.next;
        }
    }

    public List(Object[] objs) {
        init();
        for (int i = 0; i < objs.length; i++) {
            this.append((E) objs[i]);
        }
    }

    public List(ListNode<E> p, int n) { // assert:p为合法位置，且至少有n-1个后继节点
        copyNodes(p,n);
    }

    public List(List<E> L) { // 整体复制列表L
        copyNodes(L.first(), L.size());
    }

    public List(List<E> L, int r, int n) { // 复制L中自第r项起的n项（assert：r+n<=L._size）
        copyNodes(L.getListNode(r), n);
    }

    private ListNode<E> getListNode(int r) {
        ListNode<E> p = first();
        for (int i = 0; i < r; i++) {
            p = p.next;
        }
        return p;
    }

    public List() {
        init();
    }

    @Override
    public int size() {
        return _size;
    }

    @Override // put和get自己写的，不一定对
    public E get(int r) { // 通过秩访问列表节点，虽然方便，但效率低，慎用
        ListNode<E> p = first();
        for (int i = 0; i < r; i++) {
            p = p.next;
        }
        return p.data;
    } // 当r大于n/2时，从tail沿着pre逆行查找，可以在一定程度上减少迭代次数，但就总体的平均效率而言，这一改进并无实质性意义

    public ListNode<E> first() {
        return head.next; // 首节点位置
    }

    @Override //
    public void put(int r, E e) {
        ListNode<E> temp = getListNode(r);
        temp.data = e;
    }

    @Override
    public int insert(int r, E e) {
        insertBefore(getListNode(r),e);
        return r;
    }

    public ListNode<E> append(E e){
        return insertAsLast(e);
    }

    public ListNode<E> insertAsFirst(E e){
        _size++;
        head.insertAsNext(e); // e当作首节点插入
        return head.next;
    }

    public ListNode<E> insertAsLast(E e){
        _size++;
        tail.insertAsPre(e); // e当作首节点插入
        return tail.pre;
    }

    public ListNode<E> insertAfter(ListNode<E> p,E e){
        _size++;
        p.insertAsNext(e);
        return p.next;
    }

    public ListNode<E> insertBefore(ListNode<E> p,E e){
        _size++;
        p.insertAsPre(e);
        return p.pre;
    }

    @Override
    public E remove(int r) {
        ListNode<E> p = getListNode(r);
        return remove(p);
    }

    public E remove(ListNode<E> p){
        E e = p.data; // 备份，假定T类型可直接赋值
        p.pre.next  = p.next;
        p.next.pre = p.pre;
        _size--;
        return e;
    }

    public int clear(){
        int oldSize = _size;
        init();
        return oldSize;
    }

    public ListNode<E> find(E e, int n, ListNode<E> p) { // 在无序列表的内节点p（可能是tail）的n个（真）前驱中，找到等于e的最后者
        for (int i = 0; i < n; i++) {
            if(e.equals(p.pre.data)){
                return p;
            }else{
                p = p.pre;
            }
        }
        return null; // 查找失败
    }


    @Override
    public int deduplicate() {
        if (_size<2){
            return 0;
        }

        int oldSize = _size;
        ListNode<E> p = first();
        int r = 0;
        while(p!=tail){
            ListNode<E> q = find(p.data, r, p); // 在p的r个（真）前驱中查找雷同者
            if (q==null){
                r++;
            }else{
                remove(q);
            }
            p = p.next;
        }
        return oldSize-_size; // 列表规模变化量，即被删除元素总数
    }

    @Override
    public Object[] traverse() { // 返回一个对象数组，由用户自己实现遍历操作
        Object[] _elems = new Object [_size];
        ListNode<E> p = first();
        for (int i = 0; i < _size; i++) {
            _elems[i] = p.data;
            p = p.next;
        }
        return _elems;
    }

    @Override
    public String toString() {
        Object[] _elems = traverse();
        return StringOf(_elems,0, _size);
    }

    public ListNode<E> last() {
        return tail.pre;
    }

    public boolean empty(){
        if(_size==0){
            return true;
        }
        else{
            return false;
        }
    }

    public List<E> get(int lo, int hi){
        hi = index(hi, _size);
        lo = index(lo,_size);
        Object[] objs = new Object[hi-lo];
        for (int i = lo; i < hi; i++) {
            objs[i - lo] = get(i);
        }
        List<E> temp = new List<>(objs);
        return temp;
    }
}
