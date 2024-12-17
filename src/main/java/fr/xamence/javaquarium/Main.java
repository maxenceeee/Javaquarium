package fr.xamence.javaquarium;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Aquarium aquarium = new Aquarium();
        Scanner scanner = new Scanner(System.in);
        System.out.println("====Bienvenue dans le JavaQuarium====");
        System.out.println("Combien voulez vous d'algues ?");
        int algaNb = scanner.nextInt();
        System.out.println("Combien voulez vous de poisson ?");
        int poissonNb = scanner.nextInt();

        aquarium.randomlyGenerateAquarium(algaNb, poissonNb);
        System.out.println("Lancement de l'aquarium.....");
        Thread.sleep(1000);

        boolean running = true;
        while (running) {
            clearConsole();

            aquarium.turn();
            System.out.println("Vous voulez (c)ontinuer ? (s)auvegarder ? (q)uitter ?");
            String command = scanner.nextLine();
            switch (command) {
                case "q" -> System.exit(0);
            }
        }
    }

    public static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
