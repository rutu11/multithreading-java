package LambdaExp;

public class Main {
    public static void main(String[] args) {
        Student enggStudent = new Student() {
            @Override
            public String getBio(String name) {
                return name+" is a Engineering Student";
            }
        };
         //Lambda expression
        Student lawStudent = (name) -> name+" is a Law Student";

//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello World");
//            }
//        };
//        Thread task1 = new Thread(task);
//        task1.start();

//        USING LAMBAD EXP
        //Passing implementation class of Runnable
        Thread task1 = new Thread(() -> System.out.println("Hello World"));

        Thread multiLineTask = new Thread(() -> {
           for(int i=0; i<5; i++){
               System.out.println("i= "+i);
           }
        });
        multiLineTask.start();

        System.out.println(enggStudent.getBio("Ram"));
        System.out.println(lawStudent.getBio("Rutuja"));

    }
}
