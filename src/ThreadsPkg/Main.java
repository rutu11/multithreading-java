package ThreadsPkg;

public class Main extends Thread {
    @Override
    public void run() {
        System.out.println("Running mainT run()");

        //run() cannot THROWS InterruptedException, so use try-catch
        try {
            for (int i=0; i<5; i++){
                System.out.println(Thread.currentThread().getName()+" i = "+i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread name "+Thread.currentThread().getName());
        Main mainT = new Main();
        mainT.start();

//        1sec = 1000 ms
        Thread.sleep(1000);
        System.out.println("mainT state TIMED_WAITING? "+mainT.getState());
        mainT.join();
        System.out.println("mainT state "+mainT.getState());

        FirstThread thread = new FirstThread();
        System.out.println("ThreadsPkg.FirstThread state "+thread.getState());
        thread.start();

        System.out.println("ThreadsPkg.FirstThread state "+thread.getState());

        System.out.println("-----------------------------");
        RunnableThreadEg runnThread = new RunnableThreadEg(); //NEW state
        Thread runT = new Thread(runnThread);
        runT.start(); //RUNNABLE state
//        runnThread.run(); //Ran by main thread.. how???
        System.out.println("main is done? "+Thread.currentThread().getState());
    }
}