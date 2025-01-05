package LockingPkg;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
// 4 Methods of LOCK
//    1. lock.lock() -- same like synchronized keyword, waits indefinitely
//    2. lock.tryLock() -- immediately checks for the lock
//    3. lock.tryLock(TIME, UNIT) with time -- waits for the specified time and tries to acquire the lock
//    4. lock.unlock()
//    5. lock.lockInterruptibly() --> if used then if any other threads comes and is allowed to to interrupt the current lock
//    if it is acquired for indefinite time.. then the current thread can interrupt and acquire the lock

    private int balance = 100;
    private final Lock lock = new ReentrantLock(); //using final so that no one can change the lock

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName()+" trying to withdraw "+amount);
        try{
//            if(lock.tryLock()){
            if(lock.tryLock(2000, TimeUnit.MILLISECONDS)){
                if(balance >= amount){
                    try{
                        System.out.println(Thread.currentThread().getName()+" Acquired the lock and proceeding to withdraw the amount");
                        balance -= amount;
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+" Amount withdrawn, new bal: "+balance);
                    } catch (Exception e) {
                      //if interrupted by any other thread then restore the interrupted state
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else{
                    System.out.println(Thread.currentThread().getName()+" insufficient balance");
                }
            } else{
                System.out.println(Thread.currentThread().getName()+" could not acquire the lock");
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }
        if(Thread.currentThread().isInterrupted()){
            System.out.println(Thread.currentThread().getName()+" task was interrupted");
        }
    }

}
