package AtmMachine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

public class KontoDAO {
	List<Konto> all = new LinkedList<Konto>();

	/*
	 * Konten: 1. Sparkonto 2. Lohnkonto 3. Steuerkonto 4. Tradingkonto
	 */

	public KontoDAO() {// diese Kunden sollen existieren
		all.add(new Konto(1, "Sparkonto", 0));
		all.add(new Konto(2, "Lohnkonto", 0));
		all.add(new Konto(3, "Steuerkonto", 0));
		all.add(new Konto(4, "Tradingkonto", 0));
	}

	// alle auf einmal
	public List<Konto> getAll() {
		LinkedList<Konto> rc = new LinkedList<Konto>(all);
		Collections.sort(rc);
		return rc;
	}

	// ein ganz bestimmter Kunde, falls dieser existert (null, falls nicht)
	public Konto getKonto(int kontoNr) {
		Konto rc = null;
		for (Konto k : all)
			if (k.getKontenNr() == kontoNr)
				return k;
		return rc;
	}

	// schnelltest
	public static void main(String[] args) {
		KontoDAO data = new KontoDAO();
		System.out.println(data.getAll());
		System.out.println(data.getKonto(10000002));
	}
}
