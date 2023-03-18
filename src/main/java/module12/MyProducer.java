package module12;

public interface MyProducer<T> extends Runnable {
    void produce(T value);

    boolean isUpdated();
}
