package module9;

public class MyHashMap<K, V> extends ArrayBasedCollection {

    public MyHashMap() {
        super();
    }

    public void put(K key, V value) {
        resize();
        // the index of the element in array is calculated by key's hashcode
        int index = index(key);
        Node newNode = new Node(key, value);
        // check if the node exists in the table by index
        if (values[index] == null) {
            // this place in array is empty - put the new node here
            values[index] = newNode;
        } else {
            // this place in array is not empty - search for the same key
            Node previousNode = null;
            Node currentNode = (Node) values[index];
            // looping through the node chain by this index to find the node with the same key
            while (currentNode != null) {
                // check if the current node's key is the same as in the new element
                if (currentNode.key.equals(key)) {
                    // there is a node with the same key in the chain - replace its value and return from method
                    // since we are just replacing the value, no need to increment the size
                    currentNode.value = value;
                    return;
                }
                // iterate to the next node in the chain
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            // if the key not found, add new node in the end of the chain
            if (previousNode != null) {
                previousNode.next = newNode;
            }
        }
        // increment the size after adding new key-value node
        size++;
    }

    public void remove(K key) {
        // the index of the element in array is calculated by key's hashcode
        int index = index(key);
        Node previousNode = null;
        // check if the node exists in the table by index
        Node currentNode = (Node) values[index];
        // looping through the node chain by this index to find the node with the same key
        // if the currentNode is null, the method ends without actions
        while (currentNode != null) {
            // check if the current node's key is the same as the one to delete
            if (currentNode.key.equals(key)) {
                // there is a node with the same key in the chain
                // check if this node is the first one in the chain
                if (previousNode == null) {
                    // this node is the first one in the chain - replace it with the next one in the chain
                    currentNode = currentNode.next;
                    values[index] = currentNode;
                } else {
                    // this node is not the first one - replace it with the next one in the chain
                    previousNode.next = currentNode.next;
                }
                // decrement the size after removing the node from the chain, try to resize the table and return from method
                size--;
                resize();
                return;
            }
            // iterate to the next node in the chain
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public V get(K key) {
        // the index of the element in array is calculated by key's hashcode
        int index = index(key);
        // check if the node exists in the table by index
        Node node = (Node) values[index];
        // looping through the node chain by this index to find the node with the same key
        while (node != null) {
            // check if the current node's key is the same as the one to find
            if (node.key.equals(key)) {
                // there is a node with the same key in the chain - return its value
                return node.value;
            }
            // iterate to the next node in the chain
            node = node.next;
        }
        // if the node is null or the needed key is not found in the node chain, return null
        return null;
    }

    public void clear() {
        super.clear();
    }

    public int size() {
        return size;
    }

    private int index(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % values.length);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            Node node = (Node) values[i];
            while (node != null) {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append(node.key).append(" = ").append(node.value);
                node = node.next;
            }
        }
        return "[" + result + "]";
    }

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
