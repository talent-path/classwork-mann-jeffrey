package animals.concreteclasses;

import animals.baseclasses.Carcharodon;

public class CarcharodonCarcharias extends Carcharodon {
    public CarcharodonCarcharias(String name) {
        super(name);
    }
    @Override
    public String getSpecies() {
        return "Charcharodon Carcharias";
    }

    public String getCommonName() {
        return "Great White Shark";
    }
}
