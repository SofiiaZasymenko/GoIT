package module12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class HomeworkTask2 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Pair<Integer, String>> queue = new LinkedBlockingQueue<>();

        MyProducer<Integer> fizzProducer = new MyAbstractProducer<>() {
            @Override
            public void action(Integer n) throws InterruptedException {
                if (n % 3 == 0) {
                    queue.put(new Pair<>(n, "fizz"));
                }
            }
        };
        MyProducer<Integer> buzzProducer = new MyAbstractProducer<>() {
            @Override
            public void action(Integer n) throws InterruptedException {
                if (n % 5 == 0) {
                    queue.put(new Pair<>(n, "buzz"));
                }
            }
        };
        MyProducer<Integer> fizzBuzzProducer = new MyAbstractProducer<>() {
            @Override
            public void action(Integer n) throws InterruptedException {
                if (n % 3 == 0 && n % 5 == 0) {
                    queue.put(new Pair<>(n, "fizzbuzz"));
                }
            }
        };
        MyProducer<Integer> numberProducer = new MyAbstractProducer<>() {
            @Override
            public void action(Integer n) throws InterruptedException {
                if (n % 3 != 0 && n % 5 != 0) {
                    queue.put(new Pair<>(n, String.valueOf(n)));
                }
            }
        };

        Runnable consumer = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!queue.isEmpty()) {
                    System.out.println(queue.poll());
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<?>> tasks = new ArrayList<>();
        tasks.add(executor.submit(fizzProducer));
        tasks.add(executor.submit(buzzProducer));
        tasks.add(executor.submit(fizzBuzzProducer));
        tasks.add(executor.submit(numberProducer));
        tasks.add(executor.submit(consumer));

        for (int i = 1; i <= 20; i++) {
            fizzProducer.produce(i);
            buzzProducer.produce(i);
            fizzBuzzProducer.produce(i);
            numberProducer.produce(i);
            while (fizzProducer.isUpdated() || buzzProducer.isUpdated() || numberProducer.isUpdated() || fizzBuzzProducer.isUpdated()) {
                Thread.sleep(200);
            }
        }

        Thread.sleep(1000);
        tasks.forEach(task -> task.cancel(true));
        executor.shutdown();
    }

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair<" + key + ", " + value + '>';
        }
    }
}