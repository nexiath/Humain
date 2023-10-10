import java.util.*;

public class Humain implements Comparable<Humain> {
    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());
    protected int age;
    protected int poids;
    protected String nom;
    protected int esperanceVie;

    Humain(String nom) {
        age = 0;
        poids = 3;
        this.nom = nom;
        setEsperanceVie();
    }

    Humain(int age, int poids, String nom) {
        this.age = age;
        this.poids = poids;
        this.nom = nom;
        setEsperanceVie();
    }

    void setNom(String nom) {
        this.nom = nom;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setPoids(int poids) {
        this.poids = poids;
    }

    int getAge() {
        return age;
    }

    int getPoids() {
        return poids;
    }

    String getNom() {
        return nom;
    }

    protected void setEsperanceVie() {
        esperanceVie = 70;
    }

    public boolean isHomme() {
        return true;
    }

    public boolean isFemme() {
        return true;
    }

    public void vieillir(Aging a) {
        if(this.isHomme()) {
            ((Homme) this).vieillir(a);
        }
        else if(this.isFemme()) {
            ((Femme) this).vieillir(a);
        }
    }

    public void grossir(int p) {
        poids = poids + p;
    }

    public boolean isDead() {
        if (age > esperanceVie) {
            return true;
        }
        return false;
    }

    public int compareTo(Humain h) {
        if(this.age < h.age) {
            return -1;
        }
        else if(this.age > h.age) {
            return 1;
        }
        else {
            if (h instanceof Femme) {
                return 0;
            }
            if (this instanceof Femme) {
                return 0;
            }
            else {
                return ((Homme) this).salaire - ((Homme) h).salaire;
            }
        }
    }

    public void print() {
        String affichage = "Nom = " + nom + "\n" + "Age = " + age + "\n" + "Poids = " + poids + "\n" + "Esperence Vie = " + esperanceVie;
        if(this instanceof Homme && this.age >= 18) {
            affichage = affichage + "\n" + "Salaire = " + ((Homme) this).getSalaire();
        }
        String type = "";
        if(this instanceof Homme) {
            type = "\n" + "Type = Homme" + "\n";
        }
        if(this instanceof Garcon) {
            type = "\n" + "Type = Garcon" + "\n";
        }
        if(this instanceof Femme) {
            type = "\n" + "Type = Femme" + "\n";
        }
        if(this instanceof Fille) {
            type = "\n" + "Type = Fille" + "\n";
        }
        System.out.println(type + affichage);
    }
}
