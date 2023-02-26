package module9;

public class MyStackTest {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(5);
        System.out.println("Expected: 1, actual: " + myStack.size());
        myStack.push(2);
        System.out.println("Expected: 2, actual: " + myStack.peek());
        System.out.println("Expected: [2, 5], actual: " + myStack);
        myStack.push(3);
        myStack.push(7);
        System.out.println("Expected: 4, actual: " + myStack.size());
        myStack.remove(1);
        System.out.println("Expected: [7, 3, 5], actual: " + myStack);
        System.out.println("Expected: 3, actual: " + myStack.size());
        myStack.push(9);
        System.out.println("Expected: [9, 7, 3, 5], actual: " + myStack);
        System.out.println("Expected: 9, actual: " + myStack.pop());
        System.out.println("Expected: 3, actual: " + myStack.size());
        System.out.println("Expected: [7, 3, 5], actual: " + myStack);
        myStack.clear();
        System.out.println("Expected: 0, actual: " + myStack.size());
        System.out.println("Expected: [], actual: " + myStack);
    }
}
