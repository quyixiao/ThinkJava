package strings;//: strings/BetterRead.java

import java.util.Scanner;


/*****
 *
 * Scannerl的s构造器可以接受任何类型的输入对象，包括File对象，同样，我们将在第18章中
 * 详细介绍File类，InputStream，String或者像引例中的Readable对象，ReadAble在java SE5中
 * 新加入的一个接口，表示，具有Read()方法的某种东西，前一个例子中的BufferdReader也归于这一类
 * ，有了Scanner，所有的输入，分词以及翻译的操作都隐藏在不同的类型中的next()方法中，普通的next()
 * 方法返回一个String，所有的基本类型除char之外，都有对应的next方法，包括，BigDecimal和BigInteger，
 * 所有的next()方法，用以判断下一个输入分词是否所需的类型。
 *
 * */
public class BetterRead {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(SimpleRead.input);
        System.out.println("What is your name?");
        String name = stdin.nextLine();
        System.out.println(name);
        System.out.println(
                "How old are you? What is your favorite double?");
        System.out.println("(input: <age> <double>)");
        int age = stdin.nextInt();
        double favorite = stdin.nextDouble();
        System.out.println(age);
        System.out.println(favorite);
        System.out.format("Hi %s.\n", name);
        System.out.format("In 5 years you will be %d.\n",
                age + 5);
        System.out.format("My favorite double is %f.",
                favorite / 2);
    }
}



/* Output:
What is your name?
Sir Robin of Camelot
How old are you? What is your favorite double?
(input: <age> <double>)
22
1.61803
Hi Sir Robin of Camelot.
In 5 years you will be 27.
My favorite double is 0.809015.
*///:~
