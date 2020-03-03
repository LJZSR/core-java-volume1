package equals;

import java.util.Objects;

public class Manager extends Employee {

  private double bonus;

  public Manager(String name, double salary, int year, int month, int day) {
    super(name, salary, year, month, day);
    bonus = 0;
  }

  @Override
  public double getSalary() {
    return super.getSalary() + bonus;
  }

  public void setBonus(double bonus) {
    this.bonus = bonus;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (!super.equals(otherObject)) return false;
    Manager other = (Manager)otherObject;
    return bonus == other.bonus;
  }

  @Override 
  public int hashCode() {
    return super.hashCode() + Objects.hash(bonus);
  }

  @Override 
  public String toString() {
    return super.toString() + "[bonus = " + bonus + "]";
  }
}