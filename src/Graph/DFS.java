package Graph;

import Graph.structure.Graph;
import Graph.structure.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 图的深度优先遍历
 */
public class DFS {
    /**
     * 从node出发的dfs
     * @param node 起始点
     */
    public static void dfs(Node node){
        if(node == null){
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> set = new HashSet<>(); // 记录已访问的
        stack.push(node);
        set.add(node);
        System.out.print(node.value + ", ");
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next : cur.nexts){
                if(!set.contains(next)){
                    stack.push(cur); // 该点的邻居还未访问完，继续入栈
                    stack.push(next); // 未访问过的邻居，入栈
                    set.add(next);
                    System.out.print(next.value + ", "); // 可替换为一般的处理操作
                    break; // 不管横向宽度上的元素，返回while循环
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 2}, {1, 4, 1}, {2, 4, 3},
                {2, 5, 10}, {3, 1, 4}, {3, 6, 5},
                {4, 3, 2}, {4, 5, 2}, {4, 6, 8},
                {4, 7, 4}, {5, 7, 6}, {7, 6, 1}};
        Graph graph = GraphGenerator.createGraph(matrix);
        // 从节点1开始
        Node head = graph.nodes.get(1);
        /*
         * {1, 2, 4, 3, 6, 5, 7}
         */
        dfs(head);
    }
}
