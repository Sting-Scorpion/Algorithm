package Graph.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 点的结构描述
 */
public class Node {
    public int value;
    public int in;  //入度
    public int out; //出度
    public List<Node> nexts; //直接连通的点
    public List<Edge> edges; //连接该点的边

    public Node(int val){
        value = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
