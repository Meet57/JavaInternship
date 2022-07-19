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
        return "Meet";
    }
}

public class WrapperClass {
    public static void main(String[] args){
        MeetWrapper m = new MeetWrapper(1);
        System.out.println(m);
    }
}
