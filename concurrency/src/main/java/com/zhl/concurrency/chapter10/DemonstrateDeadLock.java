package com.zhl.concurrency.chapter10;

import java.util.Random;

/**
 * 动态的锁顺序死锁.
 */
public class DemonstrateDeadLock {

    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);

                    DollarAmount amount = new DollarAmount(rnd.nextInt(1000));
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }

    /**
     * 注意，容易发生死锁
     * 动态的锁顺序死锁：在transferMoney中如何发生死锁？所有的线程似乎都是按照相同的顺序来获得锁，但事实上锁的顺序取决于
     * 传递给transferMoney的参数顺序，而这些参数顺序又取决于外部输入。如果两个线程同时调用transferMoney，其中一个线程从
     * X向Y转账，另一个线程从Y向X转账，那么就会发送死锁。
     */
    public static void transferMoney(Account fromAccount, Account toAccount, DollarAmount amount) {
        // 线程A：X -> Y
        // 线程B：Y -> X
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    // throws exception
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }


    /**
     * 通过锁顺序来避免死锁
     */
    private static final Object tieLock = new Object();

    public void transferMoney2(final Account fromAcct, final Account toAcct, final DollarAmount amount) {
        class Helper {
            public void transfer() {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    // throw exception
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}

class DollarAmount {
    public DollarAmount(int random) {

    }

    public Integer compareTo(DollarAmount amount) {
        return 0;
    }
}

class Account {

    public DollarAmount getBalance() {
        return null;
    }

    public void debit(DollarAmount amount) {
    }

    public void credit(DollarAmount amount) {
    }
}
