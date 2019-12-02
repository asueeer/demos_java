package DataStructure.Sequence;

import java.util.Random;

import static DataStructure.Utils.fib;
import static DataStructure.Utils.swap;

public class SortedVector<E extends Comparable> extends Vector<E> {
    public int disordered() {
        int n = 0;
        for (int i = 1; i < _size; i++) {
            E pre = (E)_elems[i-1];
            E now = (E)_elems[i]; // 比较
            if(pre.compareTo(now)==1){ //pre>now,逆序
                n++;
            }
        }
        return n; // 向量有序当且仅当n=0
    }

    public void sort() {
        Random ra = new Random();
        int n = ra.nextInt();
        if (n%2 == 0){ // 各按50%的概率随机选用
            bubbleSort(0,_size);
        }else{
            mergeSort(0,_size);
        }
    }

    private void mergeSort(int lo, int hi) {
        if (hi-lo<2){
            return ; // 单元素区间自然有序
        }
        int mi = (lo + hi)/2;
        mergeSort(lo,mi); //分别排序
        mergeSort(mi,hi);
        merge(lo,mi,hi); // 归并
    }

    public void merge(int lo, int mi, int hi) { // 合并各自有序的子向量[lo,mi)和[mi,hi)
        Object [] A = new Object[hi-lo]; // 空间复杂度有点高，时间复杂度还可以降低一倍，不一定稳定
        int n1 = mi - 1;
        int n2 = hi - 1;
        for (int i = hi - lo - 1; i >= 0; i--) {
            if (n1==(lo-1)){
                A[i] = _elems[n2];
                n2--;
                continue;
            }
            if (n2==(mi-1)){
                A[i] = _elems[n1];
                n1--;
                continue;
            }
            E elem_n1 = (E) _elems[n1];
            if (elem_n1.compareTo(_elems[n2])==-1){ // 只有_elems[n1]>_elems[n2]时才会交换，确保稳定性
                A[i] = _elems[n2];
                n2--;
            }else{
                A[i] = _elems[n1];
                n1--;
            }
        }

        for (int i = lo; i < hi; i++) {
            _elems[i]=A[i - lo];
        }
    }

    private void bubbleSort(int lo, int hi) {
        boolean sorted = false;
        while(!sorted){
            sorted = bubble(lo,hi);
        }
    }

    private boolean bubble(int lo, int hi) {
        boolean sorted = true;
        for (int i = lo + 1; i < hi; i++) {
            E pre = (E) _elems[i-1];
            E now = (E) _elems[i];
            if (pre.compareTo(now)==1){
                sorted = false;
                Object[] swaps = swap(_elems[i-1],_elems[i]); // 交换_elems[i-1],_elems[i]两个元素
                _elems[i-1] = swaps[0];
                _elems[i] = swaps[1]; // 采用数组传值，感觉不是很优雅
            }
        }
        return sorted;
    }

    public int search(E e, int lo, int hi) {
        return binSearchC(_elems, e, lo, hi); // 此处采取教材里的二分查找版本C，为最好的版本
    }

    public int searchII(E e, int lo, int hi) {
        Random ra = new Random();
        int n = ra.nextInt();
        if (n%2 == 0){ // 各按50%的概率随机选用
            return binSearch(_elems, e, lo, hi); // 二分查找算法
        }else{
            return fibSearch(_elems, e, lo, hi); // Fibonacci查找算法
        }
    }

    private int binSearchC(Object[] elems, E e, int lo, int hi) {
        while(lo<hi){
            int mi = (lo+hi)/2;
            if(e.compareTo(_elems[mi])==-1){
                hi = mi;
            }else{
                lo = mi + 1;
            }
        } // 成功查找不能提前终止
        lo--; // 循环结束时，lo为大于e元素的最小秩，故lo-1为不大于e元素的最大秩
        return lo;
    } // 有多个命中元素时，总能保证返回秩最大者；查找失败时，能够返回失败的位置

    private int fibSearch(Object[] elems, E e, int lo, int hi) {
        while(lo<hi){
            int n = hi - lo;
            while(hi-lo < fib(n)){ // 此处可以优化，比如创建一个fib数列
                n--;
            } // 最终的fibonacci<=hi-lo,为fib的第i项
            int mi = lo + fib(n) - 1;
            if (e.compareTo(elems[mi])==-1){
                hi = mi;
            }else if(e.compareTo(_elems[mi])==1){
                lo = mi + 1;
            }else{
                return mi;
            }
        }
        return -1; // 查找失败
    } // 有多个命中元素时，不能返回秩最大者；失败时，简单返回-1，不能指示失败的位置

    private int binSearch(Object[] elems, E e, int lo, int hi) {
        while(lo<hi){
            int mi = (lo + hi) / 2; // 如果lo和hi越界，则会产生bug
            if (e.compareTo(elems[mi])==-1){ // e<_elems[mi]
                hi = mi;  // 深入前半段[lo,mi) 进行查找
            }else if(e.compareTo(_elems[mi])==1){ // e>_elems[mi]
                lo = mi+1; // 深入后半段（mi，hi）进行查找
            }else{
                return mi;
            }
        }
        return -1; // 查找失败
    } // 有多个命中元素时，不能返回秩最大者；失败时，简单返回-1，不能指示失败的位置

    public int uniquify() { // 有序向量的去重方法，以重复区间为单位，成批删除雷同元素
        int i = 0,  j = 1;
        while(j<_size){ //逐行扫描，直到末元素
            if (!_elems[i].equals(_elems[j])){
                i++;
                _elems[i] = _elems[j];
            }
            j++;
        }
        i++;
        _size = i;
        shrink();
        return j - i;
    }
}
