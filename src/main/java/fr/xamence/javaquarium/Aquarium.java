package fr.xamence.javaquarium;

import fr.xamence.javaquarium.entities.Alga;
import fr.xamence.javaquarium.entities.Fish;
import fr.xamence.javaquarium.entities.Sex;
import fr.xamence.javaquarium.entities.fish.CarnivorFish;
import fr.xamence.javaquarium.entities.fish.Race;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium {

    private static Aquarium instance;

    private List<Fish> fishList;
    private List<Alga> algaList;
    private int turn;

    public Aquarium() {
        instance = this;

        this.fishList = new ArrayList<>();
        this.algaList = new ArrayList<>();
        this.turn = 0;
    }

    public void addFish(String fishName, Sex sex, Race race) {
        Fish fish = null;
        try {
            fish = (Fish) race.getFishClass().getDeclaredConstructors()[0].newInstance(sex, fishName, race);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        this.fishList.add(fish);
    }

    public void addAlga() {
        this.algaList.add(new Alga());
    }

    public void addAlga(Alga alga) {
        this.algaList.add(alga);
    }

    public void turn() {
        Random random = new Random();
        for(int i = 0; i < this.fishList.size(); i++) {
            Fish fish = this.fishList.get(i);
            fish.turn();
            if(!fish.needToEat()) continue;

            if(fish instanceof CarnivorFish) {
                Fish eatenFish = this.fishList.get(random.nextInt(0, this.fishList.size()));

                if(eatenFish.equals(fish) || eatenFish.getRace() == fish.getRace()) continue;

                eatenFish.getEaten();
                fish.eat();
            } else {
                Alga randomAlgua = this.algaList.get(random.nextInt(0, this.algaList.size()));
                randomAlgua.getEaten();
                fish.eat();
            }
        }


        for (Alga alga : algaList) {
            alga.turn();
        }

        this.clearDeadEntity();


        System.out.println("=====TURN: " + this.turn+"=====");
        System.out.println("Number of alga: " + algaList.size());
        System.out.println("List of fish: ");
        for (Fish fish : fishList) {
            String sex = fish.getSex() == Sex.MALE ? "Male" : "Female";
            System.out.println("    " + fish.getName() + " | " + sex);
        }

        this.turn++;
    }

    public void clearDeadEntity() {
        this.algaList.stream().filter((alga) -> !alga.isAlive()).forEach(alga -> {this.algaList.remove(alga);});
        this.fishList.stream().filter((fish) -> (fish != null && !fish.isAlive())).forEach(fish -> {this.fishList.remove(fish);});
    }

    public List<Alga> getAlgaList() {
        return algaList;
    }

    public static Aquarium getInstance() {
        return instance;
    }

    public List<Fish> getFishList() {
        return fishList;
    }

    public void randomlyGenerateAquarium(int algua, int fish) {
        for (int i = 0; i < algua; i++) {
            this.addAlga();
        }

        for (int i = 0; i < fish; i++) {
            String name = Utils.getRandomNames();
            Race race = Race.values()[new Random().nextInt(Race.values().length)];
            Sex sex = Sex.values()[new Random().nextInt(Sex.values().length)];
            this.addFish(name, sex, race);
        }
    }
}
