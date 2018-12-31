package io.xfiles;//: io/xfiles/ThawAlien.java
// Try to recover a serialized file without the
// class of object that's stored in that file.
// {RunByHand}

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
 * 这个程序不介但能捕获和处理异常，而且将异常抛出到main() 方法之外，
 */
public class ThawAlien {
  public static void main(String[] args) throws Exception {
    ObjectInputStream in = new ObjectInputStream(
      new FileInputStream(new File("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/", "X.file")));
    Object mystery = in.readObject();
    System.out.println(mystery.getClass());
  }
} /* Output:
class Alien
*///:~
