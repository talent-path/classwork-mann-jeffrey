package animals.baseclasses;

public abstract class Chondrichthye extends Chordate{
    public Chondrichthye(String name) {
        super(name);
    }

    @Override
    public String getAnimalClass() {
        return "animals.baseclasses.Chondrichthye";
    }
}
