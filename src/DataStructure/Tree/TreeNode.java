package DataStructure.Tree;

import DataStructure.Sequence.List;
import DataStructure.Sequence.Queue;
import DataStructure.Sequence.Stack;

public class TreeNode<E> {
    E data;
    public TreeNode<E> parent;
    public TreeNode<E> leftChild;
    public TreeNode<E> rightChild;
    int height;
    int nullPathLength; // 左式堆
    String color;

    private List<TreeNode<E>> list; // 一个作用范围较广的变量，用于辅助二叉树的遍历

    public TreeNode(E e, TreeNode<E> parent) {
        init();
        data = e;
        this.parent = parent;
    }

    void init(){
        parent = null;
        leftChild = null;
        rightChild = null;
        height = 0;
        nullPathLength = 1;
        color = "RED";
    }

    public TreeNode() {
        init();
    }

    public TreeNode(E data) {
        init();
        this.data = data;
    }

    public TreeNode(E data, TreeNode<E> parent, TreeNode<E> leftChild, TreeNode<E> rightChild, int height, int nullPathLength, String color) {
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = height;
        this.nullPathLength = nullPathLength;
        this.color = color;
    }

    public TreeNode(E data, TreeNode<E> parent, TreeNode<E> leftChild, TreeNode<E> rightChild, int height) {
        init();
        this.data = data;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = height;
    }

    int size(){
        return 0;
    }

    boolean IsRoot(){
        if (parent==null){
            return true;
        }else{
            return false;
        }
    }

    boolean HasParent(){
        return !IsRoot();
    }

    boolean IsLeftChild(){
        if(!IsRoot()){ // 不是根节点的情况下
            if(this== this.parent.leftChild){
                return true;
            }else{
                return false;
            }
        }else{
            return false; // 是根节点，返回false
        }
    }

    boolean IsRightChild(){
        if(!IsRoot()){ // 不是根节点的情况下
            if(this==parent.rightChild){
                return true;
            }else{
                return false;
            }
        }else{
            return false; // 是根节点，返回false
        }
    }

    boolean HasLeftChild(){
        if(leftChild!=null)
            return true;
        return false;
    }

    boolean HasRightChild(){
        if(rightChild!=null)
            return true;
        return false;
    }

    boolean HasChild(){
        return HasLeftChild()||HasRightChild();
    }

    boolean HasBothChild(){
        return HasLeftChild() && HasLeftChild();
    }

    boolean IsLeaf(){
        return !HasChild();
    }

    TreeNode<E> sibling(){
        if(parent.IsLeftChild()){
            return parent.rightChild;
        }else{
            return parent.leftChild;
        }
    }

    TreeNode<E> uncle(){
        if(IsRoot()||parent.IsRoot()){
            return null;
        }
        if(parent.IsLeftChild()){
            return parent.parent.rightChild;
        }else{
            return parent.parent.leftChild;
        }
    }

    TreeNode<E> succ(){ // 取当前节点的直接后继
        List<TreeNode<E>> tra_In = this.traverse_In();
        Object[] objs = tra_In.traverse();
        for (int i = 0; i < objs.length - 1; i++) {
            if(((TreeNode) objs[i]).data==this.data){
                return (TreeNode) objs[i+1];
            }
        }
        return null;
    }


    public TreeNode<E> insertAsLeftChild(E e){
        leftChild = new TreeNode<E>(e,this);
        return leftChild;
    }

    public TreeNode<E> insertAsRightChild(E e){
        rightChild = new TreeNode<E>(e,this);
        return rightChild;
    }

    public List<TreeNode<E>> traverse_Pre(){ // 对x进行前序遍历，返回一个对象列表
        list = new List<TreeNode<E>>(); // 对list初始化
        tra_Pre(this);
        return list;
    }

    private void tra_Pre(TreeNode<E> x){
        if (x==null) return ;
        list.append(x);
        tra_Pre(x.leftChild);
        tra_Pre(x.rightChild);
    }

    public List<TreeNode<E>> traverse_After(){ // 对x进行前序遍历，返回一个对象列表
        list = new List<TreeNode<E>>(); // 对list初始化
        tra_After(this);
        return list;
    }

    private void tra_After(TreeNode<E> x){
        if (x==null) return ;
        tra_After(x.leftChild);
        tra_After(x.rightChild);
        list.append(x);
    }

    public List<TreeNode<E>> traverse_In(){ // 对x进行前序遍历，返回一个对象列表
        list = new List<TreeNode<E>>(); // 对list初始化
        tra_In(this);
        return list;
    }

    private void tra_In(TreeNode<E> x){
        if (x==null) return ;
        tra_In(x.leftChild);
        list.append(x);
        tra_In(x.rightChild);
    }

    public List<TreeNode<E>> traverse_Level(){
        list = new List<TreeNode<E>>();
        Queue<TreeNode<E>> que1 = new Queue<>();
        Queue<TreeNode<E>> que2 = new Queue<>();
        que1.add(this);
        TreeNode<E> node;
        while((!que1.empty())||(!que2.empty())){
            while(!que1.empty()){
                node = que1.dequeue();
                if(node.HasLeftChild()){
                    que2.add(node.leftChild);
                }
                if(node.HasRightChild()){
                    que2.add(node.rightChild);
                }
                list.append(node);
            }
            while(!que2.empty()){
                node = que2.dequeue();
                if(node.HasLeftChild()){
                    que1.add(node.leftChild);
                }
                if(node.HasRightChild()){
                    que1.add(node.rightChild);
                }
                list.append(node);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
