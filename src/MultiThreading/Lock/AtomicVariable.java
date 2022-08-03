package MultiThreading.Lock;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter implements Runnable {
    private volatile int counter;
    private AtomicInteger atomicCounter;

    AtomicCounter() {
        this.counter = 0;
        this.atomicCounter = new AtomicInteger(0);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter++;
            atomicCounter.incrementAndGet();
        }
    }

    public int getCounter() {
        return counter;
    }

    public AtomicInteger getAtomicCounter() {
        return atomicCounter;
    }
}

public class AtomicVariable {
    public static void main(String[] args) throws InterruptedException {
        AtomicCounter a = new AtomicCounter();
        Thread t1 = new Thread(a, "First");
        Thread t2 = new Thread(a, "Second");

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println("Normal Varible : " + a.getCounter());
        System.out.println("Atomic Varible : " + a.getAtomicCounter());
    }
}
