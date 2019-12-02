package DataStructure.Tree;

import static DataStructure.Utils.swap;

public class BST<E extends Comparable> extends BinTree<E>{ // 二叉搜索树

    TreeNode<E> _hot; // 辅助变量，存放"命中节点"的上一个节点

    public BST(TreeNode<E> node) {
        super(node);
    }

    public TreeNode<E> search(E e){
        TreeNode<E> TreeNode_now = _root;
        _hot = null;
        return searchIn(_root, e);
    }

    public TreeNode<E> searchIn(TreeNode<E> v, E e){
        if (v==null)
            return null;
        if (v.data==e){
            return v;
        }
        _hot = v;
        if (v.data.compareTo(e)==-1){
            v = v.rightChild;
        }else{
            v = v.leftChild;
        }

        return searchIn(v, e);
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
        updateHeightAbove(x);
        return x;
    }

    public boolean remove(E e){
        TreeNode<E> x = search(e);
        if(x==null)
            return false;
        removeAt(x);
        _size--;
        updateHeightAbove(_hot);
        return true;
    }

    TreeNode<E> removeAt(TreeNode<E> x){
        TreeNode<E> xx = x; // 存放实际被删除节点
        TreeNode<E> temp; // 临时变量
        TreeNode<E> succ = null;

        if(!x.HasLeftChild()){
            x = x.rightChild;
            succ = x;
        }else if(!x.HasRightChild()){
            x = x.leftChild;
            succ = x;
        }else{ // 左右节点都存在
            temp = x.succ();
            swap(x,temp); // 交换x的data和temp的data，问题转换成删除temp中的数据
            return removeAt(temp);
        }

        _hot = xx.parent;
        if(succ!=null){
            succ.parent = _hot;
            if(_hot.data.compareTo(succ.data)==-1){
                _hot.rightChild = succ;
            }else{
                _hot.leftChild = succ;
            }
        }else{
            if(xx.IsRoot()){
                _root = null;
            }
            if(xx.IsLeftChild()){
                xx.parent.leftChild = null;
            }else{
                xx.parent.rightChild = null;
            }
        }
        return succ;
    } // 很难处理，后期要优化

    private void swap(TreeNode<E> a, TreeNode<E> b){
        E temp = a.data;
        a.data = b.data;
        b.data = temp;
    }
}
