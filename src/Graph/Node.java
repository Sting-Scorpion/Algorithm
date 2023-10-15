package Graph;

import java.util.ArrayList;

public class Node {
    public int value;
    public int in;  //入度
    public int out; //出度
    public ArrayList<Node> nexts; //直接连通的点
    public ArrayList<Edge> edges; //连接该点的边
    public Node(int val){
        value = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
