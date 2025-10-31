package lebensmittel;

public class Suessigkeiten extends Lebensmittel{
    public Suessigkeiten(String name, double preisNetto) {
        // Regulärer Satz: 19 %
        super(name, preisNetto, 19);
    }
}
