package control;


/****
 * 70页
 *修改本章练习1 ,通过使用break关键字，使得程序在打印到99时退出，然后尝试使用return来达到目的
 *
 * 1
 */
public class E07_To98 {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i == 99)
                break;
            System.out.print(i + " ");
        }
    }
}