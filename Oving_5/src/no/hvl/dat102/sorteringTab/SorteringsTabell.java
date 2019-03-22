package no.hvl.dat102.sorteringTab;

import java.util.Random;

import no.hvl.dat102.sorteringTab.Radix;

public class SorteringsTabell {
	int[][] tabell;
	int[][] backup;
	
	Random tilfeldig;
	int n ;
	int ant;

	public SorteringsTabell(int n, int ant) {
		this.n = n;
		this.ant = ant;
		tabell = new int[ant][n];
		backup = new int[ant][n];
		tilfeldig = new Random();
		setup();
	}
	
	void setup() {
		tilfeldig.nextInt(1500);
		for (int i = 0; i < ant; i++) {
			for(int j = 0; j < n; j++) {
				tabell[i][j] = tilfeldig.nextInt(1500);
			}
		}
		backup();
	}
	
	void backup() {
		for (int i = 0; i < ant; i++) {
			for (int j = 0; j < n; j++) {
				backup[i][j] = tabell[i][j];
			}
		}
	}

	public void reset() {
		for (int i = 0; i < ant; i++) {
			for (int j = 0; j < n; j++) {
				tabell[i][j] = backup[i][j];
			}
		}
	}
	
	public int getAnt() {
		return ant;
	}
	
	public int getN() {
		return n;
	}

	public void printArray(int i) {
		System.out.print("Tab: ");
		for (int j = 0; j < n - 1; j++) {
			for (int k = 0; k < 35; k++) {
				System.out.print(tabell[i][j] + ", ");				
			}
			System.out.print("\n");
		}
		System.out.print(tabell[i][n - 1] + "\n");
	}

	private int[] swap(int[] tab, int a, int b) {
		int temp = tab[a];
		tab[a] = tab[b];
		tab[b] = temp;
		return tab;
	}

	void insertSort(int[] tab, int n) {
		if (n > 0) {
			insertSort(tab, n - 1);
			int x = tab[n];
			int j = n - 1;
			while (j >= 0 && tab[j] > x) {
				// printArray(tab);
				tab[j + 1] = tab[j];
				j = j - 1;
			}
			tab[j + 1] = x;
		}

	}
	
	public void insertSorting(int i) {
		insertSort(tabell[i], n-1);
	}

	void selectSort(int[] tab, int n) {
		int i, j;
		for (j = 0; j < n - 1; j++) {
			int iMin = j;
			for (i = j + 1; i < n; i++) {
				if (tab[i] < tab[iMin]) {
					iMin = i;
				}
			}
			if (iMin != j) {
				tab = swap(tab, j, iMin);
			}
		}

	}
	
	public void selectSorting(int i) {
		selectSort(tabell[i], n);
	}

	void bubbleSort(int[] tab, int n) {
		// boolean swapped = true;
		do {
			int newn = 0;
			for (int i = 0; i < n; i++) {
				if (tab[i] > tab[i + 1]) {
					tab = swap(tab, i, i + 1);
					newn = i;
				}
			}
			n = newn;
		} while (n > 1);
	}
	
	public void bubbleSorting(int i) {
		bubbleSort(tabell[i], n - 1);
	}

	int partition(int[] tab, int low, int high) {
		// pivot (Element to be placed at right position)
		int pivot = tab[high];

		int i = (low - 1); // Index of smaller element

		for (int j = low; j <= high - 1; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (tab[j] <= pivot) {
				i++; // increment index of smaller element
				tab = swap(tab, i, j);
			}
		}
		tab = swap(tab, i + 1, high);
		return (i + 1);
	}

	void quickSort(int[] tab, int low, int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, tab[pi] is now at right place
			 */
			int pi = partition(tab, low, high);

			quickSort(tab, low, pi - 1); // Before pi
			quickSort(tab, pi + 1, high); // After pi
		}
	}
	
	public void quickSorting(int i) {
		quickSort(tabell[i], 0, n - 1);
	}

	// Array A[] has the items to sort; array B[] is a work array.
	void TopDownMergeSort(int[] A, int[] B, int n) {
		CopyArray(A, 0, n, B); // duplicate array A[] into B[]
		TopDownSplitMerge(B, 0, n, A); // sort data from B[] into A[]
	}

	// Sort the given run of array A[] using array B[] as a source.
	// iBegin is inclusive; iEnd is exclusive (A[iEnd] is not in the set).
	void TopDownSplitMerge(int[] B, int iBegin, int iEnd, int[] A) {
		if (iEnd - iBegin < 2) // if run size == 1
			return; // consider it sorted
		// split the run longer than 1 item into halves
		int iMiddle = (iEnd + iBegin) / 2; // iMiddle = mid point
		// recursively sort both runs from array A[] into B[]
		TopDownSplitMerge(A, iBegin, iMiddle, B); // sort the left run
		TopDownSplitMerge(A, iMiddle, iEnd, B); // sort the right run
		// merge the resulting runs from array B[] into A[]
		TopDownMerge(B, iBegin, iMiddle, iEnd, A);
	}

	// Left source half is A[ iBegin:iMiddle-1].
	// Right source half is A[iMiddle:iEnd-1 ].
	// Result is B[ iBegin:iEnd-1 ].
	void TopDownMerge(int[] A, int iBegin, int iMiddle, int iEnd, int[] B) {
		int i = iBegin;
		int j = iMiddle;

		// While there are elements in the left or right runs...
		for (int k = iBegin; k < iEnd; k++) {
			// If left run head exists and is <= existing right run head.
			if (i < iMiddle && (j >= iEnd || A[i] <= A[j])) {
				B[k] = A[i];
				i = i + 1;
			} else {
				B[k] = A[j];
				j = j + 1;
			}
		}
	}

	void mergeSort(int[] tab, int n) {
		TopDownMergeSort(tab, new int[n], n);
	}
	
	public void mergeSorting(int i) {
		mergeSort(tabell[i], n);
	}

	void CopyArray(int[] A, int iBegin, int iEnd, int[] B) {
		for (int k = iBegin; k < iEnd; k++)
			B[k] = A[k];
	}

	public void radixSorting(int i) {
		Radix.radixSort(tabell[i], n);
	}

}
