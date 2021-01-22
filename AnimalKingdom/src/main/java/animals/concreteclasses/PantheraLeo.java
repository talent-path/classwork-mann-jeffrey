package animals.concreteclasses;

import animals.baseclasses.Panthera;

public class PantheraLeo extends Panthera {
    public PantheraLeo(String name) {
        super(name);
    }

    @Override
    public String getSpecies() {
        return "animals.baseclasses.Panthera Leo";
    }

    @Override
    public String getCommonName() {
        return "Lion";
    }

    @Override
    public void getAnimalReport() {
        System.out.println("My name is " + this.name
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
                + "\n I "
                + (this.eatsMeat() ? "do " : "don't ")
                + "eat meat"
        );
    }
}
