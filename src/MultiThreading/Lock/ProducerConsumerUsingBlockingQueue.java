package MultiThreading.Lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    BlockingQueue<Integer> q;
    float time;

    Producer(BlockingQueue q, float time) {
        this.q = q;
        this.time = time;
    }

    private void sleep() {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            sleep();
            try {
                System.out.println(Thread.currentThread().getName() + " produced packet number : " + i);
                q.put(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}

class Consumer implements Runnable {
    BlockingQueue<Integer> q;
    int time;

    Consumer(BlockingQueue q, int time) {
        this.q = q;
        this.time = time;
    }

    private void sleep() {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true){
            sleep();
            try {
                System.out.println(Thread.currentThread().getName() + " consumed packet number : " + q.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ProducerConsumerUsingBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> q = new ArrayBlockingQueue(3);

        Thread Producer = new Thread(new Producer(q, 0.1F));
        Thread Consumer1 = new Thread(new Consumer(q, 2));
        Thread Consumer2 = new Thread(new Consumer(q, 2));

        Producer.setName("Parle");
        Consumer1.setName("Shop 1");
        Consumer2.setName("Shop 2");

        Producer.start();
        Consumer1.start();
        Consumer2.start();
    }
}
