package com.guye.sun;

import static org.junit.Assert.assertTrue;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(){
        /**
         * TODO 线程测试
         */
        /*Thread thread1 = new Thread(){
            public void run(){
                try {
                    for (int i = 0;i < 10;i++){
                        Thread.sleep(100);
                        System.out.println(new Date()+"==========111");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        for (int i = 0;i < 10;i++){
            System.out.println(new Date()+"========222");
        }

        Thread thread2 = new Thread(){
            public void run(){
                try {
                    for (int i = 0;i < 5;i++){
                        Thread.sleep(100);
                        System.out.println(new Date()+"==========333");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread2.start();
        for (int i = 0;i < 5;i++){
            System.out.println(new Date()+"========444");
        }*/

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1111");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2222");
            }
        }).start();*/

        /**
         * TODO 正则表达式
         */
        /*String a = "test";
        System.out.println(a.matches("^[a-z]*$"));*/

        /**
         * TODO 创建一个线程池对象
         * public static ExecutorService newFixedThreadPool(int nThreads)
         */
//        创建固定大小的线程池
        /*ExecutorService pool = Executors.newFixedThreadPool(2);*/
//        创建一个单线程的线程池
//        ExecutorService pool1 = Executors.newSingleThreadExecutor();
//        创建一个可缓存的线程池
//        ExecutorService pool2 = Executors.newCachedThreadPool();

        // 可以执行Runnable对象或者Callable对象代表的线程
        /*pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());*/

        //结束线程池
        /*pool.shutdown();*/


        final AppTest test = new AppTest();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        /*lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }*/

        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"得到了锁");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
