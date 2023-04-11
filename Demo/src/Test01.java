import java.util.concurrent.TimeUnit;

/**
 * Created by Jesse on 2023/3/17 01:41
 */

public class Test01 {

    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        test01();
    }


    private static void test01() throws InterruptedException {
        System.out.println("main-start");
        Thread t1 = new Thread(() -> {

            try {
                System.out.println("t1-start");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1-end");
                r = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start(); //r = 0;

        t1.join(); //r = 10;

        System.out.println("r=" + r);

        System.out.println("main-end");
    }

}
