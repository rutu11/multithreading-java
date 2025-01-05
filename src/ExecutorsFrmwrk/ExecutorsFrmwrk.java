package ExecutorsFrmwrk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsFrmwrk {
    public static void main(String[] args) {
        //VARIABLE fixed length thread pool
        long startTime = System.currentTimeMillis();

        //1. CREATING THREAD
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for(int i=1; i<10; i++){
            int finalI = i;
            //2. STARTING & RUNNING TASK
            executorService.submit(() -> System.out.println("i: "+finalI+"= "+factorial(finalI)) );
        }

        //3. DESTROYING THREAD
        executorService.shutdown(); //Main Thread do not waits for shutdown
//        executorService.shutdownNow();

        //To keep waiting for all tasks to complete
        try {
//            executorService.awaitTermination(100, TimeUnit.MILLISECONDS); //Waits for 100ms

            while(!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)){ //Infinite WAITING
                System.out.println("Waiting... ");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total time required(ms): "+(System.currentTimeMillis() - startTime));
    }

    public static long factorial(int num){
        long result = 1;
        for(int i=1; i<=num; i++){
            result *= i;
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return result;
    }
}
