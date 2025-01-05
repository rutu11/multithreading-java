package LockingPkg;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockEx {
    private final Lock lock = new ReentrantLock(true);

    public void accessResource(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" Lock is acquired");
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName()+" lock is released");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessLockEx obj = new FairnessLockEx();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                obj.accessResource();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
    }
}
