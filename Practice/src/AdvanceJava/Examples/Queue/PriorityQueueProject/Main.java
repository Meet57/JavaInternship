package AdvanceJava.Examples.Queue.PriorityQueueProject;
import java.util.ArrayList;
import java.util.Optional;
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

        ArrayList<Bug> a = new ArrayList<>();
        a.add(new Bug("NullPointer",1));
        a.add(new Bug("Arithmetic",5));
        a.add(new Bug("Exception",99));

        a.sort((x,y)->y.getPriority()-x.getPriority());

        System.out.println(a);
    }
}
