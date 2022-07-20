class MeetWrapper{
    private int i;
    MeetWrapper(int i){
        this.i=i;
    }
    public int getValue(){
        return i;
    }
    public void setValue(int i){
        this.i=i;
    }
    @Override
    public String toString() {
        return "Meet says integer is : "+String.valueOf(i);
    }
}

public class WrapperClass {
    public static void main(String[] args){
        MeetWrapper m = new MeetWrapper(57);
        System.out.println(m);

//        Autoboxing
        Integer a = new Integer(10);
        Integer b = 20;

//        Unboxing
        int c = a;

        System.out.println(a.getClass() + " "+ b.getClass() + " ");
    }
}
