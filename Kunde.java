package AtmMachine;

import java.util.Collections;
import java.util.Comparator;

public class Kunde implements Comparable<Kunde> {
	private int kundenNr;
	private String name;
	private String vorname;
	private int PIN;

	// sortiert nach Attribut name
	public static Comparator<Kunde> NAME_CMP = new Comparator<Kunde>() {
		public int compare(Kunde k1, Kunde k2) {
			return k1.name.compareTo(k2.name);
		}
	};

	public Kunde(int kundenNr, String name, String vorname, int PIN) {
		if (kundenNr < 1000000 || kundenNr > 9999999)
			throw new IllegalArgumentException("Kundennummer ist nicht erlaubt: " + kundenNr);
		this.kundenNr = kundenNr;
		this.name = name;
		this.vorname = vorname;
		this.PIN = PIN;
	}

	public int getKundenNr() {
		return kundenNr;
	}

	public int getKundenPIN() {
		return PIN;
	}

	public String toString() {
		return name + ", " + vorname + " (" + kundenNr + ")";
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null || o.getClass() != getClass())
			return false;
		Kunde that = (Kunde) o;
		return kundenNr == that.kundenNr;
	}

	public int hashCode() {
		return kundenNr;
	}

	public int compareTo(Kunde o) {
		return kundenNr - o.kundenNr;
	}
}
