package randomAccess;

import java.time.LocalDate;

public class Employee {
  private String name;
  private double salary;
  private LocalDate hireDate;
  public static int NAME_SIZE = 40;
  public static int RECORD_SIZE = 100;

  public Employee(String name, double salary, int year, int month, int day) {
    this.name = name;
    this.salary = salary;
    this.hireDate = LocalDate.of(year, month, day);
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public LocalDate getHireDay() {
    return hireDate;
  }
}