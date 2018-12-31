package innerclasses;//: innerclasses/Parcel8.java
// Calling the base-class constructor.


/****
 *
 * 只需简单的传递给合适的参数给基类构造器即可，这里是将传进new Wappering(x)，尽管Wrapping只是一个具有实现的类
 * ，但是它被其导出类当作公共的接口来使用
 *
 */
public class Parcel8 {
    public Wrapping wrapping(int x) {
        // Base constructor call:
        return new Wrapping(x) { // Pass constructor argument.
            public int value() {
                return super.value() * 47;
            }
        }; // Semicolon required
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
        System.out.println(w.value());
    }
} ///:~
