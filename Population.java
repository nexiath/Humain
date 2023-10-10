import java.util.*;

class Population {
    List<Humain> pop;

    Population() {
        this.pop = new ArrayList<>();
    }

    public void vider() {
        pop.clear();
    }

    public void addHumain(Humain h) {
        pop.add(h);
    }

    public Humain getHumain(int index) {
        return pop.get(index);
    }

    public void removeHumain(Humain h) {
        getHumain(pop.indexOf(h));
        pop.remove(h);
    }

    public void removeHumain(int index) {
        getHumain(index);
        pop.remove(index);
    }

    public int taille() {
        return pop.size();
    }

    public void adulte() {
        for (int i = 0; i < pop.size(); i++) {
            if (getHumain(i) instanceof Garcon && getHumain(i).age >= 18) {
                Homme h = new Homme(getHumain(i).nom, getHumain(i).age, getHumain(i).poids, 100,
                        (int) (1000 + Math.random() * 10000));
                pop.remove(getHumain(i));
                pop.add(i, h);
            }
            if (getHumain(i) instanceof Fille && getHumain(i).age >= 18) {
                Femme f = new Femme(getHumain(i).nom, getHumain(i).age, getHumain(i).poids,
                        (int) (Math.random() * 100) + 1);
                pop.remove(getHumain(i));
                pop.add(i, f);
            }
        }
    }

    public void vieillir(Aging a, FertilityAdjustment fertilityAdjuster) {
        for (int i = 0; i < pop.size(); i++) {
            getHumain(i).vieillir(a);
            if (getHumain(i).age == 18 && getHumain(i).isHomme()) {
                ((Homme) getHumain(i)).salaire = (int) (1000 + Math.random() * 10000);
            }
            if (getHumain(i).isFemme()) {
                fertilityAdjuster.adjustFertility((Femme) getHumain(i), taille());
            }
            if (getHumain(i).isDead()) {
                removeHumain(getHumain(i));
            }
        }
    }
    

    public void tri(int n) {
        if (n == 1) {
            tri_AgeDecroissant();
        } else if (n == 2) {
            tri_Genre();
        } else if (n == 3) {
            tri_AgeCroissant_Genre();
        } else if (n == 4) {
            tri_Genre_AgeCroissant();
        } else if (n == 5) {
            tri_AgeCroissant_Genre_Salaire_Fertilite();
        }
    }

    public void tri_AgeDecroissant() {
        Collections.sort(pop, (h1, h2) -> h2.getAge() - h1.getAge());
    }

    public void tri_Genre() {
        Collections.sort(pop, (h1, h2) -> {
            if ((h1.isHomme() && h2.isHomme()) || (h1.isFemme() && h2.isFemme())) {
                return 0;
            } else if ((h1.isHomme() && h2.isFemme())) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    public void tri_AgeCroissant_Genre() {
        Collections.sort(pop, (h1, h2) -> h1.getAge() - h2.getAge());
        tri_Genre();
    }

    public void tri_Genre_AgeCroissant() {
        tri_Genre();
        Collections.sort(pop, (h1, h2) -> h1.getAge() - h2.getAge());
    }

    public void tri_AgeCroissant_Genre_Salaire_Fertilite() {
        tri_AgeCroissant_Genre();
        Collections.sort(pop, (h1, h2) -> {
            if (h1.isHomme() && h2.isHomme()) {
                return (((Homme) h1).getSalaire() - ((Homme) h2).getSalaire());
            } else if (h1.isFemme() && h2.isFemme()) {
                return (((Femme) h1).getFertilite() - ((Femme) h2).getFertilite());
            } else {
                return 0;
            }
        });
    }

    public void print() {
        tri(5); // Entrer ne numéro de tri (entre 1 et 5)
        for (int i = 0; i < pop.size(); i++) {
            getHumain(i).print();
        }
    }
}