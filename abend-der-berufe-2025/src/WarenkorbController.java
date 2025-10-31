import lebensmittel.Lebensmittel;
import lebensmittel.ObstGemuese;
import lebensmittel.Suessigkeiten;
import lebensmittel.Tierisches;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WarenkorbController {
    private static final Map<String, Lebensmittel> PRODUCTS = new HashMap<>();
    private static double cartTotalNetto = 0.0;
    private static double cartTotalSteuer = 0.0;

    private static void initializeProducts() {
        // Obst & Gemüse (7 %)
        PRODUCTS.put("Apfel", new ObstGemuese("Apfel", 0.47)); // Brutto ~ 0.50 €
        PRODUCTS.put("Banane", new ObstGemuese("Banane", 0.75)); // Brutto ~ 0.80 €

        // Süßigkeiten (19 %)
        PRODUCTS.put("Keks", new Suessigkeiten("Keks", 1.01)); // Brutto ~ 1.20 €

        // Tierisches (19 %)
        PRODUCTS.put("Milch", new Tierisches("Milch", 1.26)); // Brutto ~ 1.50 €
    }

    public static void addItem(String itemName) {
        if (!PRODUCTS.containsKey(itemName)) {
            System.out.printf("❌ Fehler: Das Produkt '%s' ist uns unbekannt.%n", itemName);
        }
        Lebensmittel item = PRODUCTS.get(itemName);

        cartTotalNetto += item.getPreisNetto();
        cartTotalSteuer += item.getSteuerBetrag();

        double brutto = item.getPreisBrutto();

        // Konsolenausgabe
        System.out.printf("✔️ '%s' hinzugefügt! (Netto: %.2f € | MwSt: %d%% | Brutto: %.2f €)%n",
                itemName, item.getPreisNetto(), item.getSteuerSatz(), brutto);
        System.out.printf("🛒 Aktueller Gesamtpreis: %.2f €%n", cartTotalNetto + cartTotalSteuer);
    }

    public void runWarenkorb() {
        initializeProducts();
        Scanner scanner = new Scanner(System.in);
        printHeader();
        showProductsToBuy();
        printManual();
        addItemUnderCondition(scanner);
        displayFinalValue();
        scanner.close();
    }

    private static void displayFinalValue() {
        double finalTotalBrutto = cartTotalNetto + cartTotalSteuer;
        System.out.println("\n========================================");
        System.out.println("Vorgang beendet.");
        System.out.printf("Gesamtsumme (Netto): %.2f €%n", cartTotalNetto);
        System.out.printf("Gesamtsumme (Steuer): %.2f €%n", cartTotalSteuer);
        System.out.printf("Ihre finale Gesamtsumme (Brutto) beträgt: %.2f €%n", finalTotalBrutto);
        System.out.println("========================================");
    }

    private static void addItemUnderCondition(Scanner scanner) {
        while (true) {
            System.out.print("\nProdukt hinzufügen (oder ENDE): ");
            String itemInput = scanner.nextLine().trim();

            if (itemInput.equalsIgnoreCase("ENDE")) {
                break;
            }

            addItem(itemInput);
        }
    }

    private static void printManual() {
        System.out.println("----------------------------------------");
        System.out.println("Anleitung: Gib den Namen des Produkts ein.");
        System.out.println("Zum Beenden gib 'ENDE' ein.");
    }

    private static void showProductsToBuy() {
        System.out.println("\nVerfügbare Produkte (Bruttopreis):");
        for (Lebensmittel item : PRODUCTS.values()) {
            System.out.printf("- %s: %.2f € (MwSt: %d%%)%n", item.getName(), item.getPreisBrutto(), item.getSteuerSatz());
        }
    }

    private static void printHeader() {
        System.out.println("========================================");
        System.out.println("      🛒 Warenkorb-Simulator (Java) 🛒");
        System.out.println("      Lektion: Klassen, Logik & MwSt.");
        System.out.println("========================================");
    }
}
