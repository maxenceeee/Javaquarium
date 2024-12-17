package fr.xamence.javaquarium.entities;

import fr.xamence.javaquarium.Aquarium;

public class Alga extends Entity {

    @Override
    public void getEaten() {
        this.setPv(this.getPv() - 2);

    }

    @Override
    public void turn() {
        this.setPv(this.getPv() + 1);

        if (this.getAge() > 10) {
            Alga babyAlga = new Alga();
            babyAlga.setPv(this.getPv() / 2);
            this.setPv(babyAlga.getPv());

            Aquarium.getInstance().addAlga(babyAlga);
        }
    }


}
