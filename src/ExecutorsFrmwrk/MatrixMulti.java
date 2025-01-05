package ExecutorsFrmwrk;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMulti {
    public static void main(String[] args) throws InterruptedException {
        int[][] mat1 = {{1,5},
                        {4,5}};
        int[][] mat2 = {{3,2},
                        {1,4}};

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        int[][] result = new int[mat1.length][mat2[0].length];

        for(int row=0; row < mat1.length; row++){
            int currentRow = row;
            Callable<Void> callTask = () -> {
                for(int i=0; i< mat2[0].length; i++){
                    for(int j=0; j< mat2.length; j++){
                        result[currentRow][i] += mat1[currentRow][j] * mat2[i][j];
                    }
                    System.out.println(result[currentRow][i]);
                }
                return null;
            };
            executorService.submit(callTask);
        }
        executorService.shutdown();
        while (!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {
            System.out.println("Waiting..");
        }
        System.out.println("Result Matrix:");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

}
