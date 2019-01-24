package enumerated;

//: enumerated/SpaceShip.java


/***
 *  593 页
 *
 *  覆盖toString() 方法，给我们提供了另一种方式来为枚举实例生成不同的字符串描述信息
 *  在下面的示例中，我们使用的就是实例的名字了，不过我们希望改变其他的格式，覆盖enums
 *  的toString() 方法覆盖一般的方法没有什么区别
 *
 *
 * toString() 方法通过调用name() 方法取得SpaceShip的，然后将其首字母大写的格式
 *
 *
 *      覆盖toString()方法，给我们提供了另一种方式来为枚举实例生成不同的字符串描述信息，
 *  在下面的示例中，我们使用的就是实例名字，不过我们希望改变其格式，覆盖enums的toString()方法
 *  一般类的方法没有区别，
 *
 *
 * 在switch中使用enum，是enum提供一项非常便利的功能，一般来说，在switch中只能使用整数
 *
 *
 *
 *
 * 1
 *
 */
public enum SpaceShip {
    SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }


    public static void main(String[] args) {
        for (SpaceShip s : values()) {
            System.out.println(s);
        }
    }
}




/* Output:
Scout
Cargo
Transport
Cruiser
Battleship
Mothership
*///:~
