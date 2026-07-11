# Java Streams Interview Questions

A collection of commonly asked Java Stream API interview questions with solutions and explanations.

## Topics Covered

### Basic Stream Operations
- Remove Duplicates and Sort in Reverse Order
- Filter Odd Numbers and Return Squares
- Skip and Limit
- Find Second Highest Number
- Find Longest String

### Grouping & Partitioning
- Divide Numbers into Even and Odd
- Find Frequency of Each Element
- Count Employees by Department
- Find Average Salary by Department

### Employee-Based Questions
- Find First Employee with Salary > 50000
- Find Top 2 Highest Paid Employees
- Sort Employees by Salary and Name
- Find Highest Paid Employee from Each Department
- Find Employees Whose Salary Is Greater Than Their Department Average Salary

### Advanced Stream Questions
- Find Duplicate Elements and Their Counts
- Find Common Elements Between Two Lists
- Flatten Nested Lists and Remove Duplicates
- Parallel Stream Example

## Java Version

- Java 21
- Java 25 Compatible

## Concepts Used

- Stream API
- filter()
- map()
- flatMap()
- distinct()
- sorted()
- limit()
- skip()
- findFirst()
- groupingBy()
- partitioningBy()
- counting()
- averagingDouble()
- maxBy()
- collectingAndThen()
- Comparator
- Parallel Streams

## Project Structure

```
src
 ├── Main.java
 └── Employee.java
```

## Sample Questions

### Find Top 2 Highest Paid Employees

```java
employees.stream()
         .sorted(
             Comparator.comparingDouble(Employee::getSalary)
                       .reversed())
         .limit(2)
         .toList();
```

### Find Highest Paid Employee from Every Department

```java
employees.stream()
         .collect(Collectors.groupingBy(
                 Employee::getDepartment,
                 Collectors.collectingAndThen(
                         Collectors.maxBy(
                                 Comparator.comparingDouble(Employee::getSalary)),
                         Optional::get
                 )
         ));
```

