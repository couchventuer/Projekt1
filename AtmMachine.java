package AtmMachine;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AtmMachine {

	/*
	 * Konten: 1. Sparkonto 2. Lohnkonto 3. Steuerkonto 4. Tradingkonto
	 */

	private static Scanner eingabe;
	private static int weitereTransaktion;
	private static int kundenNr;
	private static Kunde betroffenerKunde;
	private static Konto betroffenesKonto;
	private static int kundenPIN;
	static KundeDAO kunden = new KundeDAO();
	static KontoDAO konten = new KontoDAO();

	public static void main(String args[]) {

		eingabe = new Scanner(System.in);

		List<Konto> k = konten.getAll();
		Collections.sort(k, Konto.KONTONR_CMP);
		System.out.println("Alle Konten (sortiert nach Konto Nummer): " + k);

		startProgram();

		betroffenerKunde = kunden.getKunde(logInCustomer());

		konten.getKonto(1).setKunde(betroffenerKunde);
		konten.getKonto(2).setKunde(betroffenerKunde);
		konten.getKonto(3).setKunde(betroffenerKunde);
		konten.getKonto(4).setKunde(betroffenerKunde);

//		tempKonto = konten.getKonto(betroffenesKonto());

		if (logInValidation()) {
			transaktion();
		}
	}

	private static boolean startProgram() {
		System.out.println("\nUm ihre Karte einzuschieben schreiben Sie 20 und drücken Sie Enter: ");
		while (eingabe.nextInt() != 20) {
			startProgram();
			return true;
		}
		return false;
	}

	private static int logInCustomer() {
		System.out.println("_____________________________________________________");
		System.out.println("\nBitte geben sie Ihre Kundennummer ein und drücken Sie Enter: ");
		return kundenNr = eingabe.nextInt();
	}

	private static boolean logInCustomerValidation() {
		// maybe getKunde not hashCode
		while (betroffenerKunde.getKundenNr() != kundenNr) {
			logInCustomerValidation();
			return true;
		}
		return false;
	}

	private static int logInPIN() {
		System.out.println("_____________________________________________________");
		System.out.println("\nBitte geben sie Ihren PIN Code ein und drücken Sie Enter: ");
		return kundenPIN = eingabe.nextInt();
	}

	private static boolean logInCustomerPINValidation() {
		if (betroffenerKunde.getKundenPIN() == kundenPIN) {
			return true;
		}
		return false;
	}

	private static boolean logInValidation() {
		if (logInCustomerValidation()) {
			logInPIN();
			if (logInCustomerPINValidation()) {
				return true;
			} else {
				System.out.println("Falscher PIN, versuchen Sie es erneut.");
				logInValidation();
			}
		} else {
			System.out.println("Diese Kundennummer existiert leider nicht, bitte versuchen Sie es erneut.");
			logInValidation();
		}
		return false;
	}

	private static Konto betroffenesKonto() {
		int auswahl;
		System.out.println("Bitte geben Sie das betroffene Konto an.");
		System.out.println("\n_____________________________________________________");
		System.out.println("\n1. Sparkonto");
		System.out.println("2. Lohnkonto");
		System.out.println("3. Steuerkonto");
		System.out.println("4. Tradingkonto");
		System.out.println("\n_____________________________________________________");
		System.out.println("\nWählen Sie ein Konto: ");

		auswahl = eingabe.nextInt();

		switch (auswahl) {
		case 1:
			return konten.getKonto(1);
		case 2:
			return konten.getKonto(2);
		case 3:
			return konten.getKonto(3);
		case 4:
			return konten.getKonto(4);
		default:
			System.out.println("Ungültige Option:\n\n");
			weitereTransaktion();
			break;
		}
		return null;

	}

	private static void transaktion() {
		// here is where most of the work is

		int auswahl;

		System.out.println("_____________________________________________________");
		System.out.println("\nHerzlich Wilkommen " + betroffenerKunde.toString() + "!");
		System.out.println("\n_____________________________________________________");

		System.out.println("\n1. Auszahlung");
		System.out.println("2. Einzahlung");
		System.out.println("3. Saldo auf Konto");
		System.out.println("4. Saldo auf Konto");
		System.out.println("5. Rückgabe der Karte");
		System.out.println("Bitte wählen Sie eine Option: ");

		auswahl = eingabe.nextInt();

		switch (auswahl) {
		case 1:
			betroffenesKonto();
			float auszahlbetrag;
			float auszuzahlen;
			System.out.println("Bitte geben Sie den Betrag für die Auszahlung ein.: ");
			auszahlbetrag = eingabe.nextFloat();
			if (auszahlbetrag > betroffenesKonto.getKontostand() || auszahlbetrag == 0) {
				System.out.println("Du hast nicht genügend Geld.\n\n");
				weitereTransaktion(); // ask if they want another transaction
			} else {
				// they have some cash
				// update balance
				auszuzahlen = betroffenesKonto.getKontostand() - auszahlbetrag;
				betroffenesKonto.setKontostand(auszuzahlen);
				System.out.println("Sie haben folgenden Betrag abgehoben: " + auszahlbetrag
						+ " und Ihr neuer Saldo ist " + betroffenesKonto.getKontostand() + "\n");
				weitereTransaktion();
			}
			break;

		case 2:
			// option number 2 is depositing
			betroffenesKonto();
			float einzahlbetrag;
			float einzuzahlen;
			System.out.println("Bitte geben Sie den Betrag ein, den Sie einzahlen möchten: ");
			einzahlbetrag = eingabe.nextFloat();
			// update balance
			einzuzahlen = einzahlbetrag + betroffenesKonto.getKontostand();
			betroffenesKonto.setKontostand(einzuzahlen);
			System.out.println("Sie haben folgenden Betrag einbezahlt: " + einzahlbetrag + " und Ihr neuer Saldo ist "
					+ betroffenesKonto.getKontostand() + "\n");
			weitereTransaktion();
			break;

		case 3:
			// this option is to check balance
			betroffenesKonto();
			System.out.println("Ihr Saldo ist: " + betroffenesKonto.getKontostand() + "\n");
			weitereTransaktion();
			break;

		case 4:
			// this option is to check balance
			betroffenesKonto();
			System.out.println("Ihr Saldo ist: " + konten.getAll() + "\n");
			weitereTransaktion();
			break;

		case 5:
			System.out.println("Ausgabe der Karte, danke dass Sie uns ausgewählt haben. Auf Wiedersehen!");
			break;

		default:
			System.out.println("Ungültige Option:\n\n");
			weitereTransaktion();
			break;
		}

	}

	private static void weitereTransaktion() {
		System.out.println("Möchten Sie eine weitere Transaktion durchführen?");
		System.out.println("Drücken Sie:\n1 für eine weitere Transaktion.\n2 Rückgabe der Karte");
		weitereTransaktion = eingabe.nextInt();
		if (weitereTransaktion == 1) {
			transaktion();
		} else if (weitereTransaktion == 2) {
			System.out.println("Ausgabe der Karte, danke dass Sie uns ausgewählt haben. Auf Wiedersehen!");
		} else {
			System.out.println("Ungültige Auswahl\n\n");
			weitereTransaktion();
		}
	}
}