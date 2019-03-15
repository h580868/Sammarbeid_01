package no.hvl.dat102.medlem;

import no.hvl.dat102.mengde.tabell.TabellMengde;
import java.util.Iterator;

public class Datakontakt {
	private TabellMengde<Medlem> medlemTabell;
	
	public Datakontakt() {
		medlemTabell = new TabellMengde<Medlem>(10);
	}
	
	public Datakontakt(int antall) {
		medlemTabell = new TabellMengde<Medlem>(antall);
	}
	
	public int antallMedlemmer() {
		return medlemTabell.antall();
	}
	
	public void leggTilMedlem(Medlem person) {
		medlemTabell.leggTil(person);
	}
	
	public int finnMedlemIndeks(String medlemsnavn) {
		Medlem temp = new Medlem(medlemsnavn);
		int index = -1;
		Iterator<Medlem> iter = medlemTabell.oppramser();
		/*
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.equals(medlemsnavn)) {
				indexCounter = 0;
			}
		}
		iter = medlemTabell.oppramser(); */
		boolean found = false;
		int counter = 0;
		Medlem temp2 = null;
		while (iter.hasNext() && !found) {
			temp2 = iter.next();
			if (temp2.equals(temp)) {
				found = true;
			}
			counter++;
		}
		if (found) {
			index = counter;
		}
		
		return index;
	}

}
