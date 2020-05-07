package servicecomb.springmvcserverc.java.training.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示可重入锁是什么意思
 */
public class WhatReentrant2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("第一次获取锁，这个锁是：" + lock);
                    int index = 1;
                    while (true) {
                        try {
                            lock.lock();
                            System.out.println("第" + ++index + "次获取锁，这个锁是：" + lock);
                            if (index == 10) {
                                break;
                            }
                        } finally {
                            lock.unlock();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
