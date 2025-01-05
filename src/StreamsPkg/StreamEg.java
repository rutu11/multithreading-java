package StreamsPkg;

import java.util.*;
import java.util.stream.Collectors;

public class StreamEg {
    public static void main(String[] args) {

//      1. Filter and Collect:
//       Given a list of integers, filter out all even numbers and collect the odd numbers into a new list.
        List<Integer> nums = Arrays.asList(10,11,12,13,15,14,16);
        List<Integer> list = nums.stream()
                .filter(x -> x % 2 != 0)
                .toList();
        System.out.println(list);

//        Find Maximum:
//        From a list of StreamsPkg.Employee objects with id, name, and salary fields, find the employee with the highest salary.
        Employee emp1 = new Employee(1,"Rutuja", 10);
        Employee emp2 = new Employee(2, "Geetika", 23);
        Employee emp3 = new Employee(3, "Navneett", 43);

        List<Employee> empList = Arrays.asList(emp1, emp2, emp3);
        Optional<Employee> maxEmployee = empList.stream()
                .max((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(maxEmployee);

//        From a list of StreamsPkg.Employee objects with name and salary fields, calculate the average salary of all employees.
        Double avgSalary = empList.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avgSalary);

//        Group by Age:
//        Given a list of StreamsPkg.Person objects with name and age fields, group them by age.
        List<Person> personList = Arrays.asList(
                new Person("Rutuja",25),
                new Person("Yash", 15),
                new Person("Rob", 16),
                new Person("Robot", 15),
                new Person("Taniya", 25)
        );
        Map<Integer, List<Person>> collect = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(collect);

//        Find First Match:
//        From a list of strings, find the first string that starts with the letter "A".
        List<String> stringList = Arrays.asList("Rutuja", "Ankit", "Anna", "Yash");
        Optional<String> name = stringList.stream()
                .filter(x -> x.startsWith("A"))
                .findFirst();
        System.out.println(name.get());

//        From a list of strings, find all strings that:
//        Start with a specific letter (e.g., "A").
//                Are longer than 3 characters
        System.out.println(stringList.stream()
                .filter(x -> x.startsWith("A") && x.length() > 3)
                .toList());

//        Sum of Squares:
//        Calculate the sum of squares of all numbers in a list.
        List<Integer> values = Arrays.asList(1,2,3,4);
        Optional<Integer> reduce = values.stream()
                .map(x -> x * x)
                .reduce(Integer::sum);
        System.out.println(reduce.get());

//        Count Matching Elements:
//        Given a list of strings, count how many strings have a length greater than 5.
        List<String> strings = Arrays.asList("Rutuja", "Yash", "abcde", "Geetika");
        long count = strings.stream()
                .filter(x -> x.length() > 5)
                .count();
        System.out.println("count of strings > 5 " + count);

//        FlatMap Operation:
//        Given a list of lists of integers, flatten it into a single list of integers.
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(21, 34),
                Arrays.asList(53,25)
        );
        System.out.println(nestedList.stream()
                .flatMap(x -> x.stream())
                .toList());

//        Custom Sorting:
//        Sort a list of StreamsPkg.Student objects based on their grades, and if two grades are equal, sort by their names.
        List<Student> students = Arrays.asList(
                new Student("Rutuja", "A"),
                new Student("Geetika", "C"),
                new Student("Navneet", "B"),
                new Student("Yash", "C"),
                new Student("Gautami", "A")
        );

        List<Student> sortedStudents = students.stream()
                .sorted(Comparator.comparing(Student::getGrade)
                        .thenComparing(Student::getName))
                .collect(Collectors.toList());
        System.out.println(sortedStudents);

//        Group Students by Grade
//        From a list of StreamsPkg.Student objects with name and grade fields, group students by their grade.
        Map<String, List<String>> collect2 = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade,
                        Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(collect2);

//        Convert to Map:
//        Convert a list of StreamsPkg.Book objects with id and title fields into a map where the key is the id and the value is the title.
        List<Book> books = Arrays.asList(
                new Book(1,"anc"),
                new Book(2,"fasd"),
                new Book(4,"hdf")
        );

        Map<Integer, String> collect1 = books.stream()
                .collect(Collectors.toMap(Book::getId, book -> book.getTitle()));
        System.out.println(collect1);

//        Find Duplicates:
//        From a list of integers, find all duplicate elements.
        List<Integer> numsDup = Arrays.asList(21,34,21,45,44,45);
        System.out.println(numsDup.stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet()
                .stream().filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList()
        );
//        .map(entry -> entry.getKey())

//        From a list of strings (e.g., sentences), count the frequency of each word using streams.
        List<String> sentences = Arrays.asList("Hello World", "What is your name", "Your name in the world");
        System.out.println(
                sentences.stream()
                        .flatMap(sent -> Arrays.stream(sent.split(" ")))
                        .map(String::toLowerCase)
                        .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
        );
    }
}
