class Polygon {
    public void display() {
        System.out.println("Inside the Polygon class");
    }
    public void hello(){
        System.out.println("Hello");
    }
}

class AnonymousDemo {
    public static void createClass() {
        // creation of anonymous class extending class Polygon
        Polygon p1 = new Polygon() {
            public void display() {
                System.out.println("Inside an anonymous class.");
            }
        };
        p1.display();
        p1.hello();
    }
}

public class AnonInnerClass {
    public static void main(String[] args) {
        AnonymousDemo.createClass();
    }
}
