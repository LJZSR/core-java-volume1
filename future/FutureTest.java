package future;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

import java.io.File;

public class FutureTest {
  public static void main(String[] args) {
    try(Scanner in = new Scanner(System.in)) {
      System.out.println("Enter base directory (e.g. /opt/jdk1.8.0/src)"); 
      String directory = in.nextLine();
      System.out.println("Enter keyword (e.g. volatile): ");
      String keyword = in.nextLine();
      MatchCounter counter = new MatchCounter(new File(directory), keyword);
      FutureTask<Integer> task = new FutureTask<>(counter);
      Thread t = new Thread(task);
      t.start();
      try {
        System.out.println(task.get() + " matching files.");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

class MatchCounter implements Callable<Integer> {
  private File directory;
  private String keyword;

  public MatchCounter(File directory, String keyword) {
    this.directory = directory;
    this.keyword = keyword;
  }

  public Integer call() {
    int count = 0;
    try {
      File[] files = directory.listFiles();
      List<Future<Integer>> results = new ArrayList<>();

      for (File file : files) {
        if (file.isDirectory()) {
          MatchCounter counter = new MatchCounter(file, keyword);
          FutureTask<Integer> task = new FutureTask<>(counter);
          results.add(task);
          Thread t = new Thread(task);
          t.start();
        }
        else {
          if (search(file, keyword)) {
            count++;
          }
        }
      }
      for (Future<Integer> result : results) {
        try {
          count += result.get();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    catch(InternalException e) {
      e.printStackTrace();
    }
    return count;
  }

  public boolean search(File file, String keyword) {
    try {
      try(Scanner in = new Scanner(file, "UTF-8"))) {
        boolean found = false;
        while (in.hasNextLine()) {
          String line = in.nextLine();
          if (line.contains(keyword)) found = true;
        }
        return found;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}