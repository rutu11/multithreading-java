package ExecutorsFrmwrk;

import java.util.concurrent.*;

public class FutureEg {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(20);
            return 44;
        });

        Integer i = null;
        try {
            i = future.get(10, TimeUnit.MILLISECONDS);
            System.out.println(future.isDone());
            System.out.println(i);
        } catch (InterruptedException | TimeoutException e) {
            System.out.println("Exception occured: "+e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }

}
