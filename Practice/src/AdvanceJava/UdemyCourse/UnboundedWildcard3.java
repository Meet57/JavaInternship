package AdvanceJava.UdemyCourse;

import java.util.Arrays;
import java.util.List;

//If
public class UnboundedWildcard3 {
//    public static void print(List<Object> list){
    public static void print(List<?> list){
        for (Object o: list) {
            System.out.print(o+" ");
        }
    }

    public static void main(String[] args) {
//        Integer i = 23;
//        print(i);
        List<?> nums = Arrays.asList(1,2,3);
        print(nums);
    }
}
