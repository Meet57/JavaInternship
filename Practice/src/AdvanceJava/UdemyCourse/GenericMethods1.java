package AdvanceJava.UdemyCourse;
/*
* Bounded type Generic parameters
* */

class Person implements Comparable<Person>{
    private int age;
    private String name;

    Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(age,o.getAge());
    }

    @Override
    public String toString() {
        return getName();
    }
}

class GenericMethods{
    public <T,V> void printItems(T t, V v){
        System.out.println(t.toString());
        System.out.println(v.toString());
    }

    public <T,V> void printItems(T t,V[] v){
        System.out.println(t.toString());
        for (Object aa : v) {
            System.out.print(" "+aa.toString());
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> T calculateMin(T num1,T num2){
        if(num1.compareTo(num2) < 0){
            return num1;
        }
        return num2;
    }
}

public class GenericMethods1 {
    public static <T extends Number> double add(T num1,T num2){
        return num1.doubleValue()+ num2.doubleValue();
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.printItems(new String("Meet"),57);
//        gm.printItems("Fruits", new int[]{1, 2, 3, 4, 5});
        Integer[] nums = {1,2,3,4};
        gm.printItems("Numbers",nums);

        System.out.println("Min is : "+GenericMethods.calculateMin(1123,2432));
        System.out.println("Min is : "+GenericMethods.calculateMin('a','x'));
        System.out.println("Min is : "+GenericMethods.calculateMin(new Person("Meet",111),new Person("Dhruv",22)));
        System.out.println(add(1.5,1.5));
    }
}
