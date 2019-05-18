package AtmMachine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KundeDAO {
	List<Kunde> all = new LinkedList<Kunde>();

	public KundeDAO() {// diese Kunden sollen existieren
		all.add(new Kunde(1000000, "Meier", "Gabi", 0000));
		all.add(new Kunde(2000000, "Maurer", "Rolf", 7261));
		all.add(new Kunde(1000001, "Wirth", "Nicola", 0173));
		all.add(new Kunde(1000002, "Casutt", "Ralf", 7723));
		all.add(new Kunde(1000003, "Breset", "Maurice", 7463));
		all.add(new Kunde(4000000, "Schindler", "Ueli", 4512));
		all.add(new Kunde(1999999, "Leuthard", "Dora", 9999));
	}

	// alle auf einmal
	public List<Kunde> getAll() {
		LinkedList<Kunde> rc = new LinkedList<Kunde>(all);
		Collections.sort(rc);
		return rc;
	}

	// ein ganz bestimmter Kunde, falls dieser existert (null, falls nicht)
	public Kunde getKunde(int kundenNr) {
		Kunde rc = null;
		for (Kunde k : all)
			if (k.getKundenNr() == kundenNr)
				return k;
		return rc;
	}

	public Kunde getKundeObject(Kunde ku) {
		Kunde rc = null;
		for (Kunde k : all)
			if (k.getKundenNr() == ku.getKundenNr())
				return k;
		return rc;
	}

	// schnelltest
	public static void main(String[] args) {
		KundeDAO data = new KundeDAO();
		System.out.println(data.getAll());
		System.out.println(data.getKunde(1000002));
	}
}
