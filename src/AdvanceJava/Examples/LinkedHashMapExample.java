package AdvanceJava.Examples;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap<Character,Integer> counter = new LinkedHashMap<>();

        counter.put('A',1);
        counter.put('B',1);
        counter.put('C',1);
        counter.put(null,3);

        counter.keySet().forEach(key -> {
            System.out.print(key+" ");
        });

        counter.entrySet().forEach((key)->{
            System.out.println(key.getKey()+" "+key.getValue());
        });

        TreeMap<Character,Integer> counter1 = new TreeMap<Character,Integer>();

        counter1.put('A',1);
        counter1.put('B',1);
        counter1.put('C',1);
        counter1.put(null,3);
    }
}
