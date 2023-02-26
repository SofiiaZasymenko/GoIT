package module9;

public class MyQueue<E> {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private class Node {
        E value;
        Node next;

        Node(E value) {
            this.value = value;
        }
    }
    public void add(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        return head.value;
    }

    public E poll() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        Node result = head;
        head = head.next;
        size--;
        if (head == null) {
            tail = null;
        }
        return result.value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.value);
            if (i < size - 1) {
                result.append(", ");
            }
            current = current.next;
        }
        return "[" + result + "]";
    }
}


