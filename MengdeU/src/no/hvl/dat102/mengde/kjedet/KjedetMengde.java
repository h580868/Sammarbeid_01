package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

import no.hvl.dat102.mengde.adt.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		if (!erTom()) {
			int valg = rand.nextInt(antall) + 1;
			if (valg == 1) {
				resultat = start.getElement();
				start = start.getNeste();
			} else {
				forgjenger = start;
				for (int nr = 2; nr < valg; nr++) {
					forgjenger = forgjenger.getNeste();
				}
				aktuell = forgjenger.getNeste();
				resultat = aktuell.getElement();
				forgjenger.setNeste(aktuell.getNeste());
			}
			antall--;
		} // if
		return resultat;
	}//

	@Override
	public T fjern(T element) {
		boolean funnet = false;
		LinearNode<T> forgjenger = null;
		LinearNode<T> aktuell = null;
		T resultat = null;
		// eating inneholder() again
		aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
				resultat = aktuell.getElement();
			} else {
				forgjenger = aktuell;
				aktuell = aktuell.getNeste();
			}
		}

		if (forgjenger == null && funnet) {
			start = start.getNeste();
			antall--;
		} else if (funnet) {
			forgjenger.setNeste(start.getNeste());
			antall--;
		}

		/*
		 * Fyll ut
		 * 
		 */
		return resultat;
	}//

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new KjedetMengde<T>();
		// LinearNode<T> aktuell = start;
		T element = null;

		if (this.equals(m2)) {
			return this;
		}
		Iterator<T> iter = m2.oppramser();
		begge.leggTilAlle(this);
		while (iter.hasNext()) {
			element = iter.next();
			if (!begge.inneholder(element)) {
				begge.leggTil(element);
			}
		}

		/*
		 * Fyll ut
		 */
		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();
		T element;
		Iterator<T> iter = m2.oppramser();
		while (iter.hasNext()) {
			element = iter.next();
			if (this.inneholder(element) && !snittM.inneholder(element)) {
				snittM.leggTil(element);
			}
		}
		/*
		 * Fyll ut...
		 * 
		 * if (this.inneholder(element)) ((KjedetMengde<T>) snittM).settInn(element);
		 */
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		T element;

		if (m2.equals(this)) {
			return this;
		}

		Iterator<T> iter;
		MengdeADT<T> compare;
		if (m2.antall() > this.antall()) {
			iter = m2.oppramser();
			compare = this;
		} else {
			iter = this.oppramser();
			compare = m2;
		}

		while (iter.hasNext()) {
			element = iter.next();
			if (!compare.inneholder(element) && !differensM.inneholder(element)) {
				differensM.leggTil(element);
			}
		}

		/*
		 * Fyll ut
		 * 
		 */

		return differensM;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public boolean equals(MengdeADT<T> m2) {
		boolean likeMengder = true;
		if (m2.antall() != this.antall()) {
			return false; // <-- I prefer this
			// likeMengder = false;
		}
		Iterator<T> iter = null;
		// T element = null;
		iter = m2.oppramser();
		while (iter.hasNext() && likeMengder) {
			if (!this.inneholder(iter.next())) {
				likeMengder = false;
			}
		}
		// Fyll ut
		return likeMengder;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return erUnderMengde;
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}

	public String toString() {
		// For klassen KjedetMengde
		String resultat = "";
		LinearNode<T> aktuell = start;
		while (aktuell != null) {
			resultat += aktuell.getElement().toString() + "\t";
			aktuell = aktuell.getNeste();
		}
		return resultat;
	}

}// class
