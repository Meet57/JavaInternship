package AdvanceJava;

import java.util.LinkedHashMap;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap<Character,Integer> counter = new LinkedHashMap<>();

        counter.put('A',1);
        counter.put('B',1);
        counter.put('C',1);
        counter.put('A',3);

        counter.keySet().forEach(key -> {
            System.out.print(key+" ");
        });

        counter.entrySet().forEach((key)->{
            System.out.println(key.getKey()+" "+key.getValue());
        });

    }
}
