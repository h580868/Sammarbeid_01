package no.hvl.dat102.mengde.tabell;

import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.mengde.adt.*;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell
	//
	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;

	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		T svar = null;
		if (antall > 0) {
			int indeks = tilf.nextInt(antall);
			svar = tab[indeks];
			tab[indeks] = tab[antall - 1];
			antall--;
		}
		return svar;
	}

	@Override
	public T fjern(T element) {
		// Søker etter og fjerner element.Retur med null ved ikke-funn
		// Copying from inneholder()

		boolean funnet = false;
		T svar = null;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
				tab[i] = tab[antall];
				tab[antall] = null;
				svar = element;
				antall--;
			}
		}

		/*
		 * Fyll ut
		 */
		return svar;
	}

	/*
	 * Lite effektiv!
	 * 
	 * @Override public MengdeADT<T> union(MengdeADT<T> m2) { TabellMengde<T> begge
	 * = new TabellMengde<T>(); for (int i = 0; i < antall; i++) {
	 * begge.leggTil(tab[i]); } Iterator<T> teller = m2.oppramser();
	 * 
	 * while (teller.hasNext()) { begge.leggTil(teller.next()); } return
	 * (MengdeADT<T>)begge; }
	 */
	
	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new TabellMengde<T>();

		// ... test if they're the same first maybe?
		if (this.equals(m2)) {
			return this;
		}
		// Fill with current
		begge.leggTilAlle(this);

		// Start comparing and add missing ones
		Iterator<T> iter = m2.oppramser();
		T element = null;
		while (iter.hasNext()) {
			element = iter.next();
			if (!begge.inneholder(element)) {
				begge.leggTil(element);
			}
		}
		/*
		 * Fyll ut
		 * 
		 */
		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new TabellMengde<T>();
		// test if they're the same... yes yes, redundant
		if (this.equals(m2)) {
			return this;
		}
		T element = null;
		Iterator<T> iter = m2.oppramser();
		while (iter.hasNext()) {
			element = iter.next();
			if (this.inneholder(element) && !snittM.inneholder(element)) {
				snittM.leggTil(element);
			}
		}

		/*
		 * Fyll ut
		 */
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new TabellMengde<T>();
		// test if they're the same... yes yes, redundant
		if (this.equals(m2) || (m2.erTom() && this.erTom())) {
			return differensM;
		}
		
		
		T element = null;
		Iterator<T> iter = null;
		MengdeADT<T> compare = null;
		
		if (m2.antall() < this.antall()) {
			iter = this.oppramser();
			compare = m2;
		} else {
			iter = m2.oppramser();
			compare = this;
		}
		

		while (iter.hasNext()) {
			element = iter.next();
			if (!compare.inneholder(element) && !differensM.inneholder(element)) {
				differensM.leggTil(element);
			}
		}
		/*
		iter = m2.oppramser();
		compare = this;
		
		while (iter.hasNext()) {
			element = iter.next();
			if (!compare.inneholder(element) && !differensM.inneholder(element)) {
				differensM.leggTil(element);
			}
		}*/
		/*
		 * Fyll ut
		 * 
		 * if (!m2.inneholder(element)) ((TabellMengde<T>) differensM).settInn(element);
		 */

		return differensM;
	}

	/*
	private void settInn(T element) {
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
		antall++;
	} */

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
			}
		}
		return (funnet);
	}

	@Override
	public boolean equals(MengdeADT<T> m2) {
		boolean likeMengder = true;
		// T element;
		Iterator<T> ref = m2.oppramser();
		if (m2.erTom() || m2.antall() != antall) {
			return false;
		}
		int counter = 0;
		while (likeMengder && ref.hasNext()) {
			if (tab[counter].equals(ref.next())) {
				counter++;
			} else {
				likeMengder = false;
			}
		}

		/*
		 * Fyll ut
		 */
		return likeMengder;
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return false;
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

}// class
