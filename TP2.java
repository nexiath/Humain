import java.util.Scanner;

public class TP2 {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Aging a = (Humain h) -> {
            if (h.isHomme()) {
                Homme h1 = (Homme) h;
                if (h1.age > 60) {
                    h1.batifolage = (int) (50 + Math.random() * 50);
                } else {
                    if (h1.age > 30) {
                        h1.batifolage = (int) (20 + Math.random() * 30);
                    } else {
                        if (h1.age > 15) {
                            h1.batifolage = (int) (70 + Math.random() * 30);
                        }
                    }
                }
                if (h1.age <= 20)
                    h1.poids = 3 + (int) (3.6 * h1.age);
                else if (h1.age >= 50)
                    h1.poids += (h1.age % 2);
            } else if (h.isFemme()) {
                Femme f1 = (Femme) h;
                if (f1.age == 15)
                    f1.fertilite = (int) (Math.random() * 100);
                if (f1.age <= 20)
                    f1.poids = 3 + (int) (2.6 * f1.age);
                else if (f1.age >= 50)
                    f1.poids += (f1.age % 2);
            }
        };
        Rencontred rencounterWeightGain = (h1, h2) -> {
            if (h1.isHomme() && h2.isFemme()) {
                h1.poids += 1; 
                h2.poids += 2; 
            } else if (h1.isFemme() && h2.isHomme()) {
                h1.poids += 2; 
                h2.poids += 1; 
            }
        };
        FertilityAdjustment adjustFertilityBasedOnPopulation = (femme, size) -> {
            if (size >= 21 && size <= 50) {
                femme.fertilite += 2;
            } else if (size > 50) {
                femme.fertilite -= 1;
            }
        };
        

        int nbToursJeu = Integer.parseInt(args[0]);
        int tailleInitiale = Integer.parseInt(args[1]);
        Population population = new Population();

        for (int i = 1; i <= tailleInitiale / 2; i++) {
            Homme homme = new Homme("homme" + i, 20, 70, 100, (int) (1000 + Math.random() * 10000));
            Femme femme = new Femme("femme" + i, 20, 55, (int) (Math.random() * 100) + 1);
            population.addHumain(homme);
            population.addHumain(femme);
        }

        for (int tour = 0; tour < nbToursJeu; tour++) {
            int n = (int) (Math.random() * (population.taille() / 2));
            System.out.println(
                    "------------------------------------------------------ Nouveau tour ! ------------------------------------------------------");
            for (int i = 0; i < n; i++) {
                int i1 = (int) (Math.random() * population.taille());
                int i2 = (int) (Math.random() * population.taille());

                Humain h1 = population.getHumain(i1);
                Humain h2 = population.getHumain(i2);

                if (h1.isHomme() && h2.isFemme()) {
                    Humain bebe = ((Homme) h1).rencontre((Femme) h2, rencounterWeightGain);
                    if (bebe != null) {
                        population.addHumain(bebe);
                        System.out.println("Nouvel individu ajouté à la population : " + bebe);
                    }
                } else if (h1.isFemme() && h2.isHomme()) {
                    Humain bebe = ((Femme) h1).rencontre((Homme) h2, rencounterWeightGain);
                    if (bebe != null) {
                        population.addHumain(bebe);
                        System.out.println("Nouvel individu ajouté à la population : " + bebe);
                    }
                }
            }
            for (int i = 0; i < population.taille(); i++) {
                Humain h = population.getHumain(i);
                if (h.isFemme()) {
                    adjustFertilityBasedOnPopulation.adjustFertility((Femme) h, population.taille()); 
                }
            }
            
            population.adulte();
            population.print();
            population.vieillir(a, adjustFertilityBasedOnPopulation);
        }
    }
}
