package DataStructure.Sequence;
import static DataStructure.Utils.*;
import static java.lang.Math.max;


public class Vector<E> implements Sequence<E> {
    protected int _size;
    private int _capacity; // 规模、容量
    private static final int DEFAULT_CAPACITY = 1024;
    protected Object [] _elems; // 数据（对象数组),object是和[]一起出现的

    public Vector(int _capacity) { // 默认
        this._capacity = _capacity;
        this._elems = new Object[_capacity];
        this._size = 0;
    }

    public Vector(Object[] _elems) { // 数组整体复制
        copyFrom(_elems, 0, _elems.length);
    }

    void copyFrom(Object[] _elems, int lo, int hi){  // 以数组区间_elems[lo,hi)为蓝本复制向量，左闭右开
        this._capacity = 2 * (hi - lo);
        this._elems = new Object[_capacity];  // 分配空间
        this._size = 0; // 规模清零
        while(lo<hi){
            this._elems[_size] = _elems[lo];
            lo++;
            _size++;
        }
    }

    public Vector(Object[] _elems, int lo, int hi) { // 数组区间复制
        copyFrom(_elems, lo, hi);
    }

    private void expand(){ // 向量空间不足时扩容
        if (_size<_capacity) return;
        _capacity = max(_capacity, DEFAULT_CAPACITY); // 不低于最小容量
        Object[] old_elems = _elems; //
        _capacity = _capacity*2; // 容量加倍
        _elems = new Object[_capacity];
        for (int i = 0; i < _size; i++) { // 复制原向量的内容
            _elems[i] = old_elems[i]; // C++中需要重载操作符，这里不好说
        }
    }

    public void shrink() {

    }

    public Object[] get_elems() {
        Object[] temp = new Object [_size];
        for (int i = 0; i < _size; i++) {
            temp[i] =  _elems[i];
        }
        return temp;
    }

    public Vector(Vector<E> V) { // 向量整体复制
        copyFrom(V._elems, 0, V.size());
    }

    public Vector(Vector<E> V, int lo, int hi) { // 向量区间复制
        this._elems =  V._elems;
    }

    public Vector() {
        this._capacity = DEFAULT_CAPACITY;
        this._elems = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this._size;
    }

    @Override
    public E get(int r) {
        return (E) _elems[r];
    }

    @Override
    public void put(int r, E e) {
        _elems[r] = e;
    }

    @Override
    public int insert(int r, E e) {
        expand(); // 若有必要，扩容
        for (int i = _size; i > r; i--) { // 自后向前
            _elems[i] = _elems[i-1]; // 后继元素顺次后移一个单元
        }
        _elems[r] = e;
        _size++;
        return r;
    }

    public int append(E e){
        return insert(_size, e);
    }

    public int remove(int lo, int hi) { // 删除区间[lo,hi)，0<=lo<=hi<=size
        if (lo==hi) return 0; // 出于效率考虑，单独处理退化情况
        while(hi<_size){
            _elems[lo] = _elems[hi];
            lo++;
            hi++;
        }
        _size = lo;
        shrink();
        return hi - lo;
    }

    @Override
    public E remove(int r) { // 可视为区间删除操作的特例[r]=[r,r+1]
        E e = (E) _elems[r]; // 备份被删除元素
        remove(r,r+1);
        return e; // 返回被删除元素
    }

    public int find(E e){
        return find(e,0, _size);
    }

    public int find(E e, int lo, int hi) {
        for (int i = hi-1; i >= lo; i--) {
            if (_elems[i].equals((E) e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int deduplicate() { // 繁琐版，向量是无序状态下
        int OldSize = _size;
        int i = 1;
        while(i<_size) {
            if (find((E) _elems[i], 0, i) < 0) {
                i++;
            } else {
                remove(i);
            }
        }
        return OldSize - _size;
    }

    @Override
    public Object[] traverse() {
        return get_elems();
    }

    @Override
    public String toString() {
        return StringOf(_elems,0,size());
    }

    public boolean empty(){
        if(_size==0){
            return true;
        }
        else{
            return false;
        }
    }

    public Vector<E> get(int lo, int hi){
        lo = index(lo, _size);
        hi = index(hi, _size);

        Vector<E> temp = new Vector<>();
        for (int i = lo; i < hi; i++) {
            temp.append((E) _elems[i]);
        }
        return temp;
    }
}


