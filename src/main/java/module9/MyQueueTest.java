package module9;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.add(5);
        System.out.println("Expected: 1, actual: " + myQueue.size());
        myQueue.add(2);
        System.out.println("Expected: 5, actual: " + myQueue.peek());
        myQueue.add(3);
        System.out.println("Expected: 3, actual: " + myQueue.size());
        myQueue.add(9);
        System.out.println("Expected: [5, 2, 3, 9], actual: " + myQueue);
        myQueue.poll();
        System.out.println("Expected: 3, actual: " + myQueue.size());
        System.out.println("Expected: [2, 3, 9], actual: " + myQueue);
        myQueue.clear();
        System.out.println("Expected: 0, actual: " + myQueue.size());
        System.out.println("Expected: [], actual: " + myQueue);
    }
}
