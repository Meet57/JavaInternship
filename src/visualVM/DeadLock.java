package visualVM;

/*
 * Deadlock
 * How it can be avoided ?
 *      Avoid nested locks
 *      Avoid unnecessary locks
 *      Using Thread Joining
 * */

/*
 * To see all processes running in command line
 * jps -l -m
 * Then to see the Thread Dump
 * jstack ID
 *
 * jmap -dump:live,format=b,file=/tmp/dump.hprof <pid>

 * RMI Thread : Remote Method Invocation
 *
 * https://fastthread.io/
 * https://heaphero.io/
 * */

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        String s1 = "Meet";
        String s2 = "Patel";

        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    System.out.println(s1 + " Taken by t1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (s2) {
                        System.out.println(s2 + " Taken by t1");
                    }
                }
            }
        };

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    System.out.println(s2 + " Taken by t1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (s1) {
                        System.out.println(s1 + " Taken by t1");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
