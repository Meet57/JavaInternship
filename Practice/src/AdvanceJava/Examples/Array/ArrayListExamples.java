package AdvanceJava.Examples.Array;

import java.util.ArrayList;
public class ArrayListExamples {
    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<String>();
//        list.add(null);
        list.add(0, "A");
//        list.add(3, "B");     Array out of index
        list.add(1, "Z");
        list.add(2, "B");
        list.sort((a,b)-> (int)a.charAt(0) - (int)b.charAt(0));
        System.out.println(list);
        ArrayList<String> list2 = list;
        System.out.println(list2 == list);
        System.out.println(list2.equals(list));

        list2 = (ArrayList<String>) list.clone();
        System.out.println(list2 == list);
        System.out.println(list2.equals(list));
    }
}
