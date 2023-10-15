package BinaryTree;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Traversal {
    //先序遍历（递归）
    public static void preOrderRecur(TreeNode root){
        if(root == null) return;
        System.out.print(root.val + " ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }
    //中序遍历（递归）
    public static void inOrderRecur(TreeNode root){
        if(root == null) return;
        inOrderRecur(root.left);
        System.out.print(root.val + " ");
        inOrderRecur(root.right);
    }
    //后序遍历（递归）
    public static void posOrderRecur(TreeNode root){
        if(root == null) return;
        posOrderRecur(root.left);
        posOrderRecur(root.right);
        System.out.print(root.val + " ");
    }

    //非递归先序遍历
    //出头，入右左。访问顺序则为头左右
    public static void preOrderUnRecur(TreeNode root){
        if(root != null){
            Stack<TreeNode> s = new Stack<>();
            s.push(root);
            while(!s.isEmpty()){
                TreeNode tmp = s.pop();
                if(tmp.right != null) s.push(tmp.right);
                if(tmp.left != null) s.push(tmp.left);
                System.out.print(tmp.val + " ");
            }
        }
        System.out.println();
    }
    //非递归后序遍历
    //s1出头，入左右。传给s2顺序为头右左，则s2访问顺序为左右头
    public static void posOrderUnRecur(TreeNode root){
        if(root != null){
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(root);
            while(!s1.isEmpty()){
                TreeNode tmp = s1.pop();
                s2.push(tmp);
                if(tmp.left != null) s1.push(tmp.left);
                if(tmp.right != null) s1.push(tmp.right);
            }
            while(!s2.isEmpty()){
                TreeNode tmp = s2.pop();
                System.out.print(tmp.val + " ");
            }
        }
        System.out.println();
    }
    //非递归中序遍历
    //每棵子树不断让左节点进栈
    //左节点为空后开始出栈并将出栈的节点的右节点入栈
    //随后重复此过程
    public static void inOrderUnRecur(TreeNode root){
        if(root != null){
            Stack<TreeNode> s = new Stack<>();
            while(!s.isEmpty() || root != null){
                if(root != null){
                    s.push(root);
                    root = root.left;
                }
                else{
                    TreeNode tmp = s.pop();
                    System.out.print(tmp.val + " ");
                    if(tmp.right != null){
                        root = tmp.right;
                    }
                }
            }
        }
        System.out.println();
    }
    //层序遍历
    public static void levelOrder(TreeNode root){
        if(root == null){
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode tmp = q.poll();
            if(tmp.left != null){
                q.offer(tmp.left);
            }
            if(tmp.right != null){
                q.offer(tmp.right);
            }
            System.out.print(tmp.val + " ");
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        /*
             1
           2   3
         4  5    6
         */
        System.out.println("------recursion------");
        System.out.print("pre : ");
        preOrderRecur(root);
        System.out.print("\nin : ");
        inOrderRecur(root);
        System.out.print("\npos : ");
        posOrderRecur(root);

        System.out.println("\n-----unrecursion-----");
        System.out.print("pre : ");
        preOrderUnRecur(root);
        System.out.print("in : ");
        inOrderUnRecur(root);
        System.out.print("pos : ");
        posOrderUnRecur(root);

        System.out.println("--------level--------");
        levelOrder(root);
    }
}
