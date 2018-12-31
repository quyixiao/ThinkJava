package strings;


/******
 *
 * 297页
 *
 *
 * 参考 java.util.regex.Pattern的文档，用下划线替换Splitting.knights中的
 * 所有元音字母
 *
 *
 * 1
 */
public class E09_Replacing2 {
    public static void main(String[] args)

    {
        System.out.println(Splitting.knights.replaceAll("(?i)[aeiou]", "_"));


        double a = 20500  -  842.02;
        System.out.println(a);
        double b = 14778.21 + 4800;
        System.out.println(b);

    }
}