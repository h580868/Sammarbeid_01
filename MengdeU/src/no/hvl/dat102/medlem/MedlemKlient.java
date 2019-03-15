package no.hvl.dat102.medlem;

public class MedlemKlient {
	
	private static Datakontakt kontaktListe;
	
	private static void setup() {
		kontaktListe = new Datakontakt();
		Medlem medlem1 = new Medlem("Banana");
		medlem1.leggTilHobby("dansing");
		medlem1.leggTilHobby("skriking");
		
		Medlem medlem2 = new Medlem("Agurk");
		medlem2.leggTilHobby("dansing");
		medlem2.leggTilHobby("skriking");
		System.out.println(medlem2.getNavn());
		System.out.println(medlem2.equals(new Medlem("Agurk")));
		
		
		kontaktListe.leggTilMedlem(medlem2);
		kontaktListe.leggTilMedlem(medlem1);
		
		System.out.println(kontaktListe.finnMedlemIndeks(medlem2.getNavn()));
	}

	public static void main(String[] args) {
		setup();
		
		System.out.println(kontaktListe.finnMedlemIndeks("Agurk"));
		System.out.println(kontaktListe);
	}

}
