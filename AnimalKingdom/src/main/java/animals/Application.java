package animals;

import animals.concreteclasses.*;
import animals.interfaces.IAnimal;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        PantheraLeo simba = new PantheraLeo("Simba");
        CarcharodonCarcharias jaws = new CarcharodonCarcharias("Jaws");
        RobotDog spot = new RobotDog("Spot");

        List<IAnimal> animalList = new ArrayList<>();
        animalList.add(simba);
        animalList.add(jaws);
        animalList.add(spot);

        for (IAnimal animal : animalList) {
            animal.getAnimalReport();
        }
    }
}
