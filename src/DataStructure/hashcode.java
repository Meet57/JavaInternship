package DataStructure;

public class hashcode {

    public static int hashcodeGenerator(String a){
        int ascii,hashCode=0;
        for (int i = 0; i < a.length(); i++) {
            ascii = (int) a.charAt(i);
            hashCode += ascii * Math.pow(10,i);
        }
        return hashCode;
    }

    public static void main(String[] args) {
        String a = "Meet";
        String b = new String("Meet");
        System.out.println(
                hashcodeGenerator(a) == hashcodeGenerator(b)
        );
    }
}
