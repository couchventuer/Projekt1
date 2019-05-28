package AtmMachine;

import java.util.Comparator;

public class Konto implements Comparable<Konto> {
	private int kontoNr;
	private String kontoName;
	private Kunde kunde;
	private double kontostand;

	// sortiert nach Attribut kontoNr
	public static Comparator<Konto> KONTONR_CMP = new Comparator<Konto>() {
		public int compare(Konto k1, Konto k2) {
			return k1.compareTo(k2);
		}
	};

	public Konto(int kontoNr, String kontoName, double kontostand) {
		if (kontoNr < 1 || kontoNr > 2)
			throw new IllegalArgumentException("Kontonummer ist nicht erlaubt: " + kontoNr);
		this.kontoNr = kontoNr;
		this.kontoName = kontoName;
		this.kontostand = kontostand;
	}

	public int getKontenNr() {
		return kontoNr;
	}

	public String toString() {
		return kontoName + ", (" + kontoNr + ")";
	}

	public boolean equals(Object k) {
		if (k == this)
			return true;
		if (k == null || k.getClass() != getClass())
			return false;
		Konto that = (Konto) k;
		return kontoNr == that.kontoNr;
	}

	public int hashCode() {
		return kontoNr;
	}

	public int compareTo(Konto k) {
		return kontoNr - k.kontoNr;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public double getKontostand() {
		return kontostand;
	}

	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}
}
