package Graph;

import Graph.structure.Edge;
import Graph.structure.Graph;
import Graph.structure.Node;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floyd {
    public static Map<Node, Map<Node, Integer>> floyd(Graph graph){
        Map<Node, Map<Node, Integer>> re = new HashMap<>();
        // 初始化 map 形式的邻接矩阵
        for(int key : graph.nodes.keySet()){
            Node node = graph.nodes.get(key);
            Map<Node, Integer> distance = new HashMap<>();
            for(Edge edge : node.edges){
                distance.put(edge.to, edge.weight);
            }
            re.put(node, distance);
        }
        // floyd 算法
        // 跳转点需要在最外层枚举
        for(int kn : graph.nodes.keySet()){
            Node k = graph.nodes.get(kn);
            for(int in : graph.nodes.keySet()){
                Node i = graph.nodes.get(in);
                for(int jn : graph.nodes.keySet()){
                    Node j = graph.nodes.get(jn);
                    if(re.get(i).containsKey(k) && re.get(k).containsKey(j)){
                        int dis = Math.min(re.get(i).getOrDefault(j, Integer.MAX_VALUE), re.get(i).get(k) + re.get(k).get(j));
                        re.get(i).put(j, dis);
                    }
                }
            }
        }
        return re;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2, 2}, {2, 3, 1}, {3, 4, 3}, {1, 4, 100}, {2, 4, 50}};
        Graph graph = GraphGenerator.createGraph(edges);
        Map<Node, Map<Node, Integer>> distances = floyd(graph);
        List<Node> key = new java.util.ArrayList<>(distances.keySet().stream().toList());
        key.sort((a, b) -> (a.value - b.value));
        for(Node n : key){
            System.out.print(n.value + "\t-> ");
            Map<Node, Integer> map = distances.get(n);
            for(Node nd : key){
                System.out.print(nd.value + ": ");
                if(map.containsKey(nd)){
                    System.out.print(map.get(nd));
                }
                else{
                    System.out.print("N");
                }
                System.out.print(", ");
            }
            System.out.println();
        }
    }
}
