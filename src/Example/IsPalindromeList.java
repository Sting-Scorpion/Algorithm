package Example;

import java.util.Stack;

/*
判断是否是回文串
 */
public class IsPalindromeList {
    public static class Node{
        public int value;
        public Node next;
        public Node(){ }
        public Node(int data){
            value = data;
        }
    }

    public static boolean isPalindromeList1(Node head){
        Stack<Node> s = new Stack<>();
        Node fast = head;
        while(fast != null){
            s.push(fast);
            fast = fast.next;
        }
        while(head != null){
            if(head.value != s.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindromeList2(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node slow = head.next;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> s = new Stack<>();
        while(slow != null){
            s.push(slow);
            slow = slow.next;
        }
        while(!s.isEmpty()){
            if(head.value != s.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindromeList3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node slow = head;
        Node fast = head;
        //找中点
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        Node tmp = null;
        while(fast != null){
            tmp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = tmp;
        }
        tmp = slow;
        fast = head;
        boolean re = true;
        while(fast != null && slow != null){
            if(fast.value != slow.value){
                re = false;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        //将链表修改回原始状态
        slow = tmp.next;
        tmp.next = null;
        while(slow != null){
            fast = slow.next;
            slow.next = tmp;
            tmp = slow;
            slow = fast;
        }
        return re;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node cur = head;
        cur.next = new Node(2);
        cur = cur.next;
        cur.next = new Node(3);
        cur = cur.next;
        cur.next = new Node(2);
        cur = cur.next;
        cur.next = new Node(1);
        cur = cur.next;
        System.out.println(isPalindromeList3(head));
    }
}
