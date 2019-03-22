package no.hvl.dat102.rekursjon;

public class Rekursiv {
	static int counter = 0;

	public static int nSum(int n) {
		if (n == 1) {
			return 1;
		}
		return n + nSum(n-1);
	}
	
	public static int nSekvens(int n) {
		if (n < 2) {
			return 0;
		}
		int a0 = 2;
		int a1 = 5;
		int a2;
		a2 = 5*a1 - 6*a0 + 2;
		if (n == 2) {
			return a2;
		}
		
		for (int i = 3; i <= n; i++) {
			a0 = a1;
			a1 = a2;
			a2 = 5*a1 - 6*a0 + 2;
		}
		
		return a2;
	} // 2, 5, 15, 47, osv...
	
	public static void towerHanoi(int n, char from_rod, char to_rod, char aux_rod) {
		if (n == 1) {
			System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod + ", counter = " + ++counter);
			return;
		}
		towerHanoi(n-1, from_rod, aux_rod, to_rod);
		System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod + ", counter = " + ++counter);
		towerHanoi(n-1, aux_rod, to_rod, from_rod);
	}
	
	public static void main(String[] args) {
		//System.out.println(nSum(100));
		//System.out.println(nSekvens(2));
		int n = 12;
		long timer = System.currentTimeMillis();
		towerHanoi(n, 'A', 'B', 'C');
		System.out.println((System.currentTimeMillis()-timer));
	}
}
