package generics;//: generics/SimpleDogsAndRobots.java
// Removing the generic; code still works.


/****
 *
 *
 * 419页
 *
 *
 * 1
 *
 */
class CommunicateSimply {
    static void perform(Performs performer) {
        performer.speak();
        performer.sit();
    }
}



public class SimpleDogsAndRobots {
    public static void main(String[] args) {
        CommunicateSimply.perform(new PerformingDog());
        CommunicateSimply.perform(new Robot());
    }
}




/* Output:
Woof!
Sitting
Click!
Clank!
*///:~
