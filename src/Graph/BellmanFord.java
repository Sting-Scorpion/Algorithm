package Graph;

import Graph.structure.Edge;
import Graph.structure.Graph;
import Graph.structure.Node;

import java.util.*;

public class BellmanFord {
    /**
     * bellman-ford 算法计算最短路径
     * @param head 给定的起始点
     * @param edges 边的集合
     * @param size 顶点数量
     * @return 从head出发到所有点的最小距离
     */
    public static Map<Node, Integer> bellmanFord(Node head, Set<Edge> edges, int size){
        Map<Node, Integer> re = new HashMap<>();
        re.put(head, 0);
        // 迭代
        for(int i = 1; i < size; i++){
            for(Edge edge : edges){
                if(re.containsKey(edge.from) && (re.get(edge.from) + edge.weight < re.getOrDefault(edge.to, Integer.MAX_VALUE))){
                    re.put(edge.to, re.get(edge.from) + edge.weight);
                }
            }
        }
        // 判断负环
        for(Edge edge : edges){
            // 还未收敛
            if(re.containsKey(edge.from) && (re.get(edge.from) + edge.weight < re.getOrDefault(edge.to, Integer.MAX_VALUE))){
                throw new RuntimeException("存在负环");
            }
        }
        return re;
    }

    /**
     * 优化后的算法：SPFA 算法
     * @param head 给定的起始点
     * @param size 顶点数量
     * @return 从head出发到所有点的最小距离
     */
    public static Map<Node, Integer> spfa(Node head, int size){
        Map<Node, Integer> re = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>(); // 上一轮松弛过的点
        Map<Node, Integer> updateTimes = new HashMap<>(); // 点更新的次数
        Set<Node> inQueue = new HashSet<>(); // 在队列 q 中的点

        re.put(head, 0);
        q.add(head);
        updateTimes.put(head, 1);
        inQueue.add(head);

        while(!q.isEmpty()){
            Node tmp = q.poll();
            // 遍历该点出发的边，看是否可以对相邻的点进行松弛操作
            for(Edge edge : tmp.edges){
                if(re.get(edge.from) + edge.weight < re.getOrDefault(edge.to, Integer.MAX_VALUE)){
                    re.put(edge.to, re.get(edge.from) + edge.weight);
                    // 是否可以入队
                    if(!inQueue.contains(edge.to)){
                        q.add(edge.to);
                        inQueue.add(edge.to);
                        updateTimes.put(edge.to, updateTimes.getOrDefault(edge.to, 0) + 1); // 更新次数 + 1
                        // 更新次数超过图中点的数量，则说明存在负环
                        if(updateTimes.get(edge.to) > size){
                            throw new RuntimeException("存在负环");
                        }
                    }
                }
            }
            inQueue.remove(tmp);
        }
        return re;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 2}, {1, 4, 1}, {2, 4, 3},
                {2, 5, 10}, {3, 1, 4}, {3, 6, 5},
                {4, 3, 2}, {4, 5, 2}, {4, 6, 8},
                {4, 7, 4}, {5, 7, 6}, {7, 6, 1}};
        Graph graph = GraphGenerator.createGraph(matrix);
        // 从节点1开始
        Node head = graph.nodes.get(1);
//        Map<Node, Integer> distances = bellmanFord(head, graph.edges, graph.nodes.size());
        Map<Node, Integer> distances = spfa(head, graph.nodes.size());

        int i = 0;
        int[][] distance = new int[7][2];
        for(Node node : distances.keySet()){
            distance[i][0] = node.value;
            distance[i][1] = distances.get(node);
            i++;
        }
        Arrays.sort(distance, Comparator.comparingInt(a -> a[0]));
        /*
         * {1: 0, 2: 2, 3: 3, 4: 1, 5: 3, 6: 6, 7: 5}
         */
        for(int[] kv : distance){
            System.out.println(kv[0] + ": " + kv[1]);
        }

        // 有负环的测试（会报错）
//        int[][] cir = {{1, 2, 3}, {2, 3, -4}, {3, 4, 2}, {4, 2, 1}};
//        Graph g2 = GraphGenerator.createGraph(cir);
//        Node h2 = g2.nodes.get(1);
//        Map<Node, Integer> dis = bellmanFord(h2, g2.edges, g2.nodes.size());
    }
}
