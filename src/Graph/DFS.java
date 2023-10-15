package Graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 图的深度优先遍历
 */
public class DFS {
    //从node出发的bfs
    public static void dfs(Node node){
        if(node == null){
            return;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next : cur.nexts){
                if(!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break; //不管横向宽度上的元素，返回while循环
                }
            }
        }
    }
}
