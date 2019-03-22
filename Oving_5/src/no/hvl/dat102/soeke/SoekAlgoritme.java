package no.hvl.dat102.soeke;

public class SoekAlgoritme {
	static int[] liste = { 2, 4, 5, 7, 8, 10, 12, 15, 18, 21, 23, 27, 29, 30, 31 };

	public static int soek(int t) {
		int antall = liste.length;
		int l = 0;
		int r = antall - 1;
		int m;
		while (l <= r) {
			m = Math.floorDiv((l + r), 2);
			System.out.println("M = " + m);
			if (liste[m] < t) {
				l = m + 1;
			} else if (liste[m] > t) {
				r = m - 1;
			} else {
				return m;
			}
			System.out.println("L = " + l + "\nR = " + r);
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println("Indeks for tall: " + soek(16));

	}
}
