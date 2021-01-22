package animals.baseclasses;

public abstract class Chordate extends Animal {
    public Chordate( String name) {
        super(name);
    }

    @Override
    public String getPhylum() {
        return "Chordata";
    }
}
