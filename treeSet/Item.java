package treeSet;

import java.util.Objects;

public class Item implements Comparable<Item>{

  private String description;
  private int partNumber;
  
  public Item(String aDescription, int aPartNumber) {
    description = aDescription;
    partNumber = aPartNumber;
  }

  public String getDescription() {
    return description;
  }

  public String toString() {
    return "[description = " + description + ", partNumber = " + partNumber;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (otherObject == null) return false;
    if (otherObject == this) return true;
    if (getClass() != otherObject.getClass()) return false;
    Item other = (Item) otherObject;
    return Objects.equals(description, other.description) && partNumber == other.partNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, partNumber);
  }

  public int compareTo(Item o) {
    int diff = Integer.compare(partNumber, o.partNumber);
    return diff != 0 ? diff : description.compareTo(o.description);
  }
}