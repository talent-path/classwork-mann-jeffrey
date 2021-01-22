package animals.baseclasses;

public abstract class Feliformia extends Carnivore {
    public Feliformia(String name) {
        super(name);
    }
    @Override
    public String getSuborder() {
        return "animals.baseclasses.Feliformia";
    }
}
