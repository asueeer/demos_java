package DataStructure.Tree;

public class AVL<E extends Comparable> extends BST<E>{
    public AVL(TreeNode<E> node) {
        super(node);
    }

    public TreeNode<E> insert(E e){
        TreeNode<E> x = search(e);
        if (x!=null){
            return x; // 要插入的目标已经存在,已有雷同节点，插入操作失败
        }
        if(_hot.data.compareTo(e)==-1){
            _hot.insertAsRightChild(e);
        }else{
            _hot.insertAsLeftChild(e);
        }
        _size++;

        TreeNode<E> g = _hot; // g是被插入节点x的父亲
        while(g!=null){ // 从x的父亲出发，逐层检查各代祖先
            if(!AvlBalanced(g)){
                if(g.IsRoot()) {
                    _root = rotateAt(tallerChild(tallerChild(g)));
                }else {
                    if (g.IsLeftChild()) {
                        g.parent.leftChild = rotateAt(tallerChild(tallerChild(g)));
                    } else if (g.IsRightChild()) {
                        g.parent.rightChild = rotateAt(tallerChild(tallerChild(g)));
                    } // 等价于FromParentTo（g） = rotateAt(tallerChild(tallerChild(g)));
                }
                break;
            }else{
                updateHeight(g);
            }
            g = g.parent;
        }
        return null;
    }

    private TreeNode<E> rotateAt(TreeNode<E> v) { // v为非空孙辈节点
        TreeNode<E> p = v.parent;
        TreeNode<E> g = p.parent;
        if (p.IsLeftChild()) {
            if (v.IsLeftChild()) {
                p.parent = g.parent;
                return connect34(v, p, g, v.leftChild, v.rightChild, p.rightChild, g.rightChild);
            } else {
                v.parent = g.parent;
                return connect34(p, v, g, p.leftChild, v.leftChild, v.rightChild, g.rightChild);
            }
        }else if(v.IsRightChild()) {
                p.parent = g.parent;
                return connect34(g, p, v, g.leftChild, p.leftChild, v.leftChild, v.rightChild);
        }else{
                v.parent = g.parent;
                return connect34(g, v, p, g.leftChild, v.leftChild, v.rightChild, p.rightChild);
        }
    }

    protected TreeNode<E> connect34(TreeNode<E> a, TreeNode<E> b, TreeNode<E> c, TreeNode<E> T0, TreeNode<E> T1, TreeNode<E> T2, TreeNode<E> T3) {
        a.leftChild = T0; if(T0!=null) T0.parent = a;
        a.rightChild = T1; if(T1!=null) T1.parent = a; updateHeight(a);
        c.leftChild = T2; if(T2!=null) T2.parent = c;
        c.rightChild = T3; if(T3!=null) T3.parent = c; updateHeight(c);
        b.leftChild = a; a.parent = b;
        b.rightChild = c; c.parent = b; updateHeight(b);
        return b; // 该子树新的根节点
    }

    @Override
    public boolean remove(E e){
        TreeNode<E> x = search(e);
        TreeNode<E> hot = _hot;
        if(x==null)
            return false;
        super.removeAt(x); // 经过这么一个函数，_hot会被改变
        _size--;

        TreeNode<E> g = hot;
        while(g!=null){
            if(!AvlBalanced(g)){
                if(g.IsRoot()){
                    _root = rotateAt(tallerChild(tallerChild(g)));
                }
                else{
                    if(g.IsLeftChild()){
                        g.parent.leftChild = rotateAt(tallerChild(tallerChild(g)));
                    }else{
                        g.parent.rightChild = rotateAt(tallerChild(tallerChild(g)));
                    }
                    updateHeight(g);
                }
            }
            g = g.parent;
        }
        return true;
    }

    private int stature(TreeNode<E> x){
        if (x==null){
            return -1;
        }else{
            return x.height;
        }
    }

    boolean Balanced(TreeNode<E> x){ // 理想平衡条件
        return stature(x.leftChild)==stature(x.rightChild);
    }

    int BlaFac(TreeNode<E> x){ // 平衡因子
        return stature(x.leftChild) - stature(x.rightChild);
    }

    boolean AvlBalanced(TreeNode<E> x){ // AVL平衡条件
        return (BlaFac(x)>=-1)&&(BlaFac(x)<=1);
    }

    // 取x的左右节点中最高者,若左右等高，返回与父亲x同侧者
    TreeNode<E> tallerChild(TreeNode<E> x){
        if(stature(x.leftChild)>stature(x.rightChild)){
            return x.leftChild;
        }else if(stature(x.leftChild)<stature(x.rightChild)){
            return x.rightChild;
        }else{
            if(x.IsLeftChild()){
                return x.leftChild;
            }else{
                return x.rightChild;
            }
        }
    }
}
