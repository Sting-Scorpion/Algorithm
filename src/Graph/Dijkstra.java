package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dijkstra {
    /**
     * @return 从head出发到所有点的最小距离，若不存在某点说明无法到达
     */
    public static HashMap<Node, Integer> dijkstra(Node head){
        //key：从head出发到达key节点
        //value：从head到key的最小距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while(minNode != null){
            int distance = distanceMap.get(minNode);
            for(Edge edge : minNode.edges){
                Node toNode = edge.to;
                if(!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode, distance + edge.weight);
                }
                distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

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
}
