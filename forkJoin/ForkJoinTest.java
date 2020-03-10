package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {
  public static void main(String[] args) {
    final int SIZE = 100000000;
    double[] values = new double[SIZE];
    for (int i = 0; i < SIZE; ++i) {
      values[i] = Math.random();
    }
    Counter counter = new Counter(values, 0, SIZE, x -> x>0.5);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(counter);
    System.out.println(counter.join());

  }
}

class Counter extends RecursiveTask<Integer> {
  public static final int THRESHOLD = 1000;
  private double[] values;
  private int from;
  private int to;
  private DoublePredicate filter;

  public Counter(double[] values, int from, int to, DoublePredicate filter) {
    this.values = values;
    this.from = from;
    this.to = to;
    this.filter = filter;
  }

  protected Integer compute() {
    if (to - from < THRESHOLD) {
      int count = 0;
      for (int i = from; i < to; ++i) {
        if (filter.test(values[i])) count++;
      }
      return count;
    }
    else {
      int mid = (from + to) / 2;
      Counter first = new Counter(values, from, mid, filter);
      Counter second = new Counter(values, mid, to, filter);
      invokeAll(first, second);
      return first.join() + second.join();
    }
  }
}