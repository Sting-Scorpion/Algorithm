package Graph;

import Graph.structure.Graph;
import Graph.structure.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 */
public class BFS {
    /**
     * 从node出发的bfs
     * @param node 起始点
     */
    public static void bfs(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>(); // 防止产生环，造成无限循环；题目中用例数量不大时可以换成数组
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.print(cur.value + ", "); // 可替换为一般的处理操作
            for(Node next : cur.nexts){
                if(!set.contains(next)){
                    set.add(next);
                    queue.offer(next);
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
         * {1, 2, 4, 5, 3, 6, 7}
         */
        bfs(head);
    }
}
