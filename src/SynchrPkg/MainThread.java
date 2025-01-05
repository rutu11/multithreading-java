package SynchrPkg;

public class MainThread {
    public static void main(String[] args) {
        //same obj of counter is shared by BOTH the threads
        Counter counter = new Counter();
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (Exception e){}
        System.out.println(counter.getCount());

    }
}
