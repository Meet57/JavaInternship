package AdvanceJava.SortingAlgorithms;

/*
* it iterates and check the element is smaller or not, if yes it shifts it to front
* time: O(n^2)
* */
public class InsertionSort {
    void sort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int key = arr[i];
            int j = i-1;

            while(j>=0 && key < arr[j]){
                arr[j+1] = arr[j];
                j = j - 1;
            }
            arr[j+1] = key;
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
        InsertionSort ob = new InsertionSort();
        int arr[] = {64,25,12,22,11};
        ob.sort(arr);
        System.out.println("Bubble sort");
        ob.printArray(arr);
    }
}
