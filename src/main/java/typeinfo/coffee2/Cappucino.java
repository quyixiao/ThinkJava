package typeinfo.coffee2;

import typeinfo.Coffee;

public class Cappucino extends Coffee {
  public static class Factory
  implements typeinfo.factory.Factory<Cappucino> {
    public Cappucino create() { return new Cappucino(); }
  }
}