package ThreadsPkg;

public class RunnableThreadEg implements Runnable{

    @Override
    public void run() {
//        System.out.println("ThreadsPkg.RunnableThreadEg " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " Hello from runnable");
    }
}
