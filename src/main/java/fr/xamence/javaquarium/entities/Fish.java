package fr.xamence.javaquarium.entities;

import fr.xamence.javaquarium.Aquarium;
import fr.xamence.javaquarium.Utils;
import fr.xamence.javaquarium.entities.fish.Race;
import fr.xamence.javaquarium.entities.fish.sex.Sexuality;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Fish extends Entity {

    private Sex sex;
    private final String name;
    private final Race race;
    private Sexuality sexuality;

    public Fish(Sex sex, String name, Race race) {
        this.sex = sex;
        this.name = name;
        this.race = race;

        switch (race) {
            case BAR -> this.sexuality = Sexuality.AGE_FLUID;
            case SOL -> this.sexuality = Sexuality.OPPORTUNITY_FLUID;
            case THON -> this.sexuality = Sexuality.MONO;
            case CARPE -> this.sexuality = Sexuality.MONO;
            case MEROU -> this.sexuality = Sexuality.AGE_FLUID;
            case CLOWN_FISH -> this.sexuality = Sexuality.OPPORTUNITY_FLUID;
        }
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public boolean needToEat() {
        return this.getPv() <= 5;
    }

    public abstract void eat();

    @Override
    public void turn() {
        this.setPv(this.getPv() - 1);

        if(this.getAge() > 10 && this.sexuality == Sexuality.AGE_FLUID) {
            Sex newSex = this.getSex() == Sex.FEMALE ? Sex.MALE : Sex.FEMALE;
            this.sex = newSex;
        }


        if(!needToEat()) {
            Random random = new Random();
            Fish coupleFish = Aquarium.getInstance().getFishList().get(random.nextInt(Aquarium.getInstance().getFishList().size()));

            if(coupleFish.getRace() == this.getRace() && !(coupleFish.equals(this))) {
                if (this.sexuality == Sexuality.OPPORTUNITY_FLUID) {
                    if (coupleFish.getSex() != this.getSex()) {
                        Sex newSex = this.getSex() == Sex.FEMALE ? Sex.MALE : Sex.FEMALE;
                        this.sex = newSex;
                    }
                }

               if(coupleFish.getSex() != this.getSex()) return;

                Sex baySex = random.nextInt(0,1) == 1 ? Sex.FEMALE : Sex.MALE;
                Fish babyFish;
                try {
                    babyFish = (Fish) this.getClass().getDeclaredConstructors()[0].newInstance(baySex, Utils.getRandomNames(), this.getRace());
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                Aquarium.getInstance().getFishList().add(babyFish);
            }
        }


        super.turn();
    }

    @Override
    public void getEaten() {
        this.setPv(this.getPv() - 4);
    }

    public Race getRace() {
        return race;
    }


}
