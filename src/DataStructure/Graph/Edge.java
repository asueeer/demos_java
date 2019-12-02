package DataStructure.Graph;

public class Edge<Te> {
    public Te data;
    public int weight;
    public String EType;

    public Edge(Te data, int weight) {
        this.data = data;
        this.weight = weight;
        EType = "UNDETERMINED";
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
