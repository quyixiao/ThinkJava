package operators;


import static net.mindview.util.Print.print;


/****
 *
 *  在练习5的基础上，创建一个亲的Dog索引，并对其赋值为spot对象，测试用==和equals()方法来比较所有引用的结果
 *
 */
public class E06_DogsComparison {
    static void compare(Dog dog1, Dog dog2) {
        print("== on top references: " + (dog1 == dog2));
        print(
                "equals() on top references: " + dog1.equals(dog2)
        );
        print("== on names: " + (dog1.name == dog2.name));
        print(
                "equals() on names: " + dog1.name.equals(dog2.name)
        );
        print("== on says: " + (dog1.says == dog2.says));
        print(
                "equals() on says: " + dog1.says.equals(dog2.says)
        );
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = dog1;  // "Aliased" reference
        dog1.name = "spot";
        dog1.says = "ruff!";
        dog2.name = "scruffy";
        dog2.says = "wurf!";
        print("Comparing dog1 and dog2 objects...");
        compare(dog1, dog2);
        print("\nComparing dog1 and dog3 objects...");
        compare(dog1, dog3);
        print("\nComparing dog2 and dog3 objects...");
        compare(dog2, dog3);
    }
}