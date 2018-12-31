package typeinfo.coffee2;

import typeinfo.Coffee;

public class Americano extends Coffee {
  public static class Factory
  implements typeinfo.factory.Factory<Americano> {
    public Americano create() { return new Americano(); }
  }
}