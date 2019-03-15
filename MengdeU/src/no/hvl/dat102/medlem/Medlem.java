package no.hvl.dat102.medlem;

import java.util.Iterator;

import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	private String navn;
	private KjedetMengde<Hobby> hobbyer; // Eller TabellMengde
	private int statusIndeks;

//... Konstruktør
//... Andre metoder
	public Medlem(String navn) {
		this.navn = navn;
		hobbyer = new KjedetMengde<Hobby>();
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void leggTilHobby(String hobby) {
		hobbyer.leggTil(new Hobby(hobby));
	}
	
	/*
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < navn.length(); i++) {
			result += navn.charAt(i);
		}
		return result;
	} */

	public boolean equals(Medlem medlem) {
		if (medlem == this) {
			return true;
		}
		if (navn.equals(medlem.getNavn())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hobbyInneholder(Hobby element) {
		return hobbyer.inneholder(element); // True if contains said element.
	}

	public boolean passerTil(Medlem medlem2) {
		boolean passer = true;

		Iterator<Hobby> iter = hobbyer.oppramser();

		Hobby checkAgainst;
		while (iter.hasNext() && passer) {
			checkAgainst = iter.next();
			if (!medlem2.hobbyInneholder(checkAgainst)) {
				passer = false;
			}
		}

		return passer;
	}

	public String hobbyToString() {
		Iterator<Hobby> iter = hobbyer.oppramser();
		String result = "<" + iter.next();
		while (iter.hasNext()) {
			result += "," + iter.next();
		}
		return result + ">";
	}
}
