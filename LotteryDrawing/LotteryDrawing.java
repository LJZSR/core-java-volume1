package LotteryDrawing;

import java.util.Scanner;
import java.util.Arrays;

public class LotteryDrawing {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("How many number do you need to draw?");
    int k = in.nextInt();

    System.out.println("What is the highest number you can draw?");
    int n = in.nextInt();

    in.close();

    int[] numbers = new int[n];
    int[] result = new int[k];
    
    for (int i = 0; i < n; ++i) {
      numbers[i] = i+1;
    }

    for (int i = 0; i < k; ++i) {
      int r = (int)(Math.random() * n);
      result[i] = numbers[r];
      numbers[r] = numbers[n-1];
      --n;
    }
    Arrays.sort(result);
    System.out.println("Bet the following combination. It 'll make you rich!");
    for (int i : result) {
      System.out.println(i);
    }
  }
}