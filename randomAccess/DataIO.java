package randomAccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataIO {
  public static void writeFixedString(String s, int length, DataOutput out) throws IOException{
    for (int i = 0; i < length; ++i) {
      char ch = 0;
      if (i < s.length()) {
        ch = s.charAt(i);
      }
      out.writeChar(ch);
    }
  }

  public static String readFixedString(int length, DataInput in) throws IOException{
    StringBuilder b = new StringBuilder(length);
    for (int i = 0; i < length; ++i) {
      char ch = in.readChar();
      if (ch != 0) {
        b.append(ch);
      }
    }
    return b.toString();
  }
}