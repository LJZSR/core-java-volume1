package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.File;

public class ThreadPoolTest {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)){
      System.out.println("Enter base directory (e.g. /opt/jdk1.8.0/src)"); 
      String directory = in.nextLine();
      System.out.println("Enter keyword (e.g. volatile): ");
      String keyword = in.nextLine();

      ExecutorService pool = Executors.newCachedThreadPool();
      MatchCount counter = new MatchCount(new File(directory), keyword, pool);
      Future<Integer> result = pool.submit(counter);

      try {
        System.out.println(result.get() + " matching files.");
      } catch (Exception e) {
        e.printStackTrace();
      }

      pool.shutdown();
      int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
      System.out.println("largest pool size=" + largestPoolSize);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class MatchCount implements Callable<Integer> {
  private File directory;
  private String keyword;
  private ExecutorService pool;

  public MatchCount(File directory, String keyword, ExecutorService pool) {
    this.directory = directory;
    this.keyword = keyword;
    this.pool = pool;
  }

  public Integer call() {
    int count = 0;
    try {
      File[] files = directory.listFiles();
      List<Future<Integer>> results = new ArrayList<>();

      for (File file : files) {
        if (file.isDirectory()) {
          MatchCount counter = new MatchCount(file, keyword, pool);
          Future<Integer> task = pool.submit(counter);
          results.add(task);
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  public boolean search(File file, String keyword) {
    try {
      try(Scanner in = new Scanner(file, "UTF-8")) {
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