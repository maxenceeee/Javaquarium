package fr.xamence.javaquarium;

public class Utils {

    private static final String[] names = new String[] {"Nemo", "Jeff", "Jason", "Dori", "Papou", "Kakou", "Vegeta", "Boubou", "Sharko", "Zig", "Zag", "Woody", "Willis", "Will", "Flicker"};

    public static String getRandomNames() {
        return names[(int) (Math.random() * names.length)];
    }
}
