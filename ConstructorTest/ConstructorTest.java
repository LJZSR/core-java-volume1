package ConstructorTest;

import java.util.*;

public class ConstructorTest {
  public static void main(String[] args) {
    Employee[] staff = new Employee[3];

    staff[0] = new Employee("Harry", 40000); 
    staff[1] = new Employee(60000);
    staff[2] = new Employee();

    for (Employee e : staff) {
      System.out.println("name: " + e.getName() + ", salary: " + e.getSalary() + ", id:" + e.getId());
    }
  }
}

class Employee {

  private static int nextId;

  private int id;
  private String name = "";
  private double salary;

  static {
    Random generator = new Random();
    nextId = generator.nextInt(1000);
  }
  
  {
    id = nextId;
    ++nextId;
    System.out.println("Doing initialization Block");
  }

  public Employee(String name, double salary) {
    System.out.println("Doing first constructor");
    this.name = name;
    this.salary = salary;
    System.out.println(name + " " + nextId);
  }

  public Employee(double salary) {
    this("Employee# " + nextId, salary);
    System.out.println("Doing second constructor");
  }

  public Employee() {
    System.out.println(nextId);
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public int getId() {
    return id;
  }
}