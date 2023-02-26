package module9;

public interface MyList<E> {

    void add(E value);

    void remove(int index);

    void clear();

    int size();

    E get(int index);
}
