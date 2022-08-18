package visualVM;

public class MemLeak02 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String x = "";
                for (long i = 0; i < 999999; i++) {
                    x = x + String.valueOf(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String x = "";
                for (long i = 0; i < 999999; i++) {
                    x = x + String.valueOf(i);
                }
            }
        }).start();
    }
}
