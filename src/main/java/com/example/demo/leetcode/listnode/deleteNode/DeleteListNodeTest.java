package com.example.demo.leetcode.listnode.deleteNode;

import com.example.demo.leetcode.listnode.ListNode;

/**
 * @author yongqiang.zhu
 * @date 2020/4/26 23:32
 * 删除链表中的一个节点
 */
public class DeleteListNodeTest {

	public static void main(String[] args) {
		ListNode head1 = new ListNode(4);
		ListNode head2 = new ListNode(5);
		ListNode head3 = new ListNode(1);
		ListNode head4 = new ListNode(9);
		head1.next =head2;
		head2.next = head3;
		head3.next = head4;
		head4.next = null;

		deleteNode(head1, 1);


	}
	public static ListNode deleteNode(ListNode head, int val) {
		if(head ==null) {
			return null;
		}
		if(head.val == val) {
			return head.next;
		}
		ListNode cur = head;
		// 总结：head指向cur，然后处理cur（子链），等于1的时候，删除掉1节点，head也随之变化
		while(cur.next !=null && cur.next.val !=val){
			cur = cur.next;
		}
		if (cur.next !=null) {
			cur.next = cur.next.next;
		}
		return head;
	}
}
