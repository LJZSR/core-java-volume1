package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
  public static void main(String[] args) {
    String name;
    if (args.length > 0) name = args[0];
    else {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter class name (e.g. java.util.Date): ");
      name = in.next();
      in.close();
    }

    try {
      Class cl = Class.forName(name);
      Class superCl = cl.getSuperclass();
      String modifier = Modifier.toString(cl.getModifiers());
      if (modifier.length() > 0) {
        System.out.print(modifier + " ");
      }
      System.out.print("class " + name);
      if (superCl != null && superCl != Object.class) {
        System.out.print(" extends " + superCl.getName());
      }
      System.out.print("\n{\n");
      printContructors(cl);
      System.out.println();
      printMethods(cl);
      System.out.println();
      printFields(cl);
      System.out.println("}");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void printContructors(Class cl) {
    Constructor[] constructors = cl.getConstructors();

    for (Constructor c : constructors) {
      String name = c.getName();
      System.out.print("    ");
      String modifier = Modifier.toString(c.getModifiers());
      if (modifier.length() > 0) {
        System.out.print(modifier + " ");
      }
      System.out.print(name + "(");

      Class[] paramTypes = c.getParameterTypes();
      for (int j = 0; j < paramTypes.length; ++j) {
        if (j > 0) {
          System.out.print(", ");
        }
        System.out.print(paramTypes[j].getName());
      }
      System.out.println(");");
    }
  }
  
  public static void printMethods(Class cl) {
    Method[] methods = cl.getMethods();

    for (Method m : methods) {
      Class retType = m.getReturnType();
      String name = m.getName();

      System.out.print("    ");
      String modifiers = Modifier.toString(m.getModifiers());
      if (modifiers.length() > 0) {
        System.out.print(modifiers + " ");
      }
      System.out.print(retType.getName() + " " + name + "(");
      Class[] paramTypes = m.getParameterTypes();
      for (int j = 0; j < paramTypes.length; ++j) {
        if (j > 0) {
          System.out.print(", ");
        }
        System.out.print(paramTypes[j].getName());
      }
      System.out.println(");");
    }
  }

  public static void printFields(Class cl) {
    Field[] fields = cl.getDeclaredFields();

    for (Field f : fields) {
      Class type = f.getType();
      String name = f.getName();
      System.out.print("    ");
      String modifiers = Modifier.toString(f.getModifiers());
      if (modifiers.length() > 0) {
        System.out.print(modifiers + " ");
      }
      System.out.println(type.getTypeName() + " " + name + ";");
    }
  }
}