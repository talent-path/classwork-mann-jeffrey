package animals.baseclasses;

public abstract class Mammal extends Chordate {
    public Mammal(String name) {
        super(name);
    }
    @Override
    public String getAnimalClass() {
        return "Mammalia";
    }

    public String getBloodTemp() {
        return "Warm";
    }
}
