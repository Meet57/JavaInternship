package AdvanceJava.Examples;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Character, Integer> counter = new TreeMap<Character, Integer>();

        String s = "A Map doesn't allow duplicate keys, but you can have duplicate values. HashMap and LinkedHashMap allow null keys and values, but TreeMap doesn't allow any null key or value.";

        for (int i = 0; i < s.length(); i++) {
            if(counter.computeIfPresent(s.charAt(i),(K,V)->{
                return  V+1;
            })==null){
                counter.put(s.charAt(i), 1);
            }
        }

//        counter.put("Meet",1);
//        counter.put(new String("Meet"),2);
//        counter.put(String.valueOf(new StringBuffer("Meet")),3);

        System.out.println(counter);
    }
}
