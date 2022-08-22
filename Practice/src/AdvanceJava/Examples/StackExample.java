package AdvanceJava.Examples;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stk = new Stack<>();
        stk.push(119);
        stk.push(203);
        stk.push(988);
        stk.peek();

        Iterator i =  stk.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("---");
        stk.forEach(a -> {
            System.out.println(a);
        });
    }
}
