package DataStructure;

import DataStructure.Tree.TreeNode;

public class Utils {

    public static String StringOf(Object[] objs, int lo, int hi) {
        StringBuilder s = new StringBuilder("[");
        for (int i = lo; i < hi; i++) {
            s.append(objs[i].toString() + ",");
        }
        s.deleteCharAt(s.length() - 1);
        s.append("]");
        return s.toString();
    }

    public static String StringOf(Object[] objs) {
        StringBuilder s = new StringBuilder("[");
        for (Object obj : objs){
            if (obj!=null) {
                s.append(obj.toString() + ",");
            }
        }
        s.deleteCharAt(s.length() - 1);
        s.append("]");
        return s.toString();
    }

    public static int fib(int n){ // 时间复杂度过高
        if (n==1)
            return 1;
        if (n==2)
            return 1;
        return fib(n-1) + fib(n-2);
    }

    public static Object[] swap(Object a, Object b) {
        Object[] swaps = new Object[2];
        swaps[0] = b;
        swaps[1] = a;
        return swaps;
    }

    public static int index(int i, int size){
        if (i<0){
            return size+i;
        }

        return i;
    }

    boolean IsRoot(TreeNode x){
        if (x.parent==null){
            return true;
        }else{
            return false;
        }
    }

    boolean HasParent(TreeNode x){
        return !IsRoot(x);
    }

    boolean IsLeftChild(TreeNode x){
        if(!IsRoot(x)){ // 不是根节点的情况下
            if(x==x.parent.leftChild){
                return true;
            }else{
                return false;
            }
        }else{
            return false; // 是根节点，返回false
        }
    }

    boolean IsRightChild(TreeNode x){
        if(!IsRoot(x)){ // 不是根节点的情况下
            if(x==x.parent.rightChild){
                return true;
            }else{
                return false;
            }
        }else{
            return false; // 是根节点，返回false
        }
    }

    boolean HasLeftChild(TreeNode x){
        if(x.leftChild!=null)
            return true;
        return false;
    }

    boolean HasRightChild(TreeNode x){
        if(x.rightChild!=null)
            return true;
        return false;
    }

    boolean HasChild(TreeNode x){
        return HasLeftChild(x) || HasLeftChild(x);
    }

    boolean HasBothChild(TreeNode x){
        return HasLeftChild(x) && HasLeftChild(x);
    }

    boolean IsLeaf(TreeNode x){
        return !HasChild(x);
    }

    TreeNode sibling(TreeNode x){
        if(IsLeftChild(x)){
            return x.parent.rightChild;
        }else{
            return x.parent.leftChild;
        }
    }

    TreeNode uncle(TreeNode x){
        if(IsRoot(x)||IsRoot(x.parent)){
            return null;
        }
        if(IsLeftChild(x.parent)){
            return x.parent.parent.rightChild;
        }else{
            return x.parent.parent.leftChild;
        }
    }

    TreeNode FromParentTo(TreeNode x){
        if(IsRoot(x)){
            return null;
        }else if(IsLeftChild(x)){
            return x.parent.leftChild;
        }else {
            return x.parent.rightChild;
        }
    }


}
