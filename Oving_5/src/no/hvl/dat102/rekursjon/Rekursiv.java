package no.hvl.dat102.rekursjon;

public class Rekursiv {

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
	} // 2, 5, 15, 47, 
	
	public static void main(String[] args) {
		System.out.println(nSum(100));
		System.out.println(nSekvens(2));
	}
}
