package randomAccess;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessTest {
  public static void main(String[] args) {
    Employee[] staff = new Employee[3];
    staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
    staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
    staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

    try (DataOutputStream out = new DataOutputStream(new FileOutputStream("emplyee.dat"))) {
      for (Employee e : staff) {
        writeData(e, out);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try (RandomAccessFile in = new RandomAccessFile("emplyee.dat", "rw")) {
        int n = (int) (in.length()/Employee.RECORD_SIZE);
        Employee[] newStaff = new Employee[n];
        
        for (int i = n-1; i >= 0; --i) {
          in.seek(i * Employee.RECORD_SIZE);
          newStaff[n-i-1] = readData(in);
        }

        for (Employee e : newStaff) {
          System.out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeData(Employee e, DataOutputStream out) throws IOException{
    DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
    out.writeDouble(e.getSalary());
    out.writeInt(e.getHireDay().getYear());
    out.writeInt(e.getHireDay().getMonthValue());
    out.writeInt(e.getHireDay().getDayOfMonth());
  }

  private static Employee readData(RandomAccessFile in) throws IOException{
    String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
    double salary = in.readDouble();
    int year = in.readInt();
    int month = in.readInt();
    int day = in.readInt();
    return new Employee(name, salary, year, month, day);
  }
}