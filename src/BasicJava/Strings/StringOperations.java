package BasicJava.Strings;

public class StringOperations {
    public static void main(String[] args) {
        String s1 = "Meet";
        String s2 = "Meet";

        System.out.println(s1 == s2);

        s1 = new String("Meet");
        s2 = new String("Meet");

        System.out.println(s1 == s2);

        // 16 chars
        StringBuffer s = new StringBuffer();
        s = new StringBuffer(5);
        System.out.println(s.capacity());
        s.append("Meet");
        s.append(" N Patel");
        System.out.println(s + " " + String.valueOf(s.capacity()));


//      Stirng Buffer appending Speed
//        This is thread safe
//        It means two threads can't call the methods of StringBuffer simultaneously.
        long startTime = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("Java");
        for (int i = 0; i < 999999; i++) {
            sb.append("Meet ");
        }
        System.out.println(System.currentTimeMillis() - startTime + "ms");

//      Stirng Builder appending Speed
//        this is not thread safe as it is not synchronized
        startTime = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder("Java");
        for (int i = 0; i < 999999; i++) {
            sb2.append("Meet ");
        }
        System.out.println(System.currentTimeMillis() - startTime + "ms");
        System.out.println(sb2.length());

        StringBuilder s3 = new StringBuilder("Meet").append(1).append("Patel").replace(4,5," ");
        Object s4 = s3.toString();

        System.out.println(s3.getClass()+" "+s4.getClass());
    }
}
