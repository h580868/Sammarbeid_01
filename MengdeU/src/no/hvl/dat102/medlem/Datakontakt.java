package no.hvl.dat102.medlem;

import no.hvl.dat102.mengde.tabell.TabellMengde;
import java.util.Iterator;

public class Datakontakt extends TabellMengde<Medlem> {
	//private TabellMengde<Medlem> medlemTabell;
	
	public Datakontakt() {
		super();
	}
	
	public Datakontakt(int antall) {
		super(antall);
	}
	
	/*
	public void leggTilMedlem(Medlem person) {
		medlemTabell.leggTil(person);
	}*/
	
	public int finnMedlemIndeks(Medlem medlemtemp) {
		
		int index = -1;
		Iterator<Medlem> iter = this.oppramser();
		/*
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.equals(medlemsnavn)) {
				indexCounter = 0;
			}
		}
		iter = medlemTabell.oppramser(); */
		boolean found = false;
		int counter = -1;
		Medlem temp2 = null;
		while (iter.hasNext() && !found) {
			temp2 = iter.next();
			if (temp2.equals(medlemtemp)) {
				found = true;
			}
			counter++;
		}
		if (found) {
			index = counter;
		}
		//System.out.println(tab[index]);
		
		return index;
	}
	
	public int finnMedlemIndeks(String medlemsnavn) {
		Medlem temp = new Medlem(medlemsnavn);
		return finnMedlemIndeks(temp);
	}
	
	public Medlem finnMedlem(String medlemsnavn) {
		Medlem temp = new Medlem(medlemsnavn);
		Iterator<Medlem> iter = this.oppramser();
		boolean found = false;
		Medlem temp2 = null;
		Medlem referanse = null;
		while (iter.hasNext() && !found) {
			temp2 = iter.next();
			if (temp2.equals(temp)) {
				found = true;
				referanse = temp2;
			}
		}
		
		return referanse;
	}
	
	public int finnPartnerFor(String medlemsnavn) {
		int partner = -1;
		Medlem medlem = finnMedlem(medlemsnavn);
		
		Iterator<Medlem> iter = this.oppramser();
		Medlem temp = null;
		while (iter.hasNext()) {
			temp = iter.next();
			if (medlem.passerTil(temp) && !temp.harPartner()) {
				medlem.setPartner(temp);
				medlem.setStatusIndeks(this.finnMedlemIndeks(temp));
				temp.setPartner(medlem);
				temp.setStatusIndeks(this.finnMedlemIndeks(medlem));
			}
		}
		
		return partner;
	}
	
	public String toString() {
		String retur = "Medlemmer: \n";
		Iterator<Medlem> iter = this.oppramser();
		Medlem temp = null;
		while (iter.hasNext()) {
			temp = iter.next();
			retur += temp.toString();
			if (temp.harPartner()) {
				retur += " og " + tab[temp.getStatusIndeks()].getNavn();
			}
			retur += " hobbyer: " + temp.hobbyToString() + "\n";
		}
		return retur;
	}

}
