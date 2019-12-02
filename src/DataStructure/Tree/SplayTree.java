package DataStructure.Tree;

public class SplayTree<E extends Comparable> extends BST<E> {
    public SplayTree(TreeNode<E> node) {
        super(node);
    }

    TreeNode<E> splay (TreeNode<E> v){ // 将节点v伸展至树根
        if(v==null) return null;
        TreeNode<E> p = v.parent;
        TreeNode<E> g = null;
        if(p != null){
            g = p.parent;
        }
        while((p!=null)&&(g!=null)){
            TreeNode<E> gg = g.parent; // 每轮之后v都以原祖父的父亲为父
            if(v.IsLeftChild()){
                if(p.IsLeftChild()){ // zig-zig
                    attachAsLeftChild(g, p.rightChild); attachAsLeftChild(p, v.rightChild);
                    attachAsRightChild(p, g); attachAsRightChild(v, p);
                }else{ // zig-zag
                    attachAsLeftChild(p, v.rightChild); attachAsRightChild(g, v.leftChild);
                    attachAsLeftChild(v, g); attachAsRightChild(v, p);
                }
            }else if(p.IsRightChild()){ // zag-zag
                attachAsRightChild(g, p.leftChild); attachAsRightChild(p, v.leftChild);
                attachAsLeftChild(p, g); attachAsLeftChild(v, p);
            }else{ // zag-zig
                attachAsRightChild(p, v.leftChild); attachAsLeftChild(g, v.rightChild);
                attachAsRightChild(v, g); attachAsLeftChild(v, p);
            }

            if(gg==null){
                v.parent = null; // 如果v的曾祖父不存在，v现在应该是树根
            }else{
                if(g==gg.leftChild){
                    attachAsLeftChild(gg, v);
                }else{
                    attachAsRightChild(gg, v);
                }
                updateHeight(g);
                updateHeight(p);
                updateHeight(v);
            }
            p = v.parent;
            g = p.parent;
        }
        p = v.parent;
        if(p!=null){
            if(v.IsLeftChild()){
                attachAsLeftChild(p, v.rightChild);
                attachAsRightChild(v, p);
            }else {
                attachAsRightChild(p, v.leftChild);
                attachAsLeftChild(v, p);
            }
            updateHeight(p);
            updateHeight(v);
        }
        v.parent = null;
        return v;
    }

    @Override
    public TreeNode<E> search(E e) {
        _hot = null;
        TreeNode<E> p = searchIn(_root, e);
        if(p==null){
            _root = splay(_hot);
        }else{
            _root = splay(p);
        }
        if(_root.data.equals(e)){
            return _root;
        }else{
            return _root.parent;
        }
    }

    @Override
    public TreeNode<E> insert(E e) {
        return super.insert(e);
    }

    @Override
    public boolean remove(E e) {
        return super.remove(e);
    }

    private TreeNode attachAsLeftChild(TreeNode<E> x, TreeNode<E> s){
        if (s==null) {
            x.leftChild = null;
            return x;
        }else{
            s.parent = x;
            x.leftChild = s;
            _size = _root.traverse_In().size();
            return x;
        }
    }

    private TreeNode attachAsRightChild(TreeNode<E> x, TreeNode<E> s){
        if (s==null) {
            x.rightChild = null;
            return x;
        }else{
            s.parent = x;
            x.rightChild = s;
            _size = _root.traverse_In().size();
            return x;
        }
    }


    public int height(TreeNode<E> x){
        return x.height;
    }
}
