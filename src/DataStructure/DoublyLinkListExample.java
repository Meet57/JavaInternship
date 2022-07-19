package DataStructure;

class Node{
    public int value;
    public Node right = null,left = null;

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

    Node(int value){
        this.value = value;
    }
}

class DoublyLinkedList{
    private Node Head=null,temp,prev,next;

    private void insert(int value){
        Head = new Node(value);
    }

    public void insertRight(int value){
        if(Head == null){
            insert(value);
        }else{
            next = Head;
            while (next.getRight() != null){
                next = next.getRight();
            }
            temp = new Node(value);
            temp.setLeft(next);
            next.setRight(temp);
        }
    }

    public void insertLeft(int value){
        if(Head == null){
            insert(value);
        }else{
            prev = Head;
            while (prev.getLeft() != null){
                prev = prev.getLeft();
            }
            temp = new Node(value);
            temp.setRight(prev);
            prev.setLeft(temp);
        }
    }

    public void print(){
        temp = Head;
        while (temp.getLeft() != null){
            temp = temp.getLeft();
        }
        do{
            System.out.println(temp.value);
            temp = temp.getRight();
        }while(temp!=null);
    }
}

public class DoublyLinkListExample {
    public static void main(String[] args) {
        DoublyLinkedList dl = new DoublyLinkedList();
        dl.insertRight(1);
        dl.insertRight(2);
        dl.insertLeft(0);
        dl.insertLeft(-1);
        dl.insertRight(3);
        dl.insertLeft(-2);
        dl.insertLeft(-3);
        dl.print();
    }
}
