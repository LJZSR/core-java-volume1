package textFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
  public static void main(String[] args) {
    Employee[] staff = new Employee[3];
    staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
    staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
    staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

    try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")){
      writeData(staff, out);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
      Employee[] newStaff = readData(in);
      for (Employee e : newStaff) {
        System.out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeData(Employee[] staff, PrintWriter out) throws IOException {
    out.println(staff.length);
    for (Employee e : staff) {
      writeEmployee(e, out);
    }
  }

  private static void writeEmployee(Employee e, PrintWriter out) throws IOException {
    out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
  }

  private static Employee[] readData(Scanner in) throws IOException{
    int n = in.nextInt();
    in.nextLine();
    Employee[] employees = new Employee[n];
    for (int i = 0; i < n; ++i) {
      employees[i] = readEmployee(in);
    }
    return employees;
  }

  private static Employee readEmployee(Scanner in) throws IOException {
    String line = in.nextLine();
    String[] tokens = line.split("\\|");
    String name = tokens[0];
    double salary = Double.parseDouble(tokens[1]);
    LocalDate hireDate = LocalDate.parse(tokens[2]);
    int year = hireDate.getYear();
    int month = hireDate.getMonthValue();
    int day = hireDate.getDayOfMonth();
    return new Employee(name, salary, year, month, day);
  }
}