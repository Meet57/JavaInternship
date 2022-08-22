package AdvanceJava.Examples;

import java.util.ArrayList;
import java.util.List;

public class DoubleColon {
    public static void lengthMeet(String a){
        System.out.print(a.length());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Geeks ");
        list.add("For ");
        list.add("GEEKS ");

        list.forEach(System.out::print);
        System.out.println();
        list.forEach(DoubleColon::lengthMeet);
    }
}
