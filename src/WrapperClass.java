/*
* Made a code which
* implements constructor exception throwing
* Wrapper class
* try with resources for closable classes
* */

import java.io.Closeable;
import java.io.IOException;

class MeetWrapper implements Closeable {
    private int i;
    public MeetWrapper(String i) throws Exception {
        if(!i.matches("-?(0|[1-9]\\d*)")){
            throw new Exception("Got String Instead of Int");
        }
        this.i=Integer.parseInt(i);
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

    @Override
    public void close() throws IOException {
        System.out.println("Closable Called");
    }
}

public class WrapperClass {
    static {
        System.out.println("Static Blocked");
    }
    public static void main(String[] args) throws Exception{
        MeetWrapper m = new MeetWrapper("57");
        System.out.println(m);

//        Autoboxing
        Integer a = new Integer("10");
        Integer b = 20;

//        Unboxing
        int c = a;

        System.out.println(a.getClass() + " "+ b.getClass() + " ");

        /*
        * Closable is only called when a object is returned
        * Otherwise object will now be assigned
        * Closeable is called when the block is over
        * */
        try(MeetWrapper n = new MeetWrapper("1")){
            System.out.println(n);
//            n = null;
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Executed with Error");
        }

        System.out.println("Did closable Called ?");
    }
}
