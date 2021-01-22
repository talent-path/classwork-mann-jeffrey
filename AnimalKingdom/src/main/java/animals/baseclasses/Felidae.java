package animals.baseclasses;

public abstract class Felidae extends Feliformia{
    public Felidae(String name) {
        super(name);
    }
    @Override
    public String getFamily() {
        return "animals.baseclasses.Felidae";
    }

    public String meow() {
        return "Meow!";
    }
}
