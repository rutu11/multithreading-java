package LockingPkg;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    private int count = 0;

//  Multiple reads at the same time BUT WRITE AT A time
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try{
            count++;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try{
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter readWriteCounter = new ReadWriteCounter();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=10; i++) {
                    System.out.println(Thread.currentThread().getName() + " is reading the value " + readWriteCounter.getCount());
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
               for(int i=1; i<=10; i++){
                   readWriteCounter.increment();
                   System.out.println(Thread.currentThread().getName()+" counter is incremented");
               }
            }
        };

        Thread t1 = new Thread(writeTask, "Rutuja");
        Thread t2 = new Thread(writeTask, "Geetika");

        Thread t4 = new Thread(readTask, "Rutuja");
        Thread t5 = new Thread(readTask, "Geetika");
        Thread t6 = new Thread(readTask, "Yash");

        //Write threads
        t1.start();
        t2.start();

        //Read threads
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t4.join();
        t5.join();
        t6.join();
        System.out.println("Final count "+ readWriteCounter.getCount());
    }
}
