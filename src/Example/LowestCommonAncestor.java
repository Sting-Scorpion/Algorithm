package Example;

import java.util.HashMap;
import java.util.HashSet;

/*
给定一棵树的根节点和两个包含在树内的节点
返回这两个节点的最低公共祖先节点
 */
public class LowestCommonAncestor {
    /**
     * 树节点类
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int val){
            value = val;
        }
    }

    public static Node lowestCommonAncestor(Node root, Node o1, Node o2){
        //基础情况
        if(root == null || root == o1 || root == o2){
            return root;
        }
        //左边找o1或o2
        Node left = lowestCommonAncestor(root.left, o1, o2);
        //右边找o1或o2
        Node right = lowestCommonAncestor(root.right, o1, o2);
        //左和右都找到，说明分散在两边，返回自己
        if(left != null && right != null){
            return root;
        }
        //只有一边有结果，则返回有结果的一边的结果
        return left != null ? left : right;
    }
    
    /**
     * o1, o2一定属于root为根的树
     * @return 返回最低的公共祖先
     */
    public static Node lca(Node root, Node o1, Node o2){
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(root, root);
        process(root, fatherMap);
        HashSet<Node> set1 = new HashSet<>();
        set1.add(o1);
        Node cur = o1;
        //记录o1一路向上到根节点时经过的结点
        while(cur != fatherMap.get(cur)){
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(root);
        //若o2不在set中，则o2向上往根节点走，直到走到o1经过的节点
        while(!set1.contains(o2)){
            o2 = fatherMap.get(o2);
        }
        return o2;
    }

    /**
     * 将每个结点和其父节点的关系添加到map中
     */
    public static void process(Node root, HashMap<Node, Node> fatherMap){
        if(root == null) return;
        fatherMap.put(root.left, root);
        fatherMap.put(root.right, root);
        process(root.left, fatherMap);
        process(root.right, fatherMap);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node tar1 = new Node(8);
        Node tar2 = new Node(10);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.right = new Node(9);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = tar1;
        root.right.left.right = tar2;

        Node tar = lowestCommonAncestor(root, tar1, tar2);
        System.out.println(tar.value);
    }
}
