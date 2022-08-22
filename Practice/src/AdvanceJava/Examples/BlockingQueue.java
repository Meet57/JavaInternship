package AdvanceJava.Examples;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.TimeUnit.*;

public class BlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
        LinkedBlockingQueue<String> lqueue = new LinkedBlockingQueue<String>(5);

        queue.add("Meet");
        queue.add("Janki");
        queue.add("Dhruv");
        queue.add("Priaynshi");
        queue.add("Falgun");
        queue.offer("Parth",1, MILLISECONDS);

        Iterator i = queue.iterator();
        while (i.hasNext()){
            System.out.print(i.next()+" ");
        }

        System.out.println();
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.remainingCapacity());

        System.out.println("Linked Blocking Queue");
        lqueue.addAll(queue);
        lqueue.forEach(ele->{
            System.out.print(ele+" ");
        });
//        LinkedBlockingDeque
    }
}
