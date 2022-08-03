package MultiThreading.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RunnableTimeOut implements Runnable{

    private Lock lock1 = null;
    private Lock lock2 = null;

    public RunnableTimeOut(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int success = 0;

        while(true) {
            int failureCount = 0;
            while(! tryLockBothLocks()) {
                failureCount++;
                System.err.println(threadName + " failed to lock both Locks. " +
                        "Waiting a bit before retrying [" + failureCount + " tries]");
                sleep(100L * ((long) Math.random()));
            }
            if(failureCount > 0) {
                System.out.println(threadName +
                        " succeeded in locking both locks - after " + failureCount + " failures.");
            }
            success++;

            //do the work - now that both locks have been acquired (locked by this thread)
            //unlock
            lock2.unlock();
            lock1.unlock();
            if(success==3) break;
        }
//        System.out.println(threadName + " : "+success);
    }

    private void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean tryLockBothLocks() {
        String threadName = Thread.currentThread().getName();

        try {
            boolean lock1Succeeded = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            if(!lock1Succeeded) {
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted trying to lock Lock 1");
            return false;
        }
        try {
            boolean lock2Succeeded = lock2.tryLock(1000, TimeUnit.MILLISECONDS);
            if(!lock2Succeeded) {
                lock1.unlock();
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted trying to lock Lock 2");
            lock1.unlock();
            return false;
        }

        return true;
    }
}

public class DeadLockPrevention {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Runnable runnable1 = new RunnableTimeOut(lock1, lock2);
        Runnable runnable2 = new RunnableTimeOut(lock2, lock1);

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}
