package equals;

import java.time.LocalDate;
import java.util.*;

public class Employee {
  private String name;
  private double salary;
  private LocalDate hireDay;

  public Employee(String name, double salary, int year, int month, int day) {
    this.name = name;
    this.salary = salary;
    this.hireDay = LocalDate.of(year, month, day);
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public LocalDate getHireDay() {
    return hireDay;
  }

  public void raiseSalary(double byPercent) {
    salary += salary * byPercent / 100;
  }

  @Override
  public boolean equals(Object othrObject) {

    if (this == othrObject) return true;

    if (othrObject == null) return false;

    if (getClass() != othrObject.getClass()) return false;

    Employee other = (Employee)othrObject;

    return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, salary, hireDay);
  }

  @Override
  public String toString() {
    return getClass().getName() + "[name = " + name + ", salary = " + salary + ", hireDay = " + hireDay + "]";
  }
}