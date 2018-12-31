package reusing;

class ChessWithoutDefCtor/* extends BoardGame2*/ {
/*    ChessWithoutDefCtor () {
     System.out.println("ChessWithoutDefCtor constructor");
     super(11);
    }*/
}


/***
 *
 * 130页
 * 用Chess.java来证明前一段话
 *
 */
public class E06_ChessWithoutDefCtor {
    public static void main(String args[]) {
        new ChessWithoutDefCtor();
    }
} 