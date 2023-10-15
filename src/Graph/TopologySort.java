package Graph;

import java.util.*;

public class TopologySort {
    public static List<Node> sortedTopology(Graph graph){
        //<某一个Node, 剩余的入度>
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for(Node node : graph.nodes.values()){
            inMap.put(node, node.in);
            if(node.in == 0){
                zeroInQueue.offer(node);
            }
        }
        //拓扑排序的结果，依次加入队列
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for(Node next : cur.nexts){
                inMap.put(next, inMap.get(next) - 1);
                if(inMap.get(next) == 0){
                    zeroInQueue.offer(next);
                }
            }
        }
        return result;
    }
}
