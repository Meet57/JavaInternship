package AdvanceJava.DataStructure;

class Stack{
    private int[] arr;
    private int top = -1,size;

    Stack(int size){
        this.size = size;
        arr = new int[size];
    }

    public void push(int value){
        if(top<size-1){
            top++;
            arr[top]=value;
        }else{
            System.out.println("Stack Overflow");
        }
    }

    public int peek(){
        if(top > -1){
            return arr[0];
        }else{
            System.out.println("Stack Underflow");
            return -1;
        }
    }

    public int pop(){
        if(top > -1){
            top--;
            return arr[top+1];
        }else{
            System.out.println("Stack Underflow");
            return -1;
        }
    }

    public void print(){
        int i;
        for (i=0;i<=top-1;i++){
            System.out.print(arr[i] + " -> ");
        }
        System.out.println(arr[i]);
    }
}

public class StackExample {
    public static void main(String[] args) {
        Stack a = new Stack(5);
        a.push(1);
        a.push(2);
        a.push(3);
        a.push(4);
        a.push(5);
        a.push(6);
        a.pop();
        a.push(7);
        a.print();
    }
}
