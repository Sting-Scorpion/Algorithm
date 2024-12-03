package Graph;

import Graph.structure.Edge;
import Graph.structure.Graph;
import Graph.structure.Node;

import java.util.ArrayList;
import java.util.List;

public class GraphGenerator {
    /**
     * 把边的数组转化为图的结构（有向图）
     * @param edges N * 3 的矩阵，每行的三个元素分别为出点，入点，边的权重
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

    /**
     * 邻接矩阵建立有向带权图
     * @param edges N * 3 的矩阵，每行的三个元素分别为出点，入点，边的权重
     * @param n 节点数量
     * @return 有向带权图的邻接矩阵表示
     */
    public static int[][] createWeightedDirectedGraphWithAdjacencyMatrix(int[][] edges, int n){
        int[][] graph = new int[n + 1][n + 1];
        for(int[] edge : edges){
            graph[edge[0]][edge[1]] = edge[2];
        }
        return graph;
    }

    /**
     * 邻接矩阵建立有向无权图
     * @param edges N * 2 的矩阵，每行的两个元素分别为出点，入点
     * @param n 节点数量
     * @return 有向无权图的邻接矩阵表示
     */
    public static int[][] createUnweightedDirectedGraphWithAdjacencyMatrix(int[][] edges, int n){
        int[][] graph = new int[n + 1][n + 1];
        for(int[] edge : edges){
            graph[edge[0]][edge[1]] = 1;
        }
        return graph;
    }

    /**
     * 邻接矩阵建立无向带权图
     * @param edges N * 3 的矩阵，每行的三个元素分别为出点，入点，边的权重
     * @param n 节点数量
     * @return 无向带权图的邻接矩阵表示
     */
    public static int[][] createWeightedUndirectedGraphWithAdjacencyMatrix(int[][] edges, int n){
        int[][] graph = new int[n + 1][n + 1];
        for(int[] edge : edges){
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        return graph;
    }

    /**
     * 邻接矩阵建立无向无权图
     * @param edges N * 2 的矩阵，每行的两个元素分别为出点，入点
     * @param n 节点数量
     * @return 无向无权图的邻接矩阵表示
     */
    public static int[][] createUnweightedUndirectedGraphWithAdjacencyMatrix(int[][] edges, int n){
        int[][] graph = new int[n + 1][n + 1];
        for(int[] edge : edges){
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        return graph;
    }

    /**
     * 邻接表建立有向带权图
     * @param edges N * 3 的矩阵，每行的三个元素分别为出点，入点，边的权重
     * @param n 节点数量
     * @return 有向带权图的邻接表表示
     */
    public static List<List<int[]>> createWeightedDirectedGraphWithAdjacencyList(int[][] edges, int n){
        List<List<int[]>> graph = new ArrayList<>();
        // 准备 0 下标，但不一定用（看具体题目节点编号是否从 0 开始）
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
        }
        return graph;
    }

    /**
     * 邻接表建立有向无权图
     * @param edges N * 2 的矩阵，每行的三个元素分别为出点，入点
     * @param n 节点数量
     * @return 有向无权图的邻接表表示
     */
    public static List<List<Integer>> createUnweightedDirectedGraphWithAdjacencyList(int[][] edges, int n){
        List<List<Integer>> graph = new ArrayList<>();
        // 准备 0 下标，但不一定用（看具体题目节点编号是否从 0 开始）
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }

    /**
     * 邻接表建立无向带权图
     * @param edges N * 3 的矩阵，每行的三个元素分别为出点，入点，边的权重
     * @param n 节点数量
     * @return 无向带权图的邻接表表示
     */
    public static List<List<int[]>> createWeightedUndirectedGraphWithAdjacencyList(int[][] edges, int n){
        List<List<int[]>> graph = new ArrayList<>();
        // 准备 0 下标，但不一定用（看具体题目节点编号是否从 0 开始）
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }
        return graph;
    }

    /**
     * 邻接表建立无向无权图
     * @param edges N * 2 的矩阵，每行的三个元素分别为出点，入点
     * @param n 节点数量
     * @return 无向无权图的邻接表表示
     */
    public static List<List<Integer>> createUnweightedUndirectedGraphWithAdjacencyList(int[][] edges, int n){
        List<List<Integer>> graph = new ArrayList<>();
        // 准备 0 下标，但不一定用（看具体题目节点编号是否从 0 开始）
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
