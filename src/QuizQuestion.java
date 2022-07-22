class X {
    static int i = 1111;

    X() {
        System.out.println("X");
    }

    static {
        i = i-- - --i; //L1
        System.out.println(1);
    }

    {
        i = i++ + ++i; //L2
        System.out.println(2);
    }

}

class Y extends X {
    static {
        i = --i - i--; //L3
        System.out.println(3);
    }

    {
        i = ++i + i++; //L4
        System.out.println(4);
    }
}

public class QuizQuestion {
    public static void main(String[] args) {
        Y y = new Y();
        System.out.println(y.i); //L5
    }
}
