public class Femme extends Humain {
    public int fertilite;

    Femme(String nom) {
        super(nom);
        fertilite = 0;
    }

    Femme(String nom, int age, int poids, int fertilite) {
        super(age, poids, nom);
        this.fertilite = fertilite;
    }

    public boolean isHomme() {
        return false;
    }

    public boolean isFemme() {
        return true;
    }

    int getFertilite() {
        return fertilite;
    }

    public void vieillir(Aging a) {
        age = age + 1;
        a.gainWeight(this);
    }

    public Humain rencontre(Homme h, Rencontred rencontred) {
        rencontred.gainWeight(this, h);
        if (this.age > 15 && this.age < 50 && h.age > 15) {
            if (this.poids > 150 || h.poids > 150) {
                return null;
            } else {
                int f = loto.nextInt(100);
                if (f > this.fertilite) {
                    return null;
                } else {
                    int p = loto.nextInt(100);
                    int g = (int) (Math.random() * 20);
                    h.poids = h.poids + g;
                    this.poids = this.poids + 10;
                    if (p < 50) {
                        Garcon G = new Garcon(h.nom + this.nom);
                        return G;
                    } else {
                        Fille F = new Fille(this.nom + h.nom);
                        return F;
                    }

                }
            }
        } else {
            return null;
        }
    }

    protected void setEsperanceVie() {
        esperanceVie = (int) (55 + Math.random() * 40);
    }
}