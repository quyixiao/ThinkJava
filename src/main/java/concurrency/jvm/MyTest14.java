package concurrency.jvm;


import java.net.URL;
import java.util.Enumeration;


/****
 * 获取当前类的ClassLoader
 *  clazz.getClassLoader();
 * 获取当前线程上下文的ClassLoader
 *  Thread.currentThread().getContextClassLoader();
 * 获取系统的ClassLoader
 *  ClassLoader.getSystemClassLoader();
 * 获取调用者ClassLoader
 *  找小姐rManager.getCallerClassLoader();
 */
public class MyTest14 {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        String resoureName = "concurrency/jvm/Mytest1.class";
        Enumeration<URL> urls = classLoader.getResources(resoureName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        System.out.println("------------------------------");
        Class<?> clazz = MyTest14.class;
        System.out.println(clazz.getClassLoader());

        System.out.println("------------------------------");
        //null从启动类加载器中加载的，或者根类加载器加载的
        clazz = String.class;
        System.out.println(clazz.getClassLoader());

    }

}
