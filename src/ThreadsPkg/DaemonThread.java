package ThreadsPkg;

public class DaemonThread extends Thread{
    @Override
    public void run() {
        while(true){
            System.out.println("Hello");
        }
    }

    //    User Thread -- threads which works for us
//    Daemon Thread -- runs in the background, JVM doesn't wait for this threads to end

    public static void main(String[] args) {
        DaemonThread t1 = new DaemonThread();
        t1.setDaemon(true);
        t1.start();

        //User Thread -- t2 will keep running..
//        ThreadsPkg.DaemonThread t2 = new ThreadsPkg.DaemonThread();
//        t2.start();
        System.out.println("ThreadsPkg.Main done");

    }
}
