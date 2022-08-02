package MultiThreading.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private long count = 0;
    private Lock lock = new ReentrantLock();

    public void inc() {
        this.count++;
    }

    public long getCount() {
        return this.count;
    }
}

class MyRunnable implements Runnable {
    Counter c = null;
    Lock lock = new ReentrantLock(true);

    MyRunnable(Counter c) {
        this.c = c;
    }

    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 1000000; i++) {
            c.inc();
        }
        System.out.println(c.getCount());
        lock.unlock();
    }
}

public class LockExample {
    public static void main(String[] args) {
        Counter c = new Counter();
        MyRunnable r = new MyRunnable(c);
        MyRunnable r1 = new MyRunnable(c);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}
