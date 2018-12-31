package generics;//: generics/EpicBattle.java
// Demonstrating bounds in Java generics.

import java.util.List;


/***
 * HoldItem 直接持有一个对象，因此这种行为被继承到了Colored2中，它也要求其参数与HasColor一致
 * ，ColoredDimensiion2和Solid2时一步扩展了这个层次结构，并在第个层次上都添加了边界，瑞这些方法被继承了，
 * 因此不必在每个类中重复
 *
 *
 *
 * 1
 */
interface SuperPower {
}

interface XRayVision extends SuperPower {
    void seeThroughWalls();
}

interface SuperHearing extends SuperPower {
    void hearSubtleNoises();
}

interface SuperSmell extends SuperPower {
    void trackBySmell();
}

class SuperHero<POWER extends SuperPower> {
    POWER power;

    SuperHero(POWER power) {
        this.power = power;
    }

    POWER getPower() {
        return power;
    }
}

class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {
    SuperSleuth(POWER power) {
        super(power);
    }

    void see() {
        power.seeThroughWalls();
    }
}

class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {
    CanineHero(POWER power) {
        super(power);
    }

    void hear() {
        power.hearSubtleNoises();
    }

    void smell() {
        power.trackBySmell();
    }
}

class SuperHearSmell implements SuperHearing, SuperSmell {
    public void hearSubtleNoises() {
    }

    public void trackBySmell() {
    }
}

class DogBoy extends CanineHero<SuperHearSmell> {
    DogBoy() {
        super(new SuperHearSmell());
    }
}

public class EpicBattle {
    // Bounds in generic methods:
    static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
    }

    static <POWER extends SuperHearing & SuperSmell> void superFind(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
        hero.getPower().trackBySmell();
    }

    public static void main(String[] args) {
        DogBoy dogBoy = new DogBoy();
        useSuperHearing(dogBoy);
        superFind(dogBoy);
        // You can do this:
        List<? extends SuperHearing> audioBoys;
        // But you can't do this:
        // List<? extends SuperHearing & SuperSmell> dogBoys;
    }
} ///:~
