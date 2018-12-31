package io;//: io/MyWorld.java

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;


/**
 *
 * 582 页
 * 这里有一件有趣的的事，我们可以通过一个字节来使用对象序列化，从而实现对任何可Serializable对象的"深度复制"，深度复制
 * 的是整个对象网，而不仅仅是基本的对象及引用，复制对象将在本书的在线补充材料中进行深入的学习
 *
 * 在这个例子中，Animal 对象包含有Hourse类型的字段， 在main()方法中，创建了一个Animal列表并将其两次序列化，分别
 * 送至不同的流，当其被反序列化还原并被打印时，我们可以看到所示的执行某次运行后的结果，每次运行时，对象将会处在不同的
 * 内存地址中，
 *
 *
 * 如果我们想保存系统状态，最安全的做法是将其作为原子的操作进行序列化，如果我们序列化了某些东西，再去做其他的一些工作，再来
 * 序列化更多的东西，如此等等，那么将无法安全的保存系统的状态，取而代之的是，将构成系统状态的所有对象都置入单一的容器内，
 * 并在一个操作中将该容器直接写出，然后同样的只需要一次方法调用，即可以将其恢复
 *
 *
 * 如果我们想保存系统状态，最安全的做法是疘其作为原子的操作进行序列化，如果我们序列化了某些东西，再去做其他的一些工作，再来
 * 序列化，并在一个操作中将该容器直接写出，然后同样只需要一次方法调用，即可以将其恢复
 *
 * 下面这个例子是一个想象的计算机辅助设计系统，该例演示了一个方法，此外，它还引入了static 字段的问题，如果我们查看JDK文档，
 * 就会发现Class是Serializable的，因此只需直接对Class 对象序列化，就可以很容易地保存static字段，在任何情况下，这都是一种
 * 明智的做法
 *
 *
 *
 *
 *
 */
class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        print("animals: " + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals); // Write a 2nd set
        // Write to a different stream:
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        // Now get them back:
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));



        List
                animals1 = (List) in1.readObject(),
                animals2 = (List) in1.readObject(),
                animals3 = (List) in2.readObject();


        print("animals1: " + animals1);
        print("animals2: " + animals2);
        print("animals3: " + animals3);
    }
}



/* Output: (Sample)
animals: [Bosco the dog[Animal@addbf1], House@42e816
, Ralph the hamster[Animal@9304b1], House@42e816
, Molly the cat[Animal@190d11], House@42e816
]
animals1: [Bosco the dog[Animal@de6f34], House@156ee8e
, Ralph the hamster[Animal@47b480], House@156ee8e
, Molly the cat[Animal@19b49e6], House@156ee8e
]
animals2: [Bosco the dog[Animal@de6f34], House@156ee8e
, Ralph the hamster[Animal@47b480], House@156ee8e
, Molly the cat[Animal@19b49e6], House@156ee8e
]
animals3: [Bosco the dog[Animal@10d448], House@e0e1c6
, Ralph the hamster[Animal@6ca1c], House@e0e1c6
, Molly the cat[Animal@1bf216a], House@e0e1c6
]
*///:~
