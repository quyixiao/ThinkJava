//: net/mindview/util/CollectionData.java
// A Collection filled with data using a generator object.
package net.util;

import net.mindview.util.Generator;

import java.util.ArrayList;

public class CollectionData<T> extends ArrayList<T> {
  public CollectionData(Generator<T> gen, int quantity) {
    for(int i = 0; i < quantity; i++)
      add(gen.next());
  }
  // A generic convenience method:
  public static <T> net.mindview.util.CollectionData<T>
  list(Generator<T> gen, int quantity) {
    return new net.mindview.util.CollectionData<T>(gen, quantity);
  }
} ///:~
