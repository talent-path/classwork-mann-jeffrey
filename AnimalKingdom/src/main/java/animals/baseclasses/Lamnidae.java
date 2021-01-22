package animals.baseclasses;

public abstract class Lamnidae extends Lamniforme{
    public Lamnidae(String name) {
        super(name);
    }

    @Override
    public String getSuborder() {
        return null;
    }

    @Override
    public String getFamily() {
        return "animals.baseclasses.Lamnidae";
    }
}
