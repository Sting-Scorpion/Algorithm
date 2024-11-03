package Graph;

import Graph.structure.Edge;
import Graph.structure.Graph;
import Graph.structure.Node;

public class GraphGenerator {
    /**
     * 把边的数组转化为图的结构（有向图）
     * @param edges N * 3的矩阵，每行的三个元素分别为出点，入点，边的权重
     * @return 图的结构
     */
    public static Graph createGraph(int[][] edges){
        Graph graph = new Graph();
        // 拿到每一条边
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // 点若不存在，则新建
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            Node fromNode = graph.nodes.get(from);

            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node toNode = graph.nodes.get(to);
            // 把这条边创建出来
            Edge newEdge = new Edge(weight, fromNode, toNode);

            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
