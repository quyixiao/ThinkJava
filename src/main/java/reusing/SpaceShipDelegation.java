package reusing;//: reusing/SpaceShipDelegation.java


/****
 *
 * 131页
 *
 *
 *  然而，SpaceShip并非真正的SpaceShipControls类型，即使你可以告诉 SpaceShip向前运动，更准确的讲，SpaceShip包含了
 *  SpaceShipControls，与此同时，SpaceShip Controls的所有的方法中都暴露出来，代码解决了些难题
 *
 *
 *
 *  1
 */
public class SpaceShipDelegation {
    private String name;
    private SpaceShipControls controls =
            new SpaceShipControls();

    public SpaceShipDelegation(String name) {
        this.name = name;
    }

    // Delegated methods:
    public void back(int velocity) {
        controls.back(velocity);
    }

    public void down(int velocity) {
        controls.down(velocity);
    }

    public void forward(int velocity) {
        controls.forward(velocity);
    }

    public void left(int velocity) {
        controls.left(velocity);
    }

    public void right(int velocity) {
        controls.right(velocity);
    }

    public void turboBoost() {
        controls.turboBoost();
    }

    public void up(int velocity) {
        controls.up(velocity);
    }

    public static void main(String[] args) {
        SpaceShipDelegation protector = new SpaceShipDelegation("NSEA Protector");
        protector.forward(100);
    }
} ///:~
