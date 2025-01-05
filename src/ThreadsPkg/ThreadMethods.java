package ThreadsPkg;

public class ThreadMethods extends Thread{
//THREAD METHODS
//    1. start() -- ThreadClass
//    2. run()
//    3. sleep()
//    4. join()
//    5. setPriority()
//    6. interrupt()
//    7. yield()

    public ThreadMethods(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
        System.out.println(Thread.currentThread().getName()+" PRIORITY "+ Thread.currentThread().getPriority());
        for(int i=0; i<=5; i++){
            System.out.println(Thread.currentThread().getName()+" count= "+i);
            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep interrupted "+ e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ThreadsPkg.MyThread thread = new ThreadsPkg.MyThread();
        //Naming your THREAD thru constructor
        ThreadMethods thread = new ThreadMethods("Rutuja");
        thread.start();
        thread.join();
        System.out.println("In main thread");

        ThreadMethods t1 = new ThreadMethods("MIN_PRIORITY");
        ThreadMethods t2 = new ThreadMethods("MAX_PRIORITY");
        ThreadMethods t3 = new ThreadMethods("NORM_PRIORITY");

        t1.setPriority(MIN_PRIORITY);
        t2.setPriority(MAX_PRIORITY);
        t3.setPriority(NORM_PRIORITY);

        t1.start();
        t1.interrupt();
        t2.start();
        t3.start();

    }
}
