package animals.baseclasses;

public abstract class Lamniforme extends Chondrichthye{
    public Lamniforme(String name) {
        super(name);
    }

    @Override
    public String getOrder() {
        return "animals.baseclasses.Lamniforme";
    }
}
