package PriorityQueueProject;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Bug> q = new PriorityQueue<>(new BugComparator());
        q.add(new Bug("NullPointer",1));
        q.add(new Bug("Arithmetic",5));
        q.add(new Bug("Exception",99));

        System.out.println(new Bug("Meet",1).hashCode());

        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
}
