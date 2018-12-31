package strings;//: strings/SimpleRead.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


/****
 * 309页
 *
 *
 *      到目前为止，人文件或标准输入读取数据还是一件相当痛苦的事情，一般的解决之道就是
 *  读入一行文本，对其进行分词，然后使用Integer，Double等类的各种解析方法解析数据
 *
 *
 */
public class SimpleRead {
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));

    public static void main(String[] args) {
        try {
            System.out.println("What is your name?");
            String name = input.readLine();
            System.out.println(name);
            System.out.println(
                    "How old are you? What is your favorite double?");
            System.out.println("(input: <age> <double>)");
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);
            double favorite = Double.parseDouble(numArray[1]);
            System.out.format("Hi %s.\n", name);
            System.out.format("In 5 years you will be %d.\n",
                    age + 5);
            System.out.format("My favorite double is %f.",
                    favorite / 2);
        } catch (IOException e) {
            System.err.println("I/O exception");
        }
    }
}



/* Output:
What is your name?
Sir Robin of Camelot
How old are you? What is your favorite double?
(input: <age> <double>)
22 1.61803
Hi Sir Robin of Camelot.
In 5 years you will be 27.
My favorite double is 0.809015.
*///:~
