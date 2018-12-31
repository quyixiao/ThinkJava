//: xml/Person.java
package xml;
// Use the XOM library to write and read XML
// {Requires: nu.xom.Node; You must install
// the XOM library from http://www.xom.nu }

import nu.xom.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;


/**
 *
 *
 *
 * 586 页
 *
 *
 * 作为一个示例，假设有一个Person对象，它包含姓和名，你想把它序列化到XML中，下面的Person类有一个
 * getXML()方法，它使用XOM来产生被转换为XML的Element对象Person 数据，还有一个构造器，接受Element并
 * 从中抽取恰当的Person数据，注意，XML示例都在它们自己的子目录中
 *
 *
 *
 *
 * XOM 的方法都具有相当的自解释性，可以在XOM文档中找到我们，XOM还包含一个Serialize类，
 * 你可以在formate（）方法中看到用来将XML转换为更具有可读性的格式，如果只调用toXML() ，
 * 那么所有的东西都会混在一起，因此Serializer 是一种便利的工具
 *
 *
 *
 *
 *
 *
 * 从XML文件中反序列化Person对象也很简单的
 *
 *
 * Person构造器使用xom 的builder.build()方法打开并读取一个文件，而getChilldElement()方法产生了一个
 * Element列表，不是标准的size() get 方法对象，因为Harod 不想强制人们使用Java SE5 ，但是仍然希望使用类型
 * 安全容器，在这个列表中的每一个Element都表示一个Person对象，因此它可以传递第二个Person构造器，注意，还要求
 * 你提出知道XML文件的确切的结构，但是这经常有一些问题，如果文件结构和你预期的不匹配，那么XOM将抛出异常，对你
 * 来说，如果文件结构与你预期的不一样，那么就会编写更加复杂的代码去探测XML文档，而不是只对其做出假设
 *
 *
 *
 *
 */
public class Person {
    private String first, last;

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    // Produce an XML Element from this Person object:
    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }

    // Constructor to restore a Person from an XML Element:
    public Person(Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
    }

    public String toString() {
        return first + " " + last;
    }

    // Make it human-readable:
    public static void
    format(OutputStream os, Document doc) throws Exception {
        Serializer serializer = new Serializer(os, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws Exception {
        List<Person> people = Arrays.asList(
                new Person("Dr. Bunsen", "Honeydew"),
                new Person("Gonzo", "The Great"),
                new Person("Phillip J.", "Fry"));
        System.out.println(people);

        Element root = new Element("people");

        for (Person p : people) {
            root.appendChild(p.getXML());
        }
        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream("/Users/quyixiao/Desktop/source/ThinkJava/src/main/java/io/People.xml")), doc);
    }
}





/* Output:
[Dr. Bunsen Honeydew, Gonzo The Great, Phillip J. Fry]
<?xml version="1.0" encoding="ISO-8859-1"?>
<people>
    <person>
        <first>Dr. Bunsen</first>
        <last>Honeydew</last>
    </person>
    <person>
        <first>Gonzo</first>
        <last>The Great</last>
    </person>
    <person>
        <first>Phillip J.</first>
        <last>Fry</last>
    </person>
</people>
*///:~
