package no.hvl.dat102.sorteringTab;

import java.util.*;

class Radix {
	// A utility function to get maximum value in arr[]
	static int getMax(int[] tabell, int n) {
		int mx = tabell[0];
		for (int i = 1; i < n; i++)
			if (tabell[i] > mx)
				mx = tabell[i];
		return mx;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp. (eg. 300 is represented by 100)
	static void countSort(int[] tabell, int n, int exp) {
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[(tabell[i] / exp) % 10]++;

		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[(tabell[i] / exp) % 10] - 1] = tabell[i];
			count[(tabell[i] / exp) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to curent digit
		for (i = 0; i < n; i++)
			tabell[i] = output[i];
	}

	// The main function to that sorts arr[] of size n using
	// Radix Sort
	public static void radixSort(int[] tabell, int n) {
		// Find the maximum number to know number of digits
		int m = getMax(tabell, n);

		// Do counting sort for every digit. Note that instead
		// of passing digit number, exp is passed. exp is 10^i
		// where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(tabell, n, exp);
	}

	// A utility function to print an array
	static void print(int arr[], int n) {
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

}