package AdvanceJava.UdemyCourse;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListVSArrayLIst5 {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        long now = System.currentTimeMillis();

        for (int i = 0; i < 200000; i++) {
            array.add(0,i);
        }
        System.out.println("Time taken : " + (System.currentTimeMillis()-now));

        LinkedList<Integer> list = new LinkedList<>();

        now = System.currentTimeMillis();

        for (int i = 0; i < 200000; i++) {
            list.addFirst(i);
        }
        System.out.println("Time taken : " + (System.currentTimeMillis()-now));

    }
}
