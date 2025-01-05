package SynchrPkg;

public class Counter {
    private int count = 0;

    //No locking, multiple threads can increment the counter at the same time
//    public void increment() {
//        count++;
//    }

    //Locking the method - 1 thread at a time
//    public synchronized void increment() {
//        count++;
//    }

    public void increment() {
//        System.out.println(Thread.currentThread().getName()+" "+ count);

        //Blocking only a specific block of code
        synchronized (this){
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
