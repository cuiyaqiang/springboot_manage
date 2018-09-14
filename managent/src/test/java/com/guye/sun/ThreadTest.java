package com.guye.sun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by suneee on 2018/8/14.
 */
public class ThreadTest {


}

class TestSingleThreadExecutor {
    public static void main(String[] args){
        //创建一个单线程的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        //创建实现了Runable接口的对象
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        Thread thread3 = new MyThread();
        Thread thread4 = new MyThread();
        Thread thread5 = new MyThread();
        Thread thread6 = new MyThread();
        //将线程放入线程池进行执行
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        pool.execute(thread5);
        pool.execute(thread6);
        //关闭线程池
        pool.shutdown();
    }
}

class TestFixedThreadPool {
    public static void main(String[] args) {
        //创建固定数量线程的线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //创建实现了Runable接口的对象
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        Thread thread3 = new MyThread();
        Thread thread4 = new MyThread();
        Thread thread5 = new MyThread();
        //将线程放入线程池进行执行
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        pool.execute(thread5);
        //关闭线程池
        pool.shutdown();
    }
}

class TestCachedThreadPool {
    public static void main(String[] args) {
        //创建可缓存线程的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建实现了Runable接口的对象
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        Thread thread3 = new MyThread();
        Thread thread4 = new MyThread();
        Thread thread5 = new MyThread();
        //将线程放入线程池进行执行
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        pool.execute(thread5);
        //关闭线程池
        pool.shutdown();
    }
}

class TestScheduledThreadPool {
    public static void main(String[] args) {
        //创建大小无限的线程池
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("==========================");
            }
        },1000,5000, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        },1000,2000,TimeUnit.MILLISECONDS);
    }
}