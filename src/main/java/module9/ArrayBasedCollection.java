package module9;

public abstract class ArrayBasedCollection {
    private final static int DEFAULT_SIZE = 16;
    protected Object[] values;
    protected int size;

    public ArrayBasedCollection() {
        values = new Object[DEFAULT_SIZE];
    }

    protected void resize() {
        // increases the array if the array is full
        // decreases the array if it is filled more than default size and less than (array / 4)
        if (size >= values.length || (size >= DEFAULT_SIZE && size < values.length / 4)) {
            Object[] newValues = new Object[size * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    protected void clear() {
        for (int i = 0; i < size; i++) {
            values[i] = null;
        }
        size = 0;
    }
}
