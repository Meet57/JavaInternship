package AdvanceJava.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StreamEx {
    public static void main(String[] args) {
        ArrayList<Bug> a = new ArrayList<Bug>();

        a.add(new Bug("A", 1));
        a.add(new Bug("B", 3));
        a.add(new Bug("C", 5));
        a.add(new Bug("D", 7));
        a.add(new Bug("E", 9));
        a.add(new Bug("F", 11));

        int b = a.stream().map(ele -> {
            return ele.getPriority();
        }).filter(
                p -> p > 5
        ).reduce(
                0,
                (sum, p) -> sum + p
        );
        /*Gives the total of all priority listed above 5*/

        Object c = a.stream().collect(Collectors.summarizingInt(e->e.getPriority()));
//        IntSummaryStatistics{count=6, sum=36, min=1, average=6.000000, max=11}

        c = a.stream().collect(Collectors.summingInt(e->e.getPriority()));
//        System.out.println(c); 36

        c = a.stream().max((x,y)->x.getPriority()-y.getPriority()).get().getPriority();
        c = a.stream().min((x,y)->x.getPriority()-y.getPriority()).get().getPriority();
//        System.out.println(c); 11

        a.stream().filter(x->x.getPriority()>5).forEach(System.out::println);

        a.stream().map(ele->ele.getPriority()).reduce(Integer::sum).get();
//        36

        a.stream().filter(x->x.getPriority()>5).count();
//        3

//        collecting it as map for name to priority
        HashMap<String,Integer> map =
                new HashMap<String,Integer>(a.stream().collect(Collectors.toMap(p->p.getName(), p->p.getPriority())));
//        for (Map.Entry m : map.entrySet()) {
//            System.out.println(m.getKey() + ":" + m.getValue());
//        }
        map.entrySet().forEach(ele->{
            System.out.println(ele.getKey()+"->"+ele.getValue());
        });

//        a.stream().distinct().forEach(p->{
//            System.out.println(p.getName());
//        });

    }
}
