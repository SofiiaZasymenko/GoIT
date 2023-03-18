package module12;

public abstract class MyAbstractProducer<T> implements MyProducer<T> {
    private T value;
    private boolean updated = false;

    @Override
    public void produce(T value) {
        this.value = value;
        updated = true;
    }

    @Override
    public boolean isUpdated() {
        return updated;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (updated) {
                    updated = false;
                    action(value);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void action(T n) throws Exception;
}