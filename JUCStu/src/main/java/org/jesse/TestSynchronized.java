package org.jesse;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jesse on 2023/3/19 09:23
 */

@Slf4j(topic = "c.TestSynchronized")
public class TestSynchronized {

    public static synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        log.debug("1");
    }

    public /*static*/ synchronized void b() {
        log.debug("2");
    }

    public static void main(String[] args) {

        TestSynchronized a1 = new TestSynchronized();

        new Thread(() -> {
            try {
                a1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a1").start();

        new Thread(() -> {
            a1.b();
        }, "b1").start();
    }
}


