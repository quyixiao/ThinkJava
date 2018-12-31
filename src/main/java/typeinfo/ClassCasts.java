package typeinfo;//: typeinfo/ClassCasts.java

class Building {
}

class House extends Building {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ClassCasts {
    public static void main(String[] args) throws Exception {
        Building b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        House c = houseType.newInstance();

        System.out.println(c.getName());
        h.setName("1");
        System.out.println(h.getName());
        h = (House) b; // ... or just do this.
    }
} ///:~
