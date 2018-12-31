package object;

class Tree {
    /**
     * Current vertical aspect to the tip.
     */
    int height;  // 0 by default

    /**
     * Plant a seedling. Assume height can
     * be considered as zero.
     */
    Tree() {
        System.out.println("Planting a seedling");
    }

    /**
     * Transplant an existing tree with a given height.
     */
    Tree(int i) {
        System.out.println("Creating new Tree that is "
                + i + " feet tall");
        height = i;
    }

    /**
     * Produce information about this unit.
     */
    void info() {
        System.out.println("Tree is " + height + " feet tall");
    }

    /**
     * Produce information with optional message.
     */
    void info(String s) {
        System.out.println(s + ": Tree is "
                + height + " feet tall");
    }
}
