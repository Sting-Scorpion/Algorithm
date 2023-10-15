package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 */
public class BFS {
    //从node出发的bfs
    public static void bfs(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>(); //防止产生环，造成无限循环；题目中用例数量不大时可以换成数组
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value); //可替换为一般的处理操作
            for(Node next : cur.nexts){
                if(!set.contains(next)){
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
    }
}
