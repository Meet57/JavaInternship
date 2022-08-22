package visualVM;

import java.util.Map;

public class MemLeak {

    public String key;
    public MemLeak(String key) {

        this.key = key;

    }

    public static void main(String args[]) {
        try {
            Map map = System.getProperties();
            for (; ; ) {
                map.put(new MemLeak("key"), "value");
                System.out.println(Runtime.getRuntime().freeMemory()+" "+Runtime.getRuntime().totalMemory());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}