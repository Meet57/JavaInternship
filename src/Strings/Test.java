package Strings;

public class Test {
    public static void main(String[] args) {
        String s1 = new String("Meet");
        String s2 = new String("Meet");
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));

        int arr[][] = {{1,2},{2,3},{3,4},{4,5}};
        int arr2[][] = arr.clone();
//        It creates a shallow copy of the array
//        int arr2[][] = arr;
        System.out.println(arr.equals(arr2));
        /*
        * WHen a singel array is clone it is deep copy
        * when a multidimentional array is clone it is shallow copy but the out array is deep copied
        * https://media.geeksforgeeks.org/wp-content/cdn-uploads/Blank-Diagram-Page-1-12.jpeg
        * */
        System.out.println("arr[1] == arr2[1] "+ (arr[1] == arr2[1]));
        arr[1] = new int[]{1, 1};
        System.out.println("arr[1] == arr2[1] "+ (arr[1] == arr2[1]));
        for(int a[]:arr){
            for(int b: a){
                System.out.print(b);
            }
            System.out.println();
        }
        System.out.println("--");
        for(int a[]:arr2){
            for(int b: a){
                System.out.print(b);
            }
            System.out.println();
        }
    }
}
