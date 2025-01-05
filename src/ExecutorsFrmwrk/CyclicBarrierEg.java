package ExecutorsFrmwrk;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierEg {
    public static void main(String[] args) {
        int numOfSystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numOfSystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("All systems are UP and running..");
            }
        });
        Thread webServer = new Thread(new SubSystem("WebServer", 3000, barrier));
        Thread messageServer = new Thread(new SubSystem("messageServer", 2000, barrier));
        Thread DBServer = new Thread(new SubSystem("DBServer", 3000, barrier));
        Thread cache = new Thread(new SubSystem("cache", 4000, barrier));

        webServer.start();
        messageServer.start();
        DBServer.start();
        cache.start();

        System.out.println("Main keeps running..");

    }
}
class SubSystem implements Runnable{
    private String name;
    private int initTime;
    private CyclicBarrier barrier;

    public SubSystem(String name, int initTime, CyclicBarrier barrier){
        this.name = name;
        this.initTime = initTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+" "+name+" is starting...");
            Thread.sleep(initTime);
            System.out.println(name+" is started.");
            System.out.println("Parties waiting: "+barrier.getNumberWaiting());
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }
}
