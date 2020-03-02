package LotteryArray;

public class LotteryArray {
  public static void main(String[] args) {
    final int MAX_N = 10;
    int[][] odds = new int[MAX_N+1][];
    for (int i = 0; i <= MAX_N; ++i) {
      odds[i] = new int[i+1];
    }
    for (int n = 0; n < odds.length; ++n) {
      for (int k = 0; k < odds[n].length; ++k) {
        int lotteryOdds = 1;
        for (int i = 1; i <= k; ++i) {
          lotteryOdds = lotteryOdds * (n-i+1) / i;
        }
        odds[n][k] = lotteryOdds;
      }
    }
    for (int[] row : odds) {
      for (int odd : row) {
        System.out.printf("%4d", odd);
      }
      System.out.println();
    }
  }
}