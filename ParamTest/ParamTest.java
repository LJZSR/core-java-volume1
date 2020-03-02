package ParamTest;

public class ParamTest {
  public static void main(String[] args) {
    
    System.out.println("Testing tripleValue: ");
    double percent = 10;
    System.out.println("Before: " + percent);
    tripleValue(percent);
    System.out.println("After: " + percent);

    System.out.println("\nTesting tripleSalary");
    Employee harry = new Employee("Harry", 50000);
    System.out.println("Before: salary = " + harry.getSalary());
    tripleSalary(harry);
    System.out.println("After: salary = " + harry.getSalary());

    System.out.println("\nTesting swap");
    Employee a = new Employee("Alice", 70000); 
    Employee b = new Employee("Bob", 60000);
    System.out.println("Before swap: a = " + a.getName());
    System.out.println("Before swap: b = " + b.getName());
    swap(a, b);
    System.out.println("After swap: a = " + a.getName());
    System.out.println("After swap: b = " + b.getName());
    
  }
  public static void tripleValue(double x) {
    x *= 3;
    System.out.println("End of method: " + x);
  }
  public static void tripleSalary(Employee e) {
    e.raiseSalary(200);
    System.out.println("End of method: salary = " + e.getSalary());
  } 
  public static void swap(Employee x, Employee y) {
    Employee tmp = x;
    x = y;
    y = tmp;
    System.out.println("End of method: x = " + x.getName()); 
    System.out.println("End of method: y = " + y.getName());
  }
}

class Employee {
  private String name;
  private double salary;

  public Employee(String n, double s) {
    name = n;
    salary = s;
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public void raiseSalary(double byPercent) {
    double raise = salary * byPercent / 100;
    salary += raise;
  }
}