package Graph;

import java.util.*;

/**
 * Kruskal算法求图的最小生成树
 */
public class Kruskal {
    //要求无向图
    //将边按照权值大小排列后逐条加入
    //若加入次边后存在环，则舍弃

    //一般为并查集操作，此处使用较为简单的另一种方式
    public static class MySets{
        public HashMap<Node, List<Node>> setMap;

        public MySets(Collection<Node> nodes){
            setMap = new HashMap<>();
            for(Node cur : nodes){
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        /**
         * 判断是否在同一集合
         * 此处即判断set是否指向同一地址
         */
        public boolean isSameSet(Node from, Node to){
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        /**
         * 将一边的set中所有元素添加到另一边
         * 然后让自己的set指向另一边的set
         */
        public void union(Node from, Node to){
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for(Node toNode : toSet){
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2){
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        MySets unionFind = new MySets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for(Edge edge : graph.edges){
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while(!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!unionFind.isSameSet(edge.from, edge.to)){
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
