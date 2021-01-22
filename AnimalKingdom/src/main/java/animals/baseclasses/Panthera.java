package animals.baseclasses;

public abstract class Panthera extends Pantherinae {
    public Panthera(String name) {
        super(name);
    }
    @Override
    public String getGenus() {
        return "animals.baseclasses.Panthera";
    }
}
