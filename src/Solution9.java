public class Solution9 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(5);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(8);
        System.out.println(mergeTwoLists(l1,l2));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(0);

        ListNode tempP = newList;
        while(l1 != null&& l2 != null){
            if(l1.val <= l2.val){
                tempP.next = l1;
                tempP = tempP.next;
                l1 = l1.next;
            } else{
                tempP.next = l2;
                tempP = tempP.next;
                l2 = l2.next;
            }
        }
        if(l1 == null) tempP.next = l2;
        if(l2 == null) tempP.next = l1;
        return newList.next;
    }
}
