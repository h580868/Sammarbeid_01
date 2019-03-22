package no.hvl.dat102.dobbelkjedetordnetlistem;

import no.hvl.dat102.adt.DobbelKjedetOrdnetListeMADT;

//********************************************************************
//  
//  Representerer en dobbeltkjedet ordnet liste med midtpeker.
//********************************************************************
;

public class DobbelKjedetOrdnetListeM<T extends Comparable<T>> implements DobbelKjedetOrdnetListeMADT<T> {
//M for midtpeker
	private DobbelNode<T> foerste;
	private DobbelNode<T> midten;
	private DobbelNode<T> siste;
	private int antall;

	/******************************************************************
	 * Oppretter en tom liste.
	 ******************************************************************/
	// Konstruktør

	public DobbelKjedetOrdnetListeM(T minVerdi, T maksVerdi) {

		// Første node
		DobbelNode<T> nyNode1 = new DobbelNode<T>(minVerdi);
		foerste = nyNode1;
		midten = foerste;

		// Siste node
		DobbelNode<T> nyNode2 = new DobbelNode<T>();
		nyNode2.setElement(maksVerdi);
		nyNode1.setNeste(nyNode2);
		nyNode2.setForrige(nyNode1);
		siste = nyNode2;

		antall = 0;
	}

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************

	@Override
	public void leggTil(T el) {

		// Setter inn ordnet før den noden p peker på
		DobbelNode<T> p;
		boolean beforeMid = true;

		if ((el.compareTo(foerste.getElement()) <= 0) || (el.compareTo(siste.getElement()) >= 0)) {
			// Ugyldig. Alternativt kan vi ha det som et forkrav!
			System.out.println("Ugyldig verdi. verdi > " + foerste.getElement() + "verdi < " + siste.getElement());

		} else { // Kun lovlige verdier

			antall++;

			if (el.compareTo(midten.getElement()) >= 0) {// Finn plass i siste
															// halvdel
				beforeMid = false;
				p = midten.getNeste();
			} else { // Finn plass i første halvdel
				p = foerste.getNeste();
			}

			while (el.compareTo(p.getElement()) >= 0) {
				p = p.getNeste();
			} // while

			// Setter inn:
			// Innsett foran noden som p peker på

			DobbelNode<T> nyNode = new DobbelNode<T>(el);

			// Fyll ut med noen få setninger
			// System.out.println(p.getForrige().getElement());
			// System.out.println("Gammel: " + p.getElement() + "\nNy: " +
			// nyNode.getElement());

			p.getForrige().setNeste(nyNode);
			nyNode.setForrige(p.getForrige());
			p.setForrige(nyNode);
			nyNode.setNeste(p);
			// System.out.println("Ny node neste: " + nyNode.getNeste());

			// Oppdaterer ny midten
			// nyMidten();
			nyMidt(true, beforeMid);

		} // else lovlige

	}//

	// **********************************************************************************
	// Hjelpemetode til å finne ny midten.
	// Mindre effektiv fordi vi må gjennomgå ca halve listen, men greit nok,
	// ellers kan en teste på om antall er partall er oddetall ved oppdatering
	// av midtpeker
	// *********************************************************************************
	private void nyMidten() {
		int midtNR = antall / 2;
		DobbelNode<T> p = foerste.getNeste();
		for (int i = 1; i <= midtNR; i++) {
			p = p.getNeste();
		}
		midten = p;
	}//

	private void nyMidt(boolean increased, boolean beforeMid) {
		// System.out.println("Oddetall: " + antall % 2 != 0 + "Added? " + increased);
		/*
		 * if (increased && ((antall % 2) != 0)) {
		 * System.out.println(midten.getElement()); midten = midten.getNeste();
		 * System.out.println(midten.getElement()); } else if (!increased && ((antall %
		 * 2) == 0)) { System.out.println(midten.getElement()); midten =
		 * midten.getForrige(); System.out.println(midten.getElement()); }
		 */
		
		if (antall == 0) {
			midten = foerste;
			return;
		}

		int midtAntNy = antall / 2;
		int midtAntGam = 0;
		if (increased == true) {
			midtAntGam = (antall - 1) / 2;
		} else {
			midtAntGam = (antall + 1) / 2;
		}
		System.out.println("Antall: " + antall + "\nMidt: " + midtAntNy + " Gammel: " + midtAntGam);
		if (midtAntNy == midtAntGam && increased) {
			if (beforeMid) {
				midten = midten.getForrige();
				System.out.println("Added Changed middle -- " + midten.getElement());
			} else {
				midten = midten.getNeste();
				System.out.println("Added Changed middle ++ " + midten.getElement());
			}
			

		} else if (midtAntNy != midtAntGam && !increased) {
			if (beforeMid) {
				midten = midten.getNeste();
				System.out.println("Removed Changed middle ++ " + midten.getElement());
			} else {
				midten = midten.getForrige();
				System.out.println("Removed Changed middle -- " + midten.getElement());
			}
			
		}
	}

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************
	@Override
	public boolean fins(T el) {
		boolean funnet = false;
		DobbelNode<T> p = null;
		if ((el.compareTo(foerste.getElement()) <= 0) || (el.compareTo(siste.getElement()) >= 0)) {
			// Ugyldig. Alternativt kan vi ha det som et forkrav!
			System.out.println("Ugyldig verdi. verdi > " + foerste.getElement() + "verdi < " + siste.getElement());

		} else { // Kun lovlige verdier
			if (el.compareTo(midten.getElement()) >= 0) { // Let i siste halvdel
				p = midten; // Midten defineres å tilhøre siste del
			} else { // Let i første halvdel
				p = foerste.getNeste();
			}

			while (el.compareTo(p.getElement()) > 0) {
				p = p.getNeste();
			} // while

			// Test på funnet
			if (el.compareTo(p.getElement()) == 0) {
				funnet = true;
			}
		} // else
		return funnet;
	}

	public DobbelNode<T> finnEl(T el) { // return object or null
		boolean funnet = false;
		DobbelNode<T> p = null;
		if ((el.compareTo(foerste.getElement()) <= 0) || (el.compareTo(siste.getElement()) >= 0)) {
			// Ugyldig. Alternativt kan vi ha det som et forkrav!
			System.out.println("Ugyldig verdi. verdi > " + foerste.getElement() + "verdi < " + siste.getElement());

		} else { // Kun lovlige verdier
			if (el.compareTo(midten.getElement()) >= 0) { // Let i siste halvdel
				p = midten; // Midten defineres å tilhøre siste del
			} else { // Let i første halvdel
				p = foerste.getNeste();
			}

			while (el.compareTo(p.getElement()) > 0) {
				p = p.getNeste();
			} // while

			// Test på funnet
			if (el.compareTo(p.getElement()) == 0) {
				funnet = true;
			}
		} // else
		return p;
	}

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************
	// Omskrive til å bruke finn-metoden
	@Override
	public T fjern(T el) {
		// T resultat = null;
		DobbelNode<T> p = finnEl(el);
		boolean beforeMid = true;

		if (p == null) {
			return p.getElement();
		} else {
			--antall;
			if (el.compareTo(midten.getElement()) >= 0) {// Om siste halvdel
				beforeMid = false;
			}

			p.getForrige().setNeste(p.getNeste());
			p.getNeste().setForrige(p.getForrige());
			nyMidt(false, beforeMid);
			// nyMidten();

			return p.getElement();
		}

		/*
		 * boolean funnet = false;
		 * 
		 * if ((el.compareTo(foerste.getElement()) <= 0) ||
		 * (el.compareTo(siste.getElement()) >= 0)) { // Ugyldig. Alternativt kan vi ha
		 * det som et forkrav! System.out.println("Ugyldig verdi. verdi > " +
		 * foerste.getElement() + "verdi < " + siste.getElement());
		 * 
		 * } else { // Kun lovlige verdier
		 * 
		 * if (el.compareTo(midten.getElement()) >= 0) { p = midten; } else { p =
		 * foerste.getNeste(); }
		 * 
		 * while (el.compareTo(p.getElement()) > 0) { p = p.getNeste(); } // while
		 * 
		 * if (el.compareTo(p.getElement()) == 0) { funnet = true; }
		 * 
		 * if (funnet) { // Tar ut antall = antall - 1; // Fyll ut med noen få
		 * setninger.
		 * 
		 * // Oppadtere midten nyMidten();
		 * 
		 * resultat = p.getElement();
		 * 
		 * } // funnet
		 * 
		 * } // lovlige return resultat;
		 */
	}//

	/* Alternativ kan fjern-metoden bruke finn-metoden */

	private DobbelNode<T> finn(T el) {
		DobbelNode<T> node = null;
		DobbelNode<T> p = null;

		// Kun lovlige verdier
		if (el.compareTo(midten.getElement()) >= 0) { // Let i siste halvdel
			p = midten; // Midten defineres å tilhøre siste del
		} else { // Let i første halvdel
			p = foerste.getNeste();
		}
		while (el.compareTo(p.getElement()) > 0) {
			p = p.getNeste();
		} // while

		// Test på funnet
		if (el.compareTo(p.getElement()) == 0) {
			node = p;
		}
		return node;
	}

	// ***********************************************************************************
	// *
	// *
	// ***********************************************************************************

	public void skrivListe() {
		DobbelNode<T> p = foerste;

		while (p != null) {
			System.out.print(p.getElement() + " ");
			p = p.getNeste();
		}

		System.out.println(
				"Foerste:" + foerste.getElement() + " Midten:" + midten.getElement() + " Siste:" + siste.getElement());
		System.out.println();

	}//

}// class
