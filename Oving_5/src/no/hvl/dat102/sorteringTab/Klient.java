package no.hvl.dat102.sorteringTab;

public class Klient {
	static final int antall = 10;
	static final int n = 500;

	static SorteringsTabell tabTab;
	static long start;
	static long stop;

	static void setup() {
		tabTab = new SorteringsTabell(n, antall);
	}

	static void timerStart() {
		start = System.nanoTime();
	}

	static void timerStop() {
		stop = (System.nanoTime() - start);
	}

	public static int log2nlz(int bits) {
		if (bits == 0)
			return 0; // or throw exception
		return 31 - Integer.numberOfLeadingZeros(bits);
	}

	static double theoreticalTime() {
		return (stop / (tabTab.getN() * (Math.log(tabTab.getN()) / Math.log(2))));
	}

	public static void main(String[] args) {
		setup();

		// System.out.println(log2nlz(tabTab.getN()) + " OR " + (Math.log(tabTab.getN())
		// / Math.log(2)));

		timerStart();
		for (int i = 0; i < antall; i++) {
			tabTab.radixSorting(i);
		}
		timerStop();
		tabTab.printArray(0);
		System.out.println("Approximate nanoseconds taken per radixSort, " + tabTab.getAnt() + " sorts of " + tabTab.getN()
				+ ": " + stop / 10);
		System.out.println("Constant C: " + theoreticalTime());
		tabTab.reset();

	}
}
