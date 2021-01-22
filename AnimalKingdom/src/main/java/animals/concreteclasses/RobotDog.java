package animals.concreteclasses;

import animals.interfaces.IAnimal;

public class RobotDog implements IAnimal {
    public String name;

    public RobotDog(String name) {
        this.name = name;
    }

    @Override
    public String getKingdom() {
        return "ROBOT";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhylum() {
        return "ROBOT";
    }

    @Override
    public String getAnimalClass() {
        return "ROBOT";
    }

    @Override
    public String getOrder() {
        return "ROBOT";
    }

    @Override
    public String getSuborder() {
        return "ROBOT";
    }

    @Override
    public String getFamily() {
        return "ROBOT";
    }

    @Override
    public String getSubfamily() {
        return "ROBOT";
    }

    @Override
    public String getGenus() {
        return "ROBOT";
    }

    @Override
    public String getSpecies() {
        return "ROBOT";
    }

    @Override
    public String getCommonName() {
        return "ROBOT";
    }

    @Override
    public void getAnimalReport() {
        System.out.println("My name is " + this.getName()
                + "\nI am Kingdom: "
                + this.getKingdom()
                + "\n Phylum: "
                + this.getPhylum()
                + "\n Class: "
                + this.getAnimalClass()
                + "\n Order: "
                + this.getOrder()
                + "\n Suborder: "
                + this.getSuborder()
                + "\n Family: "
                + this.getFamily()
                + "\n Subfamily: "
                + this.getSubfamily()
                + "\n Genus: "
                + this.getGenus()
                + "\n Species: "
                + this.getSpecies()
                + "\n Common Name: "
                + this.getCommonName()
        );
    }
}
