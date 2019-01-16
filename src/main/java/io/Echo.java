package io;//: io/Echo.java
// How to read from standard input.
// {RunByHand}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 549
 *
 * 通常我们会用readLine() 一次一行地读取输入，为此，我们将system.in 包装BufferedReader 来使用这
 * 要求我们必须用InputStreamReader 把System.in 转换成Reader ,下面这个例子将直接回显你所输入的第一行
 * <p>
 * <p>
 * <p>
 * 使用异常规范是因为readLine() 会抛出IOException ,注意，System.in 和大多数流一样，通常应该对它进行
 * 缓冲
 *
 *
 *
 * 1
 */
public class Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = stdin.readLine()) != null && s.length() != 0) {
            System.out.println(s);
        }
        // An empty line or Ctrl-Z terminates the program
    }
} ///:~
