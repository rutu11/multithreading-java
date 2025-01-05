package ExecutorsFrmwrk;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchEg {
    public static void main(String[] args) throws InterruptedException {
        int numOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numOfServices);
        CountDownLatch latch = new CountDownLatch(numOfServices);
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));

        latch.await();
        System.out.println("STarting main now..");
        executorService.shutdown();
    }

}
class DependentService implements Callable {
    private final CountDownLatch latch;
    public DependentService(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public Object call() {
        System.out.println(Thread.currentThread().getName()+ " service started.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(latch.getCount());
            latch.countDown();
        }
        return "ok";
    }
}
