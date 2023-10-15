package Example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
找到二叉树最大宽度
 */
public class MaxWidthLevel {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(){}
        public TreeNode(int data){ val = data; }
    }

    public static int levelOrder(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        q.offer(root);
        levelMap.put(root, 1);

        int curLevel = 1;
        int curWidth = 0;
        int max = 1;

        while(!q.isEmpty()){
            TreeNode tmp = q.poll();
            int curNodeLevel = levelMap.get(tmp);
            if(curNodeLevel == curLevel){
                curWidth++;
            }
            else{
                max = Math.max(max, curWidth);
                curLevel++;
                curWidth = 1;
            }

            if(tmp.left != null){
                q.offer(tmp.left);
                levelMap.put(tmp.left, curLevel + 1);
            }
            if(tmp.right != null){
                q.offer(tmp.right);
                levelMap.put(tmp.right, curLevel + 1);
            }
        }
        max = Math.max(max, curWidth);
        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(6);

        int re = levelOrder(root);
        System.out.println(re);
    }
}
