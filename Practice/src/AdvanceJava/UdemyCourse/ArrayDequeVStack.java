package AdvanceJava.UdemyCourse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class ArrayDequeVStack {
    public static void main(String[] args) {
        Stack<Integer> array = new Stack<>();
        long now = System.currentTimeMillis();

        for (int i = 0; i < 200000; i++) {
            array.add(0,i);
        }
        System.out.println("Time taken : " + (System.currentTimeMillis()-now));

        ArrayDeque<Integer> list = new ArrayDeque<>();

        now = System.currentTimeMillis();

        for (int i = 0; i < 200000; i++) {
            list.add(i);
        }
        System.out.println("Time taken : " + (System.currentTimeMillis()-now));
    }
}
