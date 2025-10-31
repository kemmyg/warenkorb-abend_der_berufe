package lebensmittel;

public class Tierisches extends Lebensmittel{
    public Tierisches(String name, double preisNetto) {
        // Milchprodukte sind meist 7%, aber zur Unterscheidung der Steuersätze
        // und auf Ihren Wunsch (Milch=19) wählen wir hier 19% als Beispiel
        super(name, preisNetto, 19);
    }
}
