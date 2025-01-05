package ExecutorsFrmwrk;

import java.util.concurrent.*;

public class ExecutorEg {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable callable = () -> 3+4;
        Future callSubmit = executorService.submit(callable);
        System.out.println("callSubmit "+callSubmit.get());

        Runnable runn = () -> System.out.println("Execute Runnable");
        Future<?> runSubmit = executorService.submit(runn);
        System.out.println("runSubmit "+runSubmit.get()); // nothing returned.. Main thread is waiting for it's completion

        Future<String> submit = executorService.submit(() -> System.out.println("I am runnable"), "Task completed");
        System.out.println("Msg after submit: "+submit.get());

        executorService.shutdown();
        System.out.println("is shutdown? : "+executorService.isShutdown());
        System.out.println("is terminated? : "+executorService.isTerminated());
    }
}
