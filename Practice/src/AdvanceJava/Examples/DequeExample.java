package AdvanceJava.Examples;

import java.util.ArrayDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DequeExample {
    public static void main(String[] args) {
        ArrayDeque<Character> set=new ArrayDeque<Character>();
        String s = "asdMdsa";
        for (int i = 0; i < s.length(); i++) {
            set.offerLast(s.charAt(i));
        }

        LinkedBlockingDeque<Character> set1 = new LinkedBlockingDeque<Character>(set);

//        System.out.println(set1);
//        set1.forEach(ele->{
//            System.out.print(ele + " ");
//        });
//        set.iterator();

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


    }


}