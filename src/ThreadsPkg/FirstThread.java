package ThreadsPkg;

public class FirstThread extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " First Hello world!");
    }
}
