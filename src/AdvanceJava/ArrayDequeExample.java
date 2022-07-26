package AdvanceJava;

import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayDequeExample {
    public static void main(String[] args) {
        ArrayDeque<Character> set=new ArrayDeque<Character>();
        String s = "asdMdsa";
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }

        Boolean isPali = true;
        while(true){
            if(set.peekFirst().equals(set.peekLast())){
                set.pollFirst();
                set.pollLast();
            }else{
                isPali = false;
                break;
            }
            if(set.peekFirst() == null || set.peekLast() == null){
                break;
            }
        }

        System.out.println(isPali);
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

        System.out.println(queue.remainingCapacity());
    }
}