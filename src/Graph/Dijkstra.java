package Graph;

import Graph.structure.Edge;
import Graph.structure.Graph;
import Graph.structure.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dijkstra {
    /**
     * dijkstra算法计算最短路径
     * @param head 给定的出发点
     * @return 从head出发到所有点的最小距离，若不存在某点说明无法到达
     */
    public static Map<Node, Integer> dijkstra(Node head){
        HashMap<Node, Integer> re = new HashMap<>();
        re.put(head, 0);

        HashSet<Node> selectedNodes = new HashSet<>(); // 已经跳转过的点
        Node minNode = getMinDistanceAndUnselectedNode(re, selectedNodes); // 跳转点
        while(minNode != null){
            int distance = re.get(minNode); // 起始点到跳转点的距离（已有距离）
            // 更新起始点到和跳转点直接相连的点的距离
            for(Edge edge : minNode.edges){
                Node toNode = edge.to;
                // 原先没和起始点连接的点
                if(!re.containsKey(toNode)){
                    re.put(toNode, distance + edge.weight); // 和起始点连上了，加入 map 中
                }
                // 已经能和起始点连上的点
                else{
                    re.put(toNode, Math.min(re.get(toNode), distance + edge.weight)); // 更新最小距离（若有）
                }
            }
            selectedNodes.add(minNode); // 放入跳转完的集合中
            minNode = getMinDistanceAndUnselectedNode(re, selectedNodes); // 拿到下一个跳转点
        }
        return re;
    }

    /**
     * 优化的Dijkstra算法，使用加强堆
     * @param head 给定的出发点
     * @param size 图中点的数量（堆的最大容量）
     * @return 从head出发到所有点的最小距离，若不存在某点说明无法到达
     */
    public static Map<Node, Integer> reinforcedDijkstra(Node head, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        Map<Node, Integer> re = new HashMap<>();
        while(!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for(Edge edge : cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            re.put(cur, distance);
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
        Map<Node, Integer> distances = reinforcedDijkstra(head, graph.nodes.size());
//        for(Node node : distances.keySet()){
//            System.out.print(node.value + ": " + distances.get(node) + ", ");
//        }
        int i = 0;
        int[][] distance = new int[7][2];
        for(Node node : distances.keySet()){
            distance[i][0] = node.value;
            distance[i][1] = distances.get(node);
            i++;
        }
        Arrays.sort(distance, (a, b) -> (a[0] - b[0]));
        for(int[] kv : distance){
            System.out.println(kv[0] + ": " + kv[1]);
        }
    }

    /**
     * 获取到起始点距离最小且未被访问过的跳转点。
     * （每次都要重新全部遍历，效率很低）
     * @param distanceMap 已有的点到起始点的距离表
     * @param selectedNodes 已访问过的跳转点集合
     * @return 满足要求的跳转点
     */
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap,
                                                        HashSet<Node> selectedNodes){
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for(Map.Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            if(!selectedNodes.contains(node) && distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    /**
     * 加强堆
     */
    static class NodeHeap{
        private Node[] nodes; // 堆
        private Map<Node, Integer> heapIndexMap; // 每个点在堆上的位置
        private Map<Node, Integer> distanceMap; // 每个点到起始点的距离
        private int size; // 堆的大小

        public NodeHeap() {
        }

        public NodeHeap(int size){
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        /**
         * 将点与新计算出的和初始点的距离加入堆中。
         * 可能的三种情况：新增点、更新已在堆上的点、忽略。
         * @param node 目标点
         * @param distance 起始点到目标点的距离
         */
        void addOrUpdateOrIgnore(Node node, int distance){
            // 在堆上，更新堆
            if(inHead(node)){
                distanceMap.put(node, Math.min(distanceMap.get(node), distance)); // 更新最短距离
                insertHeapify(node, heapIndexMap.get(node)); // 更新在堆上的位置
            }
            // 未进入过堆，新增数据
            if(!hasEntered(node)){
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
            // 进入过堆且已经出堆，则忽略
        }

        /**
         * 出堆，并更新堆
         * @return 记录堆顶元素信息的数据结构
         */
        NodeRecord pop(){
            NodeRecord re = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1); // 出堆，但不清除记录，将其在堆中下标记作 -1
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return re;
        }

        /**
         * 堆是否空了
         * @return 堆是否为空
         */
        boolean isEmpty(){
            return size == 0;
        }

        /**
         * 在堆最后插入数据并更新堆的位置
         * @param node 更新的点
         * @param index 在堆中的位置
         */
        private void insertHeapify(Node node, int index){
            while(distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])){
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 目标元素向下更新位置
         * @param index 目标元素在堆中的位置（一般是堆顶）
         * @param size 堆的最后一个元素位置（堆的大小）
         */
        private void heapify(int index, int size){
            int left = index * 2 + 1;
            while(left < size){
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if(smallest == index){
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        /**
         * 只要表中存在记录，则已经进入过表
         * @param node 待检测的点
         * @return 是否进入过表中
         */
        private boolean hasEntered(Node node){
            return heapIndexMap.containsKey(node);
        }

        /**
         * 表中存在记录且值（在堆上的位置）不为 -1，则表示还在堆上
         * @param node 待检测的点
         * @return 是否还在堆上
         */
        private boolean inHead(Node node){
            return hasEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2){
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    /**
     * 节点记录类，包含节点及起始点到该点的距离
     */
    static class NodeRecord{
        Node node;
        int distance;

        public NodeRecord(Node node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }
}
