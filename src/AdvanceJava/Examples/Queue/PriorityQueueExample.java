package AdvanceJava.Examples.Queue;

import java.util.PriorityQueue;

class Meet implements Comparable<Meet>{
    private int i;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    Meet(String name,int i){
        this.i = i;
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(Meet o) {
        if(getI() > o.getI()){
            return -1;
        }
        if(getI() < o.getI()){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }
}

public class PriorityQueueExample {
    public static void main(String[] args) {
        Meet b = new Meet("Shekhar",6);
        Meet a = new Meet("Meet",2);
        Meet c = new Meet("Nilesh",11);

        PriorityQueue<Meet> pq = new PriorityQueue<>();

        pq.add(a);
        pq.add(b);
        pq.add(c);

        System.out.println("Initial PriorityQueue " + pq);
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());

    }
}
