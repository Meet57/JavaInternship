package AdvanceJava.UdemyCourse;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ArrayList4 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        names.add("D");

        names.add(0,"AA");
        names.remove(0);

        System.out.println(names);
//        System.out.println(names.toArray().toString());
        System.out.println(names.contains("X"));
    }
}
