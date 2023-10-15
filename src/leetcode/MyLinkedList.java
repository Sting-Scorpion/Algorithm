package leetcode;

public class MyLinkedList {
    public static void main(String[] args){
        mLinkedList ml = new mLinkedList();
        ml.addAtHead(1);
        ml.addAtTail(3);
        ml.addAtIndex(1,2);
        System.out.println(ml.get(1));
        ml.deleteAtIndex(1);
        System.out.println(ml.get(1));

    }
}
class mLinkedList {
    int length;
    Node head;
    Node tail;

    public mLinkedList() {
        length = 0;
        head = new Node();
        tail = head;
    }

    public int get(int index) {
        if(index >= length || index < 0){
            return -1;
        }
        int n = 0;
        Node cur = head.next;
        while(n != index){
            cur = cur.next;
            n++;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val, head.next);
        head.next = node;
        length++;
        if(length == 1) tail = node;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        tail.next = node;
        tail = tail.next;
        length++;
    }

    public void addAtIndex(int index, int val) {
        if(index <= 0){
            addAtHead(val);
        }
        else if(index > length){
            return;
        }
        else{
            Node cur = head.next;
            Node pre = head;
            int n = 0;
            while(n != index){
                pre = pre.next;
                cur = cur.next;
                n++;
            }
            Node tmp = new Node(val, cur);
            pre.next = tmp;
            if(index == length){
                tail = tmp;
            }
            length++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index >= length || index < 0){
            return;
        }
        Node pre = head;
        int n = 0;
        while(n != index){
            pre = pre.next;
            n++;
        }
        pre.next = pre.next.next;
        if(index == length - 1){
            tail = pre;
        }
        length--;
    }

    class Node{
        int val;
        Node next;
        public Node(){}
        public Node(int a){
            val = a;
        }
        public Node(int a, Node n){
            val = a;
            next = n;
        }
    }
}