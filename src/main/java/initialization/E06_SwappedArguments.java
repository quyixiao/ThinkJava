package initialization;


/*****
 * 83页
 * 修改前一个练习的程序，让两个重载方法各自接受两个不同类型的参数，但
 * 二者顺序相反，难其是否工作
 *
 *
 * 1
 */
public class E06_SwappedArguments {
    public static void main(String args[]) {
        Dog2 dog = new Dog2();
        dog.bark(1, 2.2);
        dog.bark(2.2, 1);
    }
}
