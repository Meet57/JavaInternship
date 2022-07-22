package DataStructure;

import java.util.Scanner;

public class Test {
    public int add(int a,int b){
        System.out.println("int");
        return a+b;
    }

    public long add(long a,long b){
        System.out.println("long");
        return a+b;
    }

    public void tryWithResources(){
        try(Scanner sc = new Scanner(System.in)){
            System.out.println(sc.nextLine().getClass());
        }
    }

    public void checkTry(){
        try{
            System.out.println("Tired");
            int a = 10/0;
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Done");
//            holdInfinity();
        }
        System.out.println("It goes on in on in on");
    }

    public static void main(String[] args) {
        String sa = "Java";
        String sb = new String("Java").intern();
        System.out.println(String.valueOf(sa==sb));
        System.out.println(sa.hashCode()+" "+sb.hashCode());

        Test a = new Test();
        System.out.println(a.add(1,2));

        int c = 10;
        a.tryWithResources();
        try{
            int b = c / 0;
        }catch (ArithmeticException e){
            a.checkTry();
        }catch (NullPointerException e){
            System.out.println("qwe");
        }
    }
}
