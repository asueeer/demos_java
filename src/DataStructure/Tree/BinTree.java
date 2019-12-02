package DataStructure.Tree;

import static java.lang.Math.max;

public class BinTree<E> {
    int _size;
    public TreeNode<E> _root;

    public BinTree(TreeNode<E> x) {
        insertAsRoot(x);
    }

    public BinTree() {
        _size = 0;
        _root = null;
    }

    int updateHeight(TreeNode<E> x){
        x.height = 1 + max(stature(x.leftChild),stature(x.rightChild));
        return x.height;
    }

    protected void updateHeightAbove(TreeNode<E> x){ // 更新x及x以上的节点高度
        while(x!=null){
            updateHeight(x);
            x = x.parent;
        }
    }

    public int size(){
        return _size;
    }

    private int stature(TreeNode<E> x){
        if (x==null){
            return -1; // 约定空树的高度为-1
        }else{
            return x.height;
        }
    }

    TreeNode<E> insertAsRoot(E e){
        _size = 1;
        _root = new TreeNode<E>(e);
        return _root;
    }

    TreeNode<E> insertAsRoot(TreeNode<E> x){
        if(x==null) {
            _size = 0;
            _root = null;
            return null;
        }
        _size = x.size();
        _root = x;
        return _root;
    }

    TreeNode<E> insertAsLeftChild(TreeNode<E> x,E e){
        _size++;
        x.insertAsLeftChild(e);
        updateHeightAbove(x);
        return x.leftChild;
    }

    TreeNode<E> insertAsRightChild(TreeNode<E> x,E e){
        _size++;
        x.insertAsRightChild(e);
        updateHeightAbove(x);
        return x.rightChild;
    }

    void clear(){
        _size = 0;
        _root = null;
    }

    public TreeNode attachAsLeftChild(TreeNode<E> x, BinTree<E> S){
        x.leftChild = S._root;
        if(x.leftChild!=null){
            x.leftChild.parent = x;
        }

        _size = _size + S._size;
        updateHeightAbove(x);
        S.clear();
        return x;
    }

    public TreeNode attachAsRightChild(TreeNode<E> x, BinTree<E> S){
        x.rightChild = S._root;
        if(x.rightChild!=null){
            x.rightChild.parent = x;
        }

        _size = _size + S._size;
        updateHeightAbove(x);
        S.clear();
        return x;
    }

    // 子树删除
    public int remove(TreeNode<E> x){
        if(x.IsLeftChild()){
            x.parent.leftChild = null;
        }
        if(x.IsRightChild()){
            x.parent.rightChild = null;
        } // 切断x的父节点的引用

        updateHeightAbove(x.parent);
        _size = _size - x.size();
        return x.size();
    }

    // 子树分离，与子树删除过程相似，不同之处在于，需要对分离出来的子树重新封装，并返回给上层调用者
    public BinTree<E> devide(TreeNode<E> x){
        if(x.IsLeftChild()){
            x.parent.leftChild = null;
        }
        if(x.IsRightChild()){
            x.parent.rightChild = null;
        } // 切断x的父节点的引用

        updateHeightAbove(x.parent);

        BinTree<E> S = new BinTree<>();
        x.parent = null;
        S._root = x;
        S._size = x.size();

        _size = _size - x.size(); // 更新规模
        return S; // 返回分离出来的子树
    }
}
