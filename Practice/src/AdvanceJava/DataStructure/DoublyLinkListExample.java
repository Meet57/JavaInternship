package AdvanceJava.DataStructure;

import java.util.Scanner;

class Node {
    public int value;
    public Node right = null, left = null;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    Node(int value) {
        this.value = value;
    }
}

@SuppressWarnings("All")
class DoublyLinkedList {
    private Node Head = null, temp, prev, next;
    private boolean isCircular = false;

    private void getLast() {
        next = Head;
        while (next.getRight() != null) {
            next = next.getRight();
        }
    }

    private void getFirst() {
        prev = Head;
        while (prev.getLeft() != null) {
            prev = prev.getLeft();
        }
    }

    private void insert(int value) {
        Head = new Node(value);
    }

    public void insertRight(int value) {
        if (Head == null) {
            insert(value);
        } else {
            getLast();
            temp = new Node(value);
            temp.setLeft(next);
            next.setRight(temp);
        }
    }

    public void insertLeft(int value) {
        if (Head == null) {
            insert(value);
        } else {
            getFirst();
            temp = new Node(value);
            temp.setRight(prev);
            prev.setLeft(temp);
        }
    }

    public void circularLinkedList() {
        if (isCircular)
            return;
        if (Head == null)
            return;
        getFirst();
        getLast();
        prev.setLeft(next);
        next.setRight(prev);
        isCircular = true;
    }

    private void sleep(int seconds){
        try
        {
            Thread.sleep(seconds * 1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void print() {
        if (Head == null) {
            System.out.println("No element in the list");
            sleep(3);
            return;
        }
        if (isCircular) {
            printCircular();
            sleep(5);
            return;
        }
        getFirst();
        System.out.println("Doubly Linked List");
        temp = prev;
        do {
            System.out.print(temp.value + " ");
            temp = temp.getRight();
        } while (temp != null);
        System.out.println();
        sleep(5);
    }

    public void printCircular() {
        temp = Head;
        int i = 0;
        System.out.println("Circular Linked List");
        do {
            System.out.print(temp.value + " ");
            temp = temp.getRight();
            i++;
            if (i == 20)
                break;
        } while (temp != null);
        System.out.println();
    }

    public void breakCircularList(int n) {
        if (!isCircular)
            return;
        if (Head == null)
            return;
        int headHashCode = Head.hashCode();
        temp = Head.getRight();
        while (temp.value != n) {
            if (temp.hashCode() == headHashCode) {
                System.out.println("Element Not present in the List");
                return;
            }
            temp = temp.getRight();
        }
        prev = temp;
        next = temp.getRight();
        Head = next;
        Head.setLeft(null);
        prev.setRight(null);
        isCircular = false;
    }

    public void deleteNode(int n) {
        if (Head == null)
            return;
        temp = prev = next = null;
        if (Head.getLeft() == null && Head.getRight() == null) {
            if (Head.value == n) {
                Head = temp = prev = next = null;
                return;
            } else {
                System.out.println("Element Not present in the List");
                return;
            }
        }
        int headHashCode;
        if (!isCircular) {
            getFirst();
            temp = prev;
            headHashCode = temp.hashCode();
        } else {
            headHashCode = Head.hashCode();
            temp = Head;
        }
        if (temp.value != n) {
            temp = temp.getRight();
            while (temp.getRight()!=null && temp.value != n) {
                if (temp.hashCode() == headHashCode) {
                    System.out.println("Element Not present in the List");
                    return;
                }
                temp = temp.getRight();
            }
        }
        if(temp.value != n){
            System.out.println("Element Not present in the List");
            return;
        }
        if (temp.getRight() == null) {
            temp.getLeft().setRight(null);
            return;
        }
        if (temp.getLeft() == null) {
            if (Head == temp) {
                Head = temp.getRight();
            }
            temp.getRight().setLeft(null);
            return;
        }
        temp.getLeft().setRight(temp.getRight());
        temp.getRight().setLeft(temp.getLeft());
    }
}

public class DoublyLinkListExample {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DoublyLinkedList dl = new DoublyLinkedList();
//        dl.insertRight(1);
//        dl.insertRight(2);
//        dl.insertRight(3);
//        dl.insertRight(2);
//        dl.deleteNode(2);
//        dl.deleteNode(2);
//        dl.print();
//        dl.deleteNode(2);
//        dl.print();
        int choice = 1;

        while (choice != 9) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("1.Insert Right");
            System.out.println("2.Insert Left");
            System.out.println("3.Delete Node");
            System.out.println("4.Make LL circular");
            System.out.println("5.Make LL doubly");
            System.out.println("6.Print");
            System.out.println("9.Exit");
            System.out.print("Enter your Action: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Value to add: ");
                    dl.insertRight(sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter Value to add: ");
                    dl.insertLeft(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Value to delete: ");
                    dl.deleteNode(sc.nextInt());
                    break;
                case 4:
                    dl.circularLinkedList();
                    break;
                case 5:
                    System.out.print("Enter Value to Break: ");
                    dl.breakCircularList(sc.nextInt());
                    break;
                case 6:
                    dl.print();
                    break;
                case 9:
                    break;
                default:
                    System.err.println("Wrong Option");
            }
            sc.nextLine();
        }
    }
}
