package animals.baseclasses;
import animals.interfaces.IAnimal;

public abstract class Animal implements IAnimal {
    public Animal( String name ) {
        this.name = name;
    }

    public String name;

    public String getKingdom() {
        return "Animalia";
    }

    public String getName() {
        return name;
    }

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
