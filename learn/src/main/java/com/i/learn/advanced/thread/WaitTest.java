package com.i.learn.advanced.thread;

public class WaitTest {

    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();
                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");
                t1.wait();
                t1.join();  //  等待t1线程执行完毕
                System.out.println(Thread.currentThread().getName()+" continue");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
