package module9;

public class MyArrayList<E> extends ArrayBasedCollection implements MyList<E> {

    public MyArrayList() {
        super();
    }

    @Override
    public void add(E value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        resize();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) values[index];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(values[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        return "[" + result + "]";
    }
}
