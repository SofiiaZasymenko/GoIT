package module9;

public class MyStack<E> extends ArrayBasedCollection {

    public MyStack() {
        super();
    }

    public void push(E value) {
        resize();
        values[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index > size && index < 0) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        resize();
    }

    public void clear() {
        super.clear();
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) values[size - 1];
    }

    public E pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        E result = (E) values[size - 1];
        values[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            result.append(values[i]);
            if (i > 0) {
                result.append(", ");
            }
        }
        return "[" + result + "]";
    }
}
