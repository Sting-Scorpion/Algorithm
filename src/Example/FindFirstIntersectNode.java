package Example;

/*
给定两个可能有环可能无环的单链表
若两链表相交，返回相交的第一个节点
若不相交则返回null
要求时间复杂度O(N)，额外空间复杂度O(1)
 */
public class FindFirstIntersectNode {
    /**
     * 链表节点类
     */
    public static class Node{
        public int value;
        public Node next;
        public Node() {}
        public Node(int data){ value = data; }
    }

    public static Node getIntersectNode(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * @return 返回入环的第一个节点，若无环则返回空
     */
    public static Node getLoopNode(Node head){
        /**
        正确性证明：
            设环外长度为a
            相遇处距离入环节点为b
            环剩余部分长度为c
            则fast走过距离：a + b + n * (b + c)
            slow走过：a + b
            同时，快指针速度为慢指针两倍，故有：
                a + b + n * (b + c) = 2 * (a + b)
            得出：a = (n - 1) * (b + c) + c
            因此，相遇点加n-1圈环长就等于头节点到入环点的长度
         */
        if(head == null || head.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head;
        do{
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while(slow != fast);
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 两个无环链表找相交节点
     * @return 相交则返回该节点，否则返回空
     */
    public static Node noLoop(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0; //两链表长度差
        while(cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        //不相交
        if(cur1 != cur2){
            return null;
        }

        cur1 = n > 0 ? head1 : head2; //cur1指向长链表的头
        cur2 = cur1 == head1 ? head2 : head1; //cur2指向短链表的头
        n = Math.abs(n);
        //长链表先走，直到两个链表长度相等
        while(n != 0){
            n--;
            cur1 = cur1.next;
        }
        //找相同节点
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表找相交节点
     * @return 相交则返回该节点，否则返回空
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){ //有相同入环点，则过程类似无环链表找交点
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while(n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        else{ //无相同入环点，则找是否为公共环
            cur1 = loop1.next;
            while(cur1 != loop1){
                if(cur1 == loop2){
                    return loop1; //loop2也行
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node cur = head1;
        cur.next = new Node(2);
        cur = cur.next;
        Node tmp = new Node(3);
        cur.next = tmp;
        cur = cur.next;
        cur.next = new Node(4);
        cur = cur.next;
        cur.next = new Node(5);
        cur = cur.next;
        cur.next = tmp;

        Node head2 = new Node(8);
        cur = head2;
        cur.next = new Node(7);
        cur = cur.next;
        cur.next = new Node(2);
        cur = cur.next;
        cur.next = tmp;

        Node re = getIntersectNode(head1, head2);
        System.out.println(re.value);
    }
}
