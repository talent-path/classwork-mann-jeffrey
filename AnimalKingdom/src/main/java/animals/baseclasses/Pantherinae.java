package animals.baseclasses;

import animals.baseclasses.Felidae;

public abstract class Pantherinae extends Felidae {
    public Pantherinae(String name) {
        super(name);
    }
    @Override
    public String getSubfamily() {
        return "animals.baseclasses.Pantherinae";
    }
}
