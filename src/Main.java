import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static final Logger log =
            Logger.getLogger(Main.class.getName());


    static void main() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1,5,7,5,8,2,2,9,1,1));

        // 1. Remove duplicates and sorted in reverse order

        List<Integer> result = list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();

        log.info(() -> "List: " + result);

        // 2. Find all odd numbers and return their squares

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        List<Integer> result1 = list1.stream()
                .filter(elem->elem%2!=0)
                .map(elem->elem*elem)
                .toList();

        log.info(() -> "Odd numbers squared: " + result1);

        // 3. Get 2nd and 3rd element form the list and return them in a list

        List<Integer> list2 = new ArrayList<>(Arrays.asList(10,20,30,40,50));

        List<Integer> result2 = list2.stream()
                .skip(1)
                .limit(2)
                .toList();

        log.info(() -> "Second and third elements: " + result2);

        // 4. Find 2nd highest number in a list

        List<Integer> list3 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 50));

        Optional<Integer> result3 = list3.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(1)
                .findFirst();

        // if second largest not present then it will throw exception
        result3.ifPresent(value1 ->
                log.info(() -> "Second highest number: " + value1));

        // 5. Divide numbers into even and odd

        List<Integer> list4 = List.of(1, 2, 3, 4, 5,6,7,8);

        Map<String, List<Integer>> result4 = list4.stream()
                .collect(Collectors.groupingBy(n->n%2==0 ? "Even" : "Odd"));

        // partitioningBy() always returns a Map<Boolean, List<T>> (2 groups only).
        //groupingBy() can create any number of groups based on the classifier function.

        log.info(() -> "Even/Odd grouping: " + result4);

        // 6. Find Longest String in a List

        List<String> list5 = List.of("java", "springboot", "restapi");

        String result5 = list5.stream()
                .max(Comparator.comparing(String::length))
                .orElse("");

        log.info(() -> "Longest String: " + result5);

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

        employee.ifPresent(emp ->
                log.info(() -> "First employee with salary > 50000: " + emp));

        // 8. Find top 2 highest paid employees

        List<Employee> employees1 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(2)
                .toList();

        log.info(() -> "Top 2 highest paid employees: " + employees1);

        // 9. Sort the employees by salary then name

        List<Employee> employees2 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)
                        .thenComparing(Employee::getName))
                        .toList();

        log.info(() -> "Employees sorted by salary then name: " + employees2);

        // 10. FInd the frequency of each element

        List<Integer> list6 = List.of(1,2,3,5,3,2,2,9,1,1);

        Map<Integer,Long> result6=list6.stream()
                .collect(Collectors.groupingBy(elem->elem,
                        Collectors.counting()));

        log.info(() -> "Frequency of each element: " + result6);

        // 11. Count how many employees are in each department

        Map<String, Long> employees3 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.counting()));

        log.info(() -> "Employee count by department: " + employees3);

        // 13. Find the average salary in each department

        Map<String, Double> employees4 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        log.info(() -> "Average salary by department: " + employees4);

        // 14. Find Highest Paid Employee from Every Department

        Map<String, Employee> collect = employees.stream()
                .collect(Collectors.toMap(Employee::getDepartment, Function.identity(), BinaryOperator.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        log.info(() -> "Highest paid employee from every department: " + collect);

        // 15. Find Duplicate Elements and Their Counts

        List<Integer> nums = List.of(1,2,3,2,4,1,5,2);

        Map<Integer, Long> collect1 = nums.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e->e.getValue()>1)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        log.info(() -> "Duplicate elements and counts: " + collect1);

        // 16. Find the common between 2 list

        List<Integer> listA = List.of(1,2,3,4);
        List<Integer> listB = List.of(3,4,5,6,7);

        List<Integer> results = listB.stream()
                .filter(listA::contains)
                .toList();                               // O(N^2)

        log.info(() -> "Common elements O(N²): " + results);

        Set<Integer> setA = new HashSet<>(listA);
        List<Integer> results1 = listB.stream()
                .filter(setA::contains)
                .toList();                            // O(N)

        log.info(() -> "Common elements O(N): " + results1);

        // 17. Flatten a list of lists & remove duplicates

        List<List<Integer>> listOfLists = new ArrayList<>();

        listOfLists.add(Arrays.asList(1, 2, 3, 4));
        listOfLists.add(Arrays.asList(2, 3, 5, 6));
        listOfLists.add(Arrays.asList(1, 4, 7, 8));
        listOfLists.add(Arrays.asList(3, 6, 8, 9));
        listOfLists.add(Arrays.asList(1, 2, 9, 10));

        List<Integer> resultList = listOfLists.stream()
                .flatMap(Collection::stream)
                .distinct()
                .toList();

        log.info(() -> "Flattened list with distinct elements: " + resultList);

        // 18. Print all employee names as fast as possible (parallel streams)

        employees.parallelStream()
                .map(Employee::getName)
                .forEach(name ->
                        log.info(() -> "Employee Name: " + name));

        // 19. Find Employees Whose Salary Is Greater Than Their Department Average Salary

        List<Employee> employees5 = List.of(
                new Employee(101, "John", "IT", 45000),
                new Employee(102, "Alice", "HR", 55000),
                new Employee(103, "Bob", "Finance", 70000),
                new Employee(104, "David", "IT", 48000),
                new Employee(105, "Emma", "Admin", 52000),
                new Employee(106, "Michael", "Finance", 65000),
                new Employee(107, "Sophia", "HR", 43000),
                new Employee(108, "James", "IT", 80000),
                new Employee(109, "Olivia", "Admin", 49000),
                new Employee(110, "William", "Finance", 90000)
        );

        Map<String, Double> deptAvgSalary  = employees5.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        log.info(() -> "Department average salary: " + deptAvgSalary);

        List<Employee> list7 = employees5.stream()
                .filter(e -> e.getSalary() > deptAvgSalary.get(e.getDepartment()))
                .toList();

        log.info(() -> "Employees above department average salary: " + list7);

    }
}
