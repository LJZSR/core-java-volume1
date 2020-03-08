package synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
  private final double[] accounts;
  private Lock bankLock;
  private Condition sufficienFunds;

  public Bank(int n, double initialBalance) {
    accounts = new double[n];
    Arrays.fill(accounts, initialBalance);
    bankLock = new ReentrantLock();
    sufficienFunds = bankLock.newCondition();
  }

  public void transfer(int from, int to, double amount) throws InterruptedException {
    bankLock.lock();
    try {
      while (accounts[from] < amount) {
        sufficienFunds.await();
      }
      System.out.print(Thread.currentThread());
      accounts[from] -= amount;
      accounts[to] += amount;
      System.out.printf(" %10.2f from %d to %d", amount, from, to);
      System.out.printf(" Total balance: %10.2f\n", getTotalBalance());
      sufficienFunds.signalAll();
    }
    finally {
      bankLock.unlock();
    }
  }

  public double getTotalBalance() {
    bankLock.lock();
    try {
      double sum = 0;
      for (double a : accounts) {
        sum += a;
      }
      return sum;
    }
    finally {
      bankLock.unlock();
    }
  }

  public int size() {
    return accounts.length;
  }
}