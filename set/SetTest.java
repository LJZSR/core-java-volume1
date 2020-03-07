package set;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;

public class SetTest {
  public static void main(String[] args) {
    HashSet<String> words = new HashSet<>();
    long totalTime = 0;
    try (Scanner in = new Scanner(System.in)) {
      while (in.hasNext()) {
        String word = in.next();
        long callTime = System.currentTimeMillis();
        words.add(word);
        totalTime += System.currentTimeMillis() - callTime;
      }
    }
    Iterator<String> iter = words.iterator();
    for (int i = 1; i <= 20 && iter.hasNext(); ++i) {
      System.out.println(iter.next()) ;
    }
    System.out.println("...");
    System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.") ;
  }
}