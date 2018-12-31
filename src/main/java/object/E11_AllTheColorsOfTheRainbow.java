package object;


/******
 * 37页
 *  将AllThedColorsOfTheRainBoorw这个示例改写成一个程序，然后编译，运行
 *
 */
public class E11_AllTheColorsOfTheRainbow {
    int anIntegerRepresentingColors;

    void changeTheHueOfTheColor(int newHue) {
        anIntegerRepresentingColors = newHue;
    }

    public static void main(String[] args) {
        E11_AllTheColorsOfTheRainbow all =
                new E11_AllTheColorsOfTheRainbow();
        all.changeTheHueOfTheColor(27);
    }
}