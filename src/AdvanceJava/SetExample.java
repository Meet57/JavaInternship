package AdvanceJava;

import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<String> a = new HashSet<String>();

        a.add("A");
        a.add("B");
        a.add("C");

        Set<String> b = new LinkedHashSet<String>();

        b.add("C");
        b.add("B");
        b.add("A");

        Set<String> c = new TreeSet<>();

        c.add("C");
        c.add("B");
        c.add("A");
//        c.clear();

        System.out.println(a);
        System.out.println(b);
        if(!c.isEmpty()){
            System.out.println(c);
            System.out.println(c.containsAll(b));
            Object[] arr = c.toArray();
            for (Object aa : arr)
                System.out.println(aa);
        }


    }
}
