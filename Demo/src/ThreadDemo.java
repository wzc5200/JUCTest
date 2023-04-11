import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Jesse on 2023/3/17 00:43
 */

public class ThreadDemo {
    public static void main(String[] args) {

        //继承Thread类
        MyThread myThread = new MyThread();
        //myThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程输出-->" + i);
        }

        //实现Runnable接口
        MyRunnable target = new MyRunnable();
        Thread thread01 = new Thread(target, "1号线程");
        //thread01.start();

        Thread thread02 = new Thread(target);
        //thread02.start();

        //实现Callable接口
        Callable call = new MyCallable();
        FutureTask<String> task = new FutureTask<>(call);
        Thread t = new Thread(task);
        t.start();
        try {
            String s = task.get(); // 获取call方法返回的结果（正常/异常结果）
            System.out.println(s);
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程输出-->" + i);
        }
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() + "->" + "Hello World";
    }
}