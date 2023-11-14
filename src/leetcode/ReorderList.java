package leetcode;

import java.util.List;
import java.util.Stack;

public class ReorderList {
    /**
     * 单链表 L 表示为：
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * @param head 单链表头节点
     */
    public void reorderList(ListNode head) {
        if(head.next == null || head.next.next == null) return;
        Stack<ListNode> tmp = new Stack<>();
        ListNode t = head;
        while(t != null){
            tmp.push(t);
            t = t.next;
        }
        t = head;
        ListNode insert;
        while(tmp.peek() != t){
            insert = tmp.pop();
            insert.next = t.next;
            t.next = insert;
            t = insert.next;
            if(t.next == t){
                break;
            }
        }
        t.next = null;
    }

    public static void main(String[] args) {
        ReorderList solution = new ReorderList();
        // 1 -> 2 -> 3 -> 4 -> 5
        ListNode a5 = new ListNode(5);
        ListNode a4 = new ListNode(4, a5);
        ListNode a3 = new ListNode(3, a4);
        ListNode a2 = new ListNode(2, a3);
        ListNode a1 = new ListNode(1, a2);
        solution.reorderList(a1); // 1 -> 5 -> 2 -> 4 -> 3

        ListNode head = a1;
        while(head != null){
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }
}
