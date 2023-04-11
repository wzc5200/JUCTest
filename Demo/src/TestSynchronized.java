import java.util.concurrent.TimeUnit;

/**
 * Created by Jesse on 2023/3/17 08:48
 */

public class TestSynchronized {

    public static void main(String[] args) {
        MyNumber n1 = new MyNumber();

        new Thread(() -> {
            try {
                n1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            n1.b();
        }).start();
    }

}

class MyNumber {
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
    }

    public synchronized void b() {
        System.out.println("2");
    }
}