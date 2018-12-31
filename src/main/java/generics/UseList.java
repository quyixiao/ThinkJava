package generics;//: generics/UseList.java
// {CompileTimeError} (Won't compile)

import java.util.List;


/***
 * 403页
 *
 *
 *
 * 1
 *
 *
 *  下面的程序是不能编译的，即使编译它是和种合理的尝试
 *
 *
 *
 * @param <W>
 * @param <T>
 */
public class UseList<W, T> {


    //void f(List<T> v) {}
    void f(List<W> v) {



    }
} ///:~
