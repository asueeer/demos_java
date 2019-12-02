package DataStructure.Graph;

public class Vertex<Tv> {
    Tv data;
    int inDegree, outDegree;
    String status;

    int dTime, fTime;
    int parent;
    int priority;

    static int INT_MAX = 10;

    public Vertex(Tv data) {
        this.data = data;
        this.inDegree = 0;
        this.outDegree = 0;
        this.status = "UNDISCOVERED";
        this.dTime = -1;
        this.fTime = -1;
        this.parent = -1;
        this.parent = INT_MAX;
    }

}