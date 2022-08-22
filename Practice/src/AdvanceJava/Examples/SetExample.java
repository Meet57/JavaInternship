package AdvanceJava.Examples;

import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<String> a = new HashSet<>();

        a.add("A");
        a.add("B");
        a.add("C");


        LinkedHashSet<String> b = new LinkedHashSet<>();

        b.add("C");
        b.add("B");
        b.add("A");

        Set<String> c = new TreeSet<String>();

        c.add("C");
        c.add("B");
        c.add("A");
//        Object[] aa = c.toArray();
//        System.out.println(aa[1]);
        ArrayList<String> aaa = new ArrayList<>(c);
        System.out.println(aaa.get(1));
//        c.clear();

        System.out.println(a);
        System.out.println(b);
        if(!c.isEmpty()){
            System.out.println(c);
            System.out.println(c.containsAll(b));
//            Object[] arr = c.toArray();
//            for (Object aa : arr)
//                System.out.println(aa);
        }

//        Iterator i = c.iterator();
//
//        while (i.hasNext()){
//            System.out.println(i.next());
//        }

        for (String as : c) {
            System.out.print(as);
        }

    }
}
