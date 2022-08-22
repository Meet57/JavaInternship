package AdvanceJava.SortingAlgorithms;

/*
* It compares the data with other data in the list and exchange the min with the current index
* time: O(n^2)
* */
class SelectionSort
{
    void sort(int arr[])
    {
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[i]){
                    min = j;
                }
            }

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
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
        SelectionSort ob = new SelectionSort();
        int arr[] = {64,25,12,22,11};
        ob.sort(arr);
        System.out.println("Selection sort");
        ob.printArray(arr);
    }
}

