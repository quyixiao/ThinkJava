//: generics/E38_DecoratorSystem.java
/****************** Exercise 38 *****************
 * Create a simple Decorator system by starting
 * with basic coffee, then providing decorators
 * of steamed milk, foam, chocolate, caramel
 * and whipped cream.
 ************************************************/
package generics;

import java.awt.*;

/****
 *
 *
 *
 * 415页
 *  从基本的咖啡入手，创建一个简单的装饰器系统，然后提供可以到倒入牛奶
 *  泡沫，巧克力，焦糖和生奶油的装饰器
 *
 *  编写一次多次使用，并在一个位置保存代码，因此我并未被要求去会
 *
 *
 *
 * 1
 *
 */
class BasicCoffee {
    private String type;

    public BasicCoffee() {
    }

    public BasicCoffee(String type) {
        setType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class CoffeeDecorator extends BasicCoffee {
    protected BasicCoffee basic;

    public CoffeeDecorator(BasicCoffee basic) {
        this.basic = basic;
    }

    public void setType(String type) {
        basic.setType(type);
    }

    public String getType() {
        return basic.getType();
    }
}

class SteamedMilk extends CoffeeDecorator {
    public SteamedMilk(BasicCoffee basic) {
        super(basic);
        setType(getType() + " & steamed milk");
    }
}

class Foam extends CoffeeDecorator {
    public Foam(BasicCoffee basic) {
        super(basic);
        setType(getType() + " & foam");
    }
}

class Chocolate extends CoffeeDecorator {
    private final Color color;

    public Chocolate(BasicCoffee basic, Color color) {
        super(basic);
        this.color = color;
        setType(getType() + " & chocolate[color = " +
                getColor() + "]");
    }

    public Color getColor() {
        return color;
    }
}

class Caramel extends CoffeeDecorator {
    public Caramel(BasicCoffee basic) {
        super(basic);
        setType(getType() + " & caramel");
    }
}

class WhippedCream extends CoffeeDecorator {
    public WhippedCream(BasicCoffee basic) {
        super(basic);
        setType(getType() + " & whipped cream");
    }
}

public class E38_DecoratorSystem {
    public static void main(String[] args) {
        CoffeeDecorator cappuccino = new Foam(new SteamedMilk(new BasicCoffee("espresso")));
        System.out.println("Capuccino is: " + cappuccino.getType());
        CoffeeDecorator whiteChocolateCoffee = new WhippedCream(
                new Chocolate(new BasicCoffee("hot coffee"), Color.WHITE));
        System.out.println("White Chocolate Coffee is: " + whiteChocolateCoffee.getType());
    }
}