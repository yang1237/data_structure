package com.yang.linkedlist;

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(0);//要返回的链表(头节点是空节点),所以返回res.next
        ListNode cur = res; //辅助返回的链表
        while (l1 != null || l2 != null || carry != 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int num = num1 + num2 + carry;
            //判断进位，进位用于下一个node的值的计算
            carry = num / 10;
            //处理num放到listNode
            cur.next = new ListNode(num % 10);
            //保存新加入链表的节点，以便于再次向链表中添加节点
            cur = cur.next;
            //设置在处理的被操作链表
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return res.next;
    }
}