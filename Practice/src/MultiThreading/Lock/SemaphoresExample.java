package MultiThreading.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
* Here in this example i have tired to implement singleton class and Semaphore concept
* SO the basic reason on semaphore is, whenever you have multiple thread trying to access
* a resource, but the resource can only respond to only 2 threads at a time, there
* semaphores are used
*
* Semaphores are initialized with a int variable, which is the capacity of tje resource to handel thread
* semaphore.acquire() gives you access if you have space. else will put you on wait
* semaphore.release() takes that access back from you
* */

class WorkerClass{

    protected Semaphore watchMan = new Semaphore(2);
    protected int connections = 0;
    protected static WorkerClass instance = null;

    private WorkerClass(){

    }

    public static WorkerClass getInstance() {
        if(instance==null)
            instance = new WorkerClass();
        return instance;
    }

    public void connectForWork(){
        try {
            watchMan.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this){
            connections++;
            System.out.println(Thread.currentThread().getName() + " Working at " + connections + " : " + watchMan.availablePermits());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        watchMan.release();
    }
}

public class SemaphoresExample {
    public static void main(String[] args) {
        WorkerClass wc = WorkerClass.getInstance();

        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    wc.connectForWork();
                }
            });
        }

        es.shutdown();
    }
}
