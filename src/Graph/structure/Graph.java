package Graph.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图的结构描述
 */
public class Graph {
    public Map<Integer, Node> nodes;
    public Set<Edge> edges;

    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
