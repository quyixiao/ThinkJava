//: innerclasses/E01_ReferenceToInnerClass.java
/****************** Exercise 1 *****************
 * Write a class named Outer containing an
 * inner class named Inner. Add a method to Outer
 * that returns an object of type Inner. In
 * main(), create and initialize a reference to
 * an Inner.
 ***********************************************/
package innerclasses;


/*****
 *
 *
 *
 * 191页
 *  编写一个为Outer的类，它包夜一个名为Inner的类，在Outer中添加一个方法
 *  它返回一个Inner类的对象，在main(）中，创建并初始化一个指向某个Inner对象的引用
 *
 *
 *
 * 1
 */

class Outer {
    class Inner {
        {
            System.out.println("Inner created");
        }
    }

    Inner getInner() {
        return new Inner();
    }
}

/****************** Exercise 7 *****************
 * Create a class with a private field and a
 * private method. Create an inner class with a
 * method that modifies the outer-class field and
 * calls the outer-class method. In a second
 * outer-class method, create an object of the
 * inner class and call its method, then show
 * the effect on the outer-class object.
 ***********************************************/
