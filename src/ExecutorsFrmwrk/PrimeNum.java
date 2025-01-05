package ExecutorsFrmwrk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Math.sqrt;

public class PrimeNum {

    public static boolean isPrime(int num){
       if(num <= 1) return false;
        for(int i=2; i< sqrt(num); i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int threads = 4;
        int start=1, end=50;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        int chunkSize = (end - start +1)/threads;

//        1 to 50 -- 3 threads
//        chunkSize = 50-1+1/3 = 16 --> 1 to 16, 16 to 32, 32 to 50 ??
        List<Future<List<Integer>>> result = new ArrayList<>();

        for(int i=0; i<threads; i++){
            int rangeStart = start + i*chunkSize;
            int rangeEnd = (i == threads-1) ? end : rangeStart+chunkSize-1;

            Callable<List<Integer>> task = () -> {
                List<Integer> primes = new ArrayList<>();

                System.out.println("rangeStart  "+rangeStart+" rangeEnd "+rangeEnd+" by "+Thread.currentThread().getName());
                for(int num = rangeStart; num < rangeEnd; num++){
                    if(isPrime(num)){
                        primes.add(num);
                    }
                }
                System.out.println("primes  "+primes);
                return primes;
            };
            result.add(executorService.submit(task));
//            System.out.println("result "+executorService.submit(task));
            Thread.sleep(1000);
        }

        List<Integer> allPrimes = new ArrayList<>();
        for (Future<List<Integer>> future : result) {
            allPrimes.addAll(future.get());
        }

        System.out.println("Prime no. "+allPrimes);
        executorService.shutdown();
    }
}
