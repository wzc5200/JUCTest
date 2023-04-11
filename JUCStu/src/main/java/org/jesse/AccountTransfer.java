package org.jesse;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jesse on 2023/3/17 15:35
 */

@Slf4j(topic = "c.AccountTransfer")
public class AccountTransfer {

    public static void main(String[] args) throws InterruptedException {
        Account account01 = new Account(1000);
        Account account02 = new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account01.transfer(account02, 200);
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account02.transfer(account01, 200);
            }
        }, "t2");


        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.debug("total: {}", account01.getMoney() + account02.getMoney());
    }

}


class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int i) {
        this.money = money;
    }

    public void transfer(Account target, int amount) {
        synchronized (Account.class) {
            if (this.money > amount) {
                this.setMoney(this.getMoney() - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
