package DataStructure.Graph;

import java.util.ArrayList;

public class  Graph <Tv, Te> { // 基于邻接矩阵实现的图
    private int n = 0; // 顶点总数
    private int e = 0; //
    public ArrayList<Vertex<Tv>> V = new ArrayList<Vertex<Tv>>(); // 顶点集
    public ArrayList<ArrayList<Edge<Te>>> E = new ArrayList<ArrayList<Edge<Te>>>(); // 边集

    void reset(){
        for (int i = 0; i < n; i++) {
            V.get(i).status = "UNDISCOVERED";
            V.get(i).dTime = -1;
            V.get(i).fTime = -1;
            V.get(i).parent = -1;
            V.get(i).priority = Vertex.INT_MAX;
            for (int j = 0; j < n; j++) {
                if(exists(i,j)){
                    E.get(i).get(j).EType = "UNDETERMINED";
                }
            }
        }
    }

    // 顶点基本操作
    public Tv vertex(int i){
        return V.get(i).data;
    }

    public int inDegree(int i){
        return V.get(i).inDegree;
    }

    public int outDegree(int i){
        return V.get(i).outDegree;
    }

    public int firstNbr(int i){
        return nextNbr(i, n);
    }

    public int nextNbr(int i, int j){ // 顶点i，相对于顶点j的下一个邻接顶点(貌似是从后往前计算)
        for (int k = j; k > -1; k--) {
            if(exists(i,k)){
                return k;
            }
        }
        return -1;
    }

    public String VertexStatus(int i){
        return V.get(i).status;
    }

    public int dTime(int i){
        return V.get(i).dTime;
    }

    public int fTime(int i){
        return V.get(i).fTime;
    }

    public int parent(int i){
        return V.get(i).parent;
    }

    public int priority(int i){
        return V.get(i).priority;
    }

    // 顶点动态操作
    public int insert(Tv vertexData){ // 插入顶点，返回编号
        for (int j = 0; j < n; j++) { // 各顶点预留一条潜在的关联边
            E.get(j).add(null);
        }
        n++;
        E.add(new ArrayList<Edge<Te>>());
        for (int j = 0; j < n; j++) {
            E.get(E.size()-1).add(null); // 创建新顶点对应的边向量
        }

        V.add(new Vertex<Tv>(vertexData));
        return V.size()-1;
    }

    public Tv remove(int i){ // 十分耗时
        ArrayList<ArrayList<Edge<Te>>> E_new = new ArrayList<ArrayList<Edge<Te>>>();
        for (int j = 0; j < n; j++) {
            if (j!=i){
                E_new.add(new ArrayList<Edge<Te>>());
                for (int k = 0; k < n; k++) {
                    if(k!=i){
                        if(exists(j,k)){
                            E_new.get(E_new.size()-1).add(E.get(j).get(k));
                        }
                        else{
                            E_new.get(E_new.size()-1).add(null);
                        }
                    }
                }
            }
        }
        E = E_new;

        Tv vBackup = vertex(i);
        return vBackup;
    }

    // 边确认操作
    public boolean exists(int i, int j){ //确认边（i，j）是否存在
        if ((i>=0&&i<n)&&(j>=0&&j<n)){
            if(E.get(i).get(j)!=null){
                return true;
            }
        }
        return false;
    }

    public String type(int i, int j){
        return E.get(i).get(j).EType;
    }

    public Te edge(int i, int j){
        return E.get(i).get(j).data;
    }

    public int weight(int i, int j){
        return E.get(i).get(j).weight;
    }

    // 边的动态操作
    public void insert(Te edgeData, int w, int i, int j){
        if(exists(i,j)) return; // 确保边尚不存在

        E.get(i).set(j, new Edge<Te>(edgeData,w)); // 创建新边
        e++;
        V.get(i).outDegree++;
        V.get(j).inDegree++;
    }

    public Te remove(int i, int j){
        Te eBackup = edge(i, j);
        E.get(i).set(j,null);
        e--;
        V.get(i).inDegree--;
        return eBackup;
    }
}
