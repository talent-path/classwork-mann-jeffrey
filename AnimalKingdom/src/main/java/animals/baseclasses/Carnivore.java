package animals.baseclasses;

public abstract class Carnivore extends Mammal{
    public Carnivore(String name) {
        super(name);
    }

    @Override
    public String getOrder() {
        return "Carnivora";
    }

    public boolean eatsMeat() { return true; }
}
