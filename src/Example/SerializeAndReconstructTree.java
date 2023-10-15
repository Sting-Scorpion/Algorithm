package Example;

import java.util.LinkedList;
import java.util.Queue;
import BinaryTree.*;

/*
二叉树的序列化与反序列化
 */
public class SerializeAndReconstructTree {
    /**
     * 树的序列化（先序）
     */
    public static String SerialByPre(TreeNode root){
        //'_'表示一个结点的结束
        //表示节点为空的情况
        if(root == null){
            return "#_";
        }
        String re = root.val + "_";
        re += SerialByPre(root.left);
        re += SerialByPre(root.right);
        return re;
    }

    /**
     * 树的反序列化
     */
    public static TreeNode ReconByPre(String treeStr){
        String[] values = treeStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < values.length; i++){
            queue.add(values[i]);
        }
        return ReconPreOrder(queue);
    }

    /**
     * 反序列化形成树
     */
    public static TreeNode ReconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = ReconPreOrder(queue);
        root.right = ReconPreOrder(queue);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(2);

        String serialized = SerialByPre(root);
        System.out.println("serialized result : " + serialized);
        TreeNode head = ReconByPre(serialized);
        Traversal.preOrderRecur(head);
    }
}
