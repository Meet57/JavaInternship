package DataStructure;

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
        getFirst();
        getLast();
        prev.setLeft(next);
        next.setRight(prev);
        isCircular = true;
    }

    public void print() {
        if (Head == null) {
            System.out.println("No element in the list");
            return;
        }
        if (isCircular) {
            printCircular();
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
    }

    public void printCircular() {
        temp = Head;
        int i = 0;
        System.out.println("Circular Linked List");
        do {
            System.out.print(temp.value + " ");
            temp = temp.getRight();
            i++;
            if (i == 20) break;
        } while (temp != null);
        System.out.println();
    }

    public void breakCircularList(int n) {
        if (!isCircular) return;
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
        while (temp.value != n) {
            if (temp.hashCode() == headHashCode) {
                System.out.println("Element Not present in the List");
                return;
            }
            temp = temp.getRight();
        }
        if (temp.getRight() == null) {
            temp.getLeft().setRight(null);
            return;
        }
        if (temp.getLeft() == null) {
            temp.getRight().setLeft(null);
            return;
        }
        temp.getLeft().setRight(temp.getRight());
        temp.getRight().setLeft(temp.getLeft());
    }
}

public class DoublyLinkListExample {
    @SuppressWarnings("All")
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DoublyLinkedList dl = new DoublyLinkedList();
        dl.insertRight(1);
        dl.insertRight(2);
        dl.insertLeft(0);
        dl.insertRight(3);
        dl.insertRight(4);
        dl.insertRight(5);
        dl.print();
        dl.deleteNode(0);
        dl.print();
        dl.circularLinkedList();
        dl.print();

        System.out.print("Enter the number from circular linked list to break: 2\n");
//        int n = sc.nextInt();
//        dl.breakCircularList(n);
        dl.breakCircularList(2);
        dl.print();
    }
}
