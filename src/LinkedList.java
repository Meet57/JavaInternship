class Node<T> {
    private T value = null;
    private Node next = null;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class LinkList {
    private Node head = null, temp = null;

    public void add(int value) {
        if (head == null) {
            head = new Node<Integer>();
            head.setValue(value);
        } else {
            temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<Integer>());
            temp = temp.getNext();
            temp.setValue(value);
        }
    }

    public void add(Float value) {
        if (head == null) {
            head = new Node<Float>();
            head.setValue(value);
        } else {
            temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<Float>());
            temp = temp.getNext();
            temp.setValue(value);
        }
    }

    public void add(String value) {
        if (head == null) {
            head = new Node<String>();
            head.setValue(value);
        } else {
            temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<String>());
            temp = temp.getNext();
            temp.setValue(value);
        }
    }

    public void delete(Object value){
        if(head.getValue() == value){
            head = head.getNext();
            return;
        }
        temp = head;
        while(temp!=null){
            if(temp.getNext().getValue() == value){
                temp.setNext(temp.getNext().getNext());
                return;
            }
            temp =temp.getNext();
        }
    }

    public void display(){
        temp = head;
        do{
            System.out.println(temp.getValue());
            temp = temp.getNext();
        }while(temp != null);
    }

    @Override
    public String toString() {
        return "This is custom Link List class";
    }
}

class LinkedList {
    public static void main(String[] args) {
        LinkList A = new LinkList();
        A.add("Hello");
        A.add(10);
        A.add(30.3f);
        A.add("Meet");
        A.add(40);
        A.delete("Meet");
        A.display();

        System.out.println(A.hashCode());
        System.out.println(A.toString());
    }
}