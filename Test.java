import java.util.*;

class Employee implements Comparable<Employee> {
    String name;
    int salary;

    // This is the constructor for employee class.
    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    //MISSING Code in the task.
    /**
     * This method compares employees based on salary..
     */
    @Override
    public int compareTo(Employee e) {
        return this.salary - e.salary; 
    }

    // This is the method to display employee's info.
    @Override
    public String toString() {
        return name + " - " + salary;
    }
}

/***
 * This is the Main class. 
 */
public class Test{
    /***
     * This is the main method. 
     */
    public static void main(String[] args) {


        List<Employee> list = new ArrayList<>();

        list.add(new Employee("Ali", 50000));
        list.add(new Employee("Sara", 70000));
        list.add(new Employee("Ahmed", 60000));

        // Sorting the list using Comparable
        Collections.sort(list);

        // Printing the sorted list.
        for (Employee e : list) {
            System.out.println(e);
        }
    }
}