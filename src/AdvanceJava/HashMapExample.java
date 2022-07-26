package AdvanceJava;

import java.util.*;
import java.util.function.BiFunction;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
        Scanner sc = new Scanner(System.in);

        String s = "A Map doesn't allow duplicate keys, but you can have duplicate values. HashMap and LinkedHashMap allow null keys and values, but TreeMap doesn't allow any null key or value.";

        for (int i = 0; i < s.length(); i++) {
//            if (counter.containsKey(s.charAt(i))) {
//                counter.put(s.charAt(i), counter.get(s.charAt(i)) + 1);
//            } else {
//                counter.put(s.charAt(i), 1);
//            }
            if(counter.computeIfPresent(s.charAt(i),(K,V)->{
                return  V+1;
            })==null){
                counter.put(s.charAt(i), 1);
            }
        }

        System.out.println(counter);

        for (Map.Entry m : counter.entrySet()) {
            System.out.println(m.getKey() + ":" + m.getValue());
        }

//        ---------
        Set a = counter.keySet();

        counter.get('a');

        for (Object b : a) {
            System.out.println(b + ":" + counter.get(b));
        }

//        ----------
        counter.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        });

        counter.remove("A");
        System.out.println(counter.replace('A', 1, 10)); // it only changes if it is there

        counter.replaceAll((k, v) -> {
            return v + 100;
        });

        System.out.println(counter);

        System.out.println(counter.entrySet());

        System.out.println(counter.size());

        counter.computeIfAbsent('D', k -> {
            return 10 * 20;
        });

        counter.computeIfPresent('H',
                (key, value) -> {
                    return 100 * 200;
                }
        );

        System.out.println(counter.get('D'));
        System.out.println(counter.get('H'));

        ArrayList<Character> asd = new ArrayList<>();
        asd.addAll(counter.keySet());

        System.out.println(asd);
    }
}
