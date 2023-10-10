public class Homme extends Humain {
    public int batifolage;
    public int salaire;

    Homme(String nom) {
        super(nom);
        salaire = 0;
        batifolage = 0;
    }

    Homme(String nom, int age, int poids, int batifolage, int salaire) {
        super(age, poids, nom);
        this.salaire = salaire;
        this.batifolage = batifolage;
    }

    public boolean isHomme() {
        return true;
    }

    public boolean isFemme() {
        return false;
    }

    int getBatifolage() {
        return batifolage;
    }

    int getSalaire() {
        return salaire;
    }

    public Humain rencontre(Femme f, Rencontred rencontred) {
        rencontred.gainWeight(this, f);
        int b = (int) (Math.random() * 100);
        if (b < batifolage) {
            return null;
        }
        if (f.age > 15 && f.age < 50 && this.age > 15) {
            if (this.poids > 150 || f.poids > 150) {
                return null;
            } else {
                int c = (int) (Math.random() * 100);
                if (c > f.getFertilite()) {
                    return null;
                } else {
                    int p = (int) (Math.random() * 100);
                    int g = (int) (-10 + Math.random() * 20);
                    f.poids = f.poids + 10;
                    this.poids = this.poids + g;
                    if (p < 50) {
                        Garcon G = new Garcon(this.nom + f.nom);
                        return G;
                    } else {
                        Fille F = new Fille(f.nom + this.nom);
                        return F;
                    }
                }
            }
        } else {
            return null;
        }
    }

    public void vieillir(Aging a) {
        age = age + 1;
        a.gainWeight(this);

    }

    protected void setEsperanceVie() {
        esperanceVie = (int) (50 + Math.random() * 30);
    }
}