package reusing;//: reusing/Chess.java
// Inheritance, constructors and arguments.
import static net.mindview.util.Print.print;

class Game {
  Game(int i) {
    print("Game constructor");
  }
}

class BoardGame2 extends Game {
  BoardGame2(int i) {
    super(i);
    print("BoardGame constructor");
  }
}

public class Chess extends BoardGame2 {
  Chess() {
    super(11);
    print("Chess constructor");
  }
  public static void main(String[] args) {
    Chess x = new Chess();
  }
} /* Output:
Game constructor
BoardGame constructor
Chess constructor
*///:~
