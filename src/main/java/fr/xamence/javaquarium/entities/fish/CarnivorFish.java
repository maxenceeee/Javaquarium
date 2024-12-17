package fr.xamence.javaquarium.entities.fish;

import fr.xamence.javaquarium.entities.Fish;
import fr.xamence.javaquarium.entities.Sex;

public class CarnivorFish extends Fish {

    public CarnivorFish(Sex sex, String name, Race race) {
        super(sex, name, race);
    }

    @Override
    public void eat() {
        this.setPv(this.getPv() + 3);
    }


}
