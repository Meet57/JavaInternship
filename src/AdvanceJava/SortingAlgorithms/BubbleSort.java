package AdvanceJava.SortingAlgorithms;

/*
* It compares every two adjacent pairs and exchange if required
* By every iteration the largest element arrives at the end
* Time: O(n^2)
* */
public class BubbleSort {
    void sort(int arr[])
    {
        for (int i = 0; i < arr.length-1; i++) {
            Boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j]>arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    void printArray(int arr[])
    {
        for (int a :
                arr) {
            System.out.print(a+" ");
        }
    }

    public static void main(String args[])
    {
        BubbleSort ob = new BubbleSort();
        int arr[] = {64,25,12,22,11};
        ob.sort(arr);
        System.out.println("Bubble sort");
        ob.printArray(arr);
    }
}
