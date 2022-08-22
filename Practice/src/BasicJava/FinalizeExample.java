package BasicJava;

class Meet{
    private int Value;

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object Deleted");
    }
}

public class FinalizeExample {
    public static void main(String[] args) {
        Meet m = new Meet();
        m.setValue(10);
        System.out.println(m.getValue());
        m = null;
        System.gc();
    }
}
