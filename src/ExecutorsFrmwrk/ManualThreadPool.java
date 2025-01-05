package ExecutorsFrmwrk;

public class ManualThreadPool {
    public static void main(String[] args) {
        //Creating Threads array
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[9];
        for(int i=1; i<10; i++) {
            int finalI = i;
            //Creating each thread for each factorial
            threads[i - 1] = new Thread(() -> {
                long result = factorial(finalI);
                System.out.println("i: "+finalI+"= "+result);
            });
            threads[i-1].start();
        }

        for(Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Total time required(ms): "+(System.currentTimeMillis() - startTime));
    }

    public static long factorial(int num){
        int result = 1;
        for(int i=1; i<=num; i++){
            result *= i;
        }
        return result;
    }
}
