package io;//: io/Logon.java
// Demonstrates the "transient" keyword.

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


/**
 *
 * transient 关键字
 *
 * 当我们对序列化进行控制时，可能某个特定的对象不想让Java序列化机制自动保存与恢复，如果子对象表示的是我们
 * 不希望将其序列化的敏感信息，通常就会面临这种情况，即使对象中的这些信息是private属性,一经序列化处理
 * ，人们就可以通过读取文件或者拦截网络传输的方式为访问到它；
 *
 *
 * 有一种方法可以防止对象的敏感的部分的属性，就是将类实现Exenrnalizable ,如前面所示，这样一来
 * ，没有任何的东西可以自动序列化，并可以在writeExtenal()内部只能所需部分进行显示的操作和序列化
 *
 *
 * 然而，如果我们正在操作的是一个Serializable对象，那么所有的序列化操作都会自动的进行，为了能够予
 * 以控制，可以用transient 关键字逐个的关闭序列化，它的意思是，不用麻烦你保存或恢复数据， 我会自己处理的
 *
 *
 *
 * 可以看到，其中的date 和 username 域是一般的，所以它们会被自动的序列化，而pass 是transient的，所以不会被自动的
 * 保存到磁盘中，另外的，自动序列化也不会尝试去在恢复他，当对象，但是null引用会被自动转换，成字符串null。
 *
 * 我们还可以实现，date 字段存储了到磁盘上被恢复的了出来，而且没有再重新生成，由于Externalizable 对象不
 * 保存能保存它们的任何字段，如果不是所以transient 关键字只能和Serializable 对象一起使用。
 *
 *
 * 如果不是特别的坚持实现Externalizable的接口，那么还有另一个种方法，我们可以实现Serivaliz 接口，并添加（
 * 注意我说的是添加，而不是覆盖，或者实现） 名为 writeObject 和 readObject 的方法，这样一旦对象被序列化或
 * 反序列化还原，就会自动的分别调用这两个方法，也就是说，只要我们提供了这两个方法，就会使用它们不是默认的的序列化
 * 机制
 *
 *
 *
 *
 *  从设计的观点来看，现在事情变得真是不可思义，首先，我们可能会认为由于这引进方法不是基类或Servializable
 *  接口的一部分，所以应该在它们自己的接口中进会你进行定义，但是注意的它们被定义为成了private ,这意味它们
 *  仅能被这个类的的其它的成员调用，然而，实际上我们并没有从这个类中的其他的方法中调用它们，而是ObjectoutStream
 *  和ObjectinputStream对象的writeObject() 和readObject() 方法调用你的对象的writeObject() 方法，
 *  注意关于这里用到相同的方法名，我尽量抑制住不去谩骂，读者可能想知道ObjectOutputStream 和ObjectinputStream
 *  对象是怎样的访问你的private 方法的，我们只能假设这正是序列化神奇的效果
 *
 *
 *
 */

public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;

    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    public String toString() {
        return "logon info: \n   username: " + username +
                "\n   date: " + date + "\n   password: " + password;
    }

    public static void main(String[] args) throws Exception {
        Logon a = new Logon("Hulk", "myLittlePony");
        print("logon a = " + a);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Logon.out"));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1); // Delay
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Logon.out"));
        print("Recovering object at " + new Date());
        a = (Logon) in.readObject();
        print("logon a = " + a);
    }
}


/* Output: (Sample)
logon a = logon info:
   username: Hulk
   date: Sat Nov 19 15:03:26 MST 2005
   password: myLittlePony
Recovering object at Sat Nov 19 15:03:28 MST 2005
logon a = logon info:
   username: Hulk
   date: Sat Nov 19 15:03:26 MST 2005
   password: null
*///:~
