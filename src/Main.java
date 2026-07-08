import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static void main() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1,5,7,5,8,2,2,9,1,1));

        // 1. Remove duplicates and sorted in reverse order

        List<Integer> result = list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.println(result);

        // 2. Find all odd numbers and return their squares

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        List<Integer> result1 = list1.stream()
                .filter(elem->elem%2!=0)
                .map(elem->elem*elem)
                .toList();

        System.out.println(result1);

        // 3. Get 2nd and 3rd element form the list and return them in a list

        List<Integer> list2 = new ArrayList<>(Arrays.asList(10,20,30,40,50));

        List<Integer> result2 = list2.stream()
                .skip(1)
                .limit(2)
                .toList();

        System.out.println(result2);

        // 4. Find 2nd highest number in a list

        List<Integer> list3 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 50));

        Optional<Integer> result3 = list3.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(1)
                .findFirst();

        // if second largest not present then it will throw exception
        result3.ifPresent(System.out::println);

        // 5. Divide numbers into even and odd

        List<Integer> list4 = List.of(1, 2, 3, 4, 5,6,7,8);

//        Map<Boolean, List<Integer>> result = list.stream()
//                        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        Map<String, List<Integer>> result4 = list4.stream()
                .collect(Collectors.groupingBy(n->n%2==0 ? "Even" : "Odd"));

        // partitioningBy() always returns a Map<Boolean, List<T>> (2 groups only).
        //groupingBy() can create any number of groups based on the classifier function.

        System.out.println(result4);

        // 6. Find Longest String in a List

        List<String> list5 = List.of("java", "springboot", "restapi");

        String result5 = list5.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");

        System.out.println(result5);

        // 7. Find the first employee whose salary is greater than 50000

        List<Employee> employees = List.of(
                new Employee(101, "John", "IT", 45000),
                new Employee(102, "Alice", "HR", 55000),
                new Employee(103, "Bob", "Finance", 70000),
                new Employee(104, "David", "IT", 48000),
                new Employee(105, "Emma", "Admin", 52000),
                new Employee(105, "Abha", "Admin", 52000),
                new Employee(106, "Michael", "Finance", 65000),
                new Employee(107, "Sophia", "HR", 43000),
                new Employee(108, "James", "IT", 80000),
                new Employee(109, "Olivia", "Admin", 49000),
                new Employee(110, "William", "Finance", 90000)
        );

        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .findFirst();

        employee.ifPresent(System.out::println);

        // 8. Find top 2 highest paid employees

        List<Employee> employees1 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(2)
                .toList();

        System.out.println(employees1);

        // 9. Sort the employees by salary then name

        List<Employee> employees2 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)
                        .thenComparing(Employee::getName))
                        .toList();

        System.out.println(employees2);

        // 10. FInd the frequency of each element

        List<Integer> list6 = List.of(1,2,3,5,3,2,2,9,1,1);

        Map<Integer,Long> result6=list6.stream()
                .collect(Collectors.groupingBy(elem->elem,
                        Collectors.counting()));

        System.out.println(result6);


    }
}
