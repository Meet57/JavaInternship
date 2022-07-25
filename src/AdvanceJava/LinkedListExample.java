package AdvanceJava;

import java.util.*;

public class LinkedListExample{
    public static void main(String args[]){

//        LinkedList<String> al=new LinkedList<String>();
//        al.add("Ravi");
//        al.add("Vijay");
//        al.add("Ravi");
//        al.add("Ajay");
//
//        Iterator<String> itr=al.iterator();
//        while(itr.hasNext()){
//            System.out.println(itr.next());
//        }

        LinkedList<Integer> ill = new LinkedList<Integer>();
        ill.add(1);
        ill.add(3);
        ill.add(5);
        ill.add(1);
        ill.add(1);
        ill.add(1);
        ill.add(1);
        ill.add(4);
        ill.add(2);
        ill.add(1);
        ill.add(1);
//        ill.remove(new Integer(1)); //value 1
//        ill.remove(1);  // Index
        while(ill.contains(new Integer(1))){
            ill.remove(new Integer(1));
        }
        for (Object a : ill.toArray()) {
            System.out.println(a);
        }
        System.out.println("---");
        Iterator i = ill.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }
}