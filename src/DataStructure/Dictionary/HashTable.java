package DataStructure.Dictionary;

import DataStructure.Tree.Entry;

public class HashTable<K, V> {
    private Entry[] buckets;
    public int M; // 桶数组容量
    public int N; // 词条数量
    private boolean[] lazyRomoval; // 懒惰删除标记

    // 懒惰删除
    boolean lazilyRemoved(int r){
        return lazyRomoval[r];
    }

    void markAsRemoved(int r){
        lazyRomoval[r] = true;
    }

    protected int probeForHit(K k){  // 沿关键码k对应的查找链，找到词条匹配的那个桶
        int r = hashCode(k) % M;
        while(((buckets[r]!=null)&&(k!=buckets[r].key))||((buckets[r]==null)&&(lazilyRemoved(r)))){
            r = (r + 1) % M;
        }
        return r; // 调用者根据r是否为空，即可判断查找是否成功
    }

    private int hashCode(K k) {
        String s = k.toString();
        return s.hashCode();
    }

    protected int probeForFree(K k){ // 沿关键码k对应的查找链，找到首个可用的空桶
        int r = hashCode(k) % M;
        while(buckets[r]!=null){
            r = (r+1) % M;
        }
        return r;
    }

    protected void rehash(){ // 重散列算法：扩充桶数组，保证装填因子在警戒线以下
        int old_Capacity = M;
        Entry<K, V>[] old_buckets = buckets;
        M = prime_NoLessThan(M*2);
        N = 0;
        buckets = new Entry[M];
        lazyRomoval = new boolean[M];
        for (int i = 0; i < old_Capacity; i++) {
            if(old_buckets[i]!=null){ // 将非空桶中的词条逐一插入至新的桶数组
                put(old_buckets[i].key, old_buckets[i].value);
            }
        }
    }

    public HashTable(int c) {
        M = prime_NoLessThan(c);
        N = 0;
        buckets = new Entry[M];
        lazyRomoval = new boolean[M];
        for (int i = 0; i < M; i++) {
            lazyRomoval[i] = false;
        }
    }

    private int prime_NoLessThan(int i) {
        while(!IsPrimeNumber(i)){
            i = i + 1;
        }
        return i;
    }

    private boolean IsPrimeNumber(int i) {
        for (int j = 2; j < i; j++) {
            if(i%j==0)
                return false;
        }
        return true;
    }

    public int size(){
        return N;
    }

    public boolean put(K k, V v){ // 禁止雷同词条，故插入可能失败
        if(buckets[probeForHit(k)]!=null) return false;
        int r = probeForFree(k); // 为新词条找个空桶，只要装填因子控制得当，必然成功
        buckets[r] = new Entry<K,V>(k,v);
        N++;

        if(N*2>M){
            rehash(); // 装填因子高于50%时，进行重散列
        }
        return true;
    }

    public V get(K k){ // 读取
        int r = probeForHit(k);
        if(buckets[r]!=null){
            return (V) buckets[r].value;
        }else{
            return null;
        }
    }

    public boolean remove(K k){ // 删除
        int r = probeForHit(k);
        if(buckets[r]==null) return false; // 对应词条为空，无法删除

        buckets[r] = null;
        markAsRemoved(r);
        N--;
        return true; // 惰性删除
    }
}
