package Graph;

public class Edge {
    public int weight;
    public Node from;
    public Node to;
    public Edge(int w, Node f, Node t){
        weight = w;
        from = f;
        to = t;
    }
}
