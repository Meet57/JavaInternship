public class EnumExample {
    enum Season{
        WINTER(5), SPRING(10), SUMMER(15), FALL(20);

        private int value;
        private Season(int value){
            System.out.println(value);
            this.value=value;
        }
    }
    public static void main(String[] args) {
        for (Season s : Season.values())
            System.out.println(s+" "+s.value);
        System.out.println("Index of WINTER is: "+Season.valueOf("WINTER").ordinal());

        Season s = Season.WINTER;
//        Season s = Season.values()[0];
        System.out.println(s+ " "+s.value);
        System.out.println(Season.values()[1]);

        System.out.println(Season.WINTER.toString());
    }
}
