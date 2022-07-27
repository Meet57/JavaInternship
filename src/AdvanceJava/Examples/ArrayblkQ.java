package AdvanceJava.Examples;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.*;

public class ArrayblkQ {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

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
    }
}
