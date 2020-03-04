package innerClass;

import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Date;

import javax.swing.Timer;
import javax.swing.JOptionPane;

public class InnerClassTest {
  public static void main(String[] args) {
    TalkingClock clock = new TalkingClock(10000, true);
    clock.start();

    JOptionPane.showMessageDialog(null, "Quit program?"); 
    System.exit(0);
  }
}

class TalkingClock {
  private int interval;
  private boolean beep;

  public TalkingClock(int interval, boolean beep) {
    this.interval = interval;
    this.beep = beep;
  }

  public void start() {
    Timer t = new Timer(interval, new TimePrinter());
    t.start();
  }

  public class TimePrinter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("At the tone, the time is " + new Date());
      if (beep) Toolkit.getDefaultToolkit().beep();
    }
  }
}