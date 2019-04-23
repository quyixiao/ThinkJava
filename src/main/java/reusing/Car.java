package reusing;//: reusing/Car_override_print.java
// Composition with public objects.


/****
 * 137页
 *
 * 1
 *
 */
class Engine {
    public void start() {
    }

    public void rev() {
    }

    public void stop() {
    }
}

class Wheel {
    public void inflate(int psi) {
        System.out.println("car.wheel.inflate=>"+psi);
    }
}

class Window {

    private String redirect;

    public Window(){

    }
    public Window(String redirect){
        this.redirect = redirect;
    }

    public void rollup() {
        System.out.println(this.redirect + ".window.rollup");
    }

    public void rolldown(String redirect) {
        System.out.println(redirect + "window.rolldown");
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}

class Door {
    private String redirect;

    public Door(){

    }

    public Door(String redirect){
        this.redirect = redirect;
        this.window.setRedirect(redirect);
    }

    public Window window = new Window(this.redirect);

    public void open() {
        System.out.println("window.open");
    }


    public void close() {
    }
}

public class Car {
    public Engine engine = new Engine();
    public Wheel[] wheel = new Wheel[4];
    public Door
            left = new Door(),
            right = new Door(); // 2-door

    public Car() {
        for (int i = 0; i < 4; i++)
            wheel[i] = new Wheel();
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.left.window.rollup();
        car.wheel[0].inflate(72);
    }
} ///:~
