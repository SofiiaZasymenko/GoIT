package module9;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(3);
        System.out.println("Expected: 1, actual: " + myLinkedList.size());
        myLinkedList.add(2);
        System.out.println("Expected: 2, actual: " + myLinkedList.get(1));
        myLinkedList.add(5);
        System.out.println("Expected: 3, actual: " + myLinkedList.size());
        myLinkedList.add(9);
        System.out.println("Expected: [3, 2, 5, 9], actual: " + myLinkedList);
        myLinkedList.remove(0);
        System.out.println("Expected: 9, actual: " + myLinkedList.get(2));
        System.out.println("Expected: 3, actual: " + myLinkedList.size());
        System.out.println("Expected: [2, 5, 9], actual: " + myLinkedList);
        myLinkedList.clear();
        System.out.println("Expected: 0, actual: " + myLinkedList.size());
        System.out.println("Expected: [], actual: " + myLinkedList);
    }
}
