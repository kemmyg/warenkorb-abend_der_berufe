import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Globale Datenstruktur für alle Produkte
    private static final Map<String, Lebensmittel> PRODUCTS = new HashMap<>();
    private static double cartTotalNetto = 0.0;
    private static double cartTotalSteuer = 0.0;

    // --- KLASSEN DEFINITIONEN ---

    // Basisklasse
    static abstract class Lebensmittel {
        protected String name;
        protected double preisNetto; // Preis ohne Mehrwertsteuer
        protected int steuerSatz;    // MwSt. in Prozent (z.B. 7 oder 19)

        public Lebensmittel(String name, double preisNetto, int steuerSatz) {
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

    // Subklassen mit vordefiniertem Steuersatz
    static class ObstGemuese extends Lebensmittel {
        public ObstGemuese(String name, double preisNetto) {
            // Ermäßigter Satz: 7 %
            super(name, preisNetto, 7);
        }
    }

    static class Suessigkeiten extends Lebensmittel {
        public Suessigkeiten(String name, double preisNetto) {
            // Regulärer Satz: 19 %
            super(name, preisNetto, 19);
        }
    }

    static class Tierisches extends Lebensmittel {
        public Tierisches(String name, double preisNetto) {
            // Milchprodukte sind meist 7%, aber zur Unterscheidung der Steuersätze
            // und auf Ihren Wunsch (Milch=19) wählen wir hier 19% als Beispiel
            super(name, preisNetto, 19);
        }
    }

    // --- INITIALISIERUNG ---

    private static void initializeProducts() {
        // Obst & Gemüse (7 %)
        PRODUCTS.put("Apfel", new ObstGemuese("Apfel", 0.47)); // Brutto ~ 0.50 €
        PRODUCTS.put("Banane", new ObstGemuese("Banane", 0.75)); // Brutto ~ 0.80 €

        // Süßigkeiten (19 %)
        PRODUCTS.put("Keks", new Suessigkeiten("Keks", 1.01)); // Brutto ~ 1.20 €

        // Tierisches (19 %)
        PRODUCTS.put("Milch", new Tierisches("Milch", 1.26)); // Brutto ~ 1.50 €
    }

    // --- LOGIK ---

    public static void addItem(String itemName) {
        if (PRODUCTS.containsKey(itemName)) {
            Lebensmittel item = PRODUCTS.get(itemName);

            cartTotalNetto += item.getPreisNetto();
            cartTotalSteuer += item.getSteuerBetrag();

            double brutto = item.getPreisBrutto();

            // Konsolenausgabe
            System.out.printf("✔️ '%s' hinzugefügt! (Netto: %.2f € | MwSt: %d%% | Brutto: %.2f €)%n",
                    itemName, item.getPreisNetto(), item.getSteuerSatz(), brutto);
            System.out.printf("🛒 Aktueller Gesamtpreis: %.2f €%n", cartTotalNetto + cartTotalSteuer);
        } else {
            System.out.printf("❌ Fehler: Das Produkt '%s' ist uns unbekannt.%n", itemName);
        }
    }

    // --- HAUPTPROGRAMM ---

    public static void main(String[] args) {
        initializeProducts();
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("      🛒 Warenkorb-Simulator (Java) 🛒");
        System.out.println("      Lektion: Klassen, Logik & MwSt.");
        System.out.println("========================================");

        System.out.println("\nVerfügbare Produkte (Bruttopreis):");
        for (Lebensmittel item : PRODUCTS.values()) {
            System.out.printf("- %s: %.2f € (MwSt: %d%%)%n", item.getName(), item.getPreisBrutto(), item.getSteuerSatz());
        }
        System.out.println("----------------------------------------");
        System.out.println("Anleitung: Gib den Namen des Produkts ein.");
        System.out.println("Zum Beenden gib 'ENDE' ein.");

        while (true) {
            System.out.print("\nProdukt hinzufügen (oder ENDE): ");
            String itemInput = scanner.nextLine().trim();

            if (itemInput.equalsIgnoreCase("ENDE")) {
                break;
            }

            addItem(itemInput);
        }

        // Abschluss
        double finalTotalBrutto = cartTotalNetto + cartTotalSteuer;
        System.out.println("\n========================================");
        System.out.println("Vorgang beendet.");
        System.out.printf("Gesamtsumme (Netto): %.2f €%n", cartTotalNetto);
        System.out.printf("Gesamtsumme (Steuer): %.2f €%n", cartTotalSteuer);
        System.out.printf("Ihre finale Gesamtsumme (Brutto) beträgt: %.2f €%n", finalTotalBrutto);
        System.out.println("========================================");

        scanner.close();
    }
}