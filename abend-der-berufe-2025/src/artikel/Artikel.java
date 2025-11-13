package artikel;

public class Artikel {
    protected String name;
    protected double preisNetto; // Preis ohne Mehrwertsteuer
    protected int steuerSatz;    // MwSt. in Prozent (z.B. 7 oder 19)

    public Artikel(String name, double preisNetto, int steuerSatz) {
        this.name = name;
        this.preisNetto = preisNetto;
        this.steuerSatz = steuerSatz;
    }

    public double getPreisBrutto() {
        // PreisBrutto = PreisNetto * (1 + steuerSatz/100)
        return preisNetto * (1 + steuerSatz / 100.0);
    }

    public double getSteuerBetrag() {
        return getPreisBrutto() - preisNetto;
    }

    // Getter-Methoden
    public String getName() { return name; }
    public double getPreisNetto() { return preisNetto; }
    public int getSteuerSatz() { return steuerSatz; }
}
