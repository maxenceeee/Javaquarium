package fr.xamence.javaquarium.entities.fish;

import fr.xamence.javaquarium.entities.Fish;

public enum Race {

    MEROU(CarnivorFish.class),
    THON(CarnivorFish.class),
    CLOWN_FISH(CarnivorFish.class),
    SOL(HerbivorFish.class),
    BAR(HerbivorFish.class),
    CARPE(HerbivorFish.class);


    private final Class<? extends Fish> fishClass;

    Race(Class<? extends Fish> clazz) {
        this.fishClass = clazz;
    }

    public Class<? extends Fish> getFishClass() {
        return fishClass;
    }
}
