package org.sorting;

public class BubbleSort {
	static int[] a={5,3,2}; // ref to array a
	static int nElems=a.length;

	static public void bubbleSort()
	{
		
		for(int i=0; i<nElems-1; i++) // outer loop (backword)
		{
			for(int j=1; j<nElems-i; j++) // inner loop (forward)
			{
				if( a[j-1] > a[j] ) // out of order?
					swap(j-1, j); // swap them
			}
				
		}
		
	} 
		 
	

	static void swap(int one, int two) {
		int temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println("Array Before Bubble Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
        System.out.println();  
          
        bubbleSort();//sorting array elements using bubble sort  
         
        System.out.println("Array After Bubble Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
	}
}


/*
 
In general, where N is the number of items in the array, there are N�1 comparisons on
the first pass, N�2 on the second, and so on. The formula for the sum of such a series is
(N�1) + (N�2) + (N�3) + ... + 1 = N*(N�1)/2
N*(N�1)/2 is 45 when N is 10.
Thus the algorithm makes about N2/2 comparisons (ignoring the �1, which doesn't make
much difference, especially if N is large).
There are fewer swaps than there are comparisons, because two bars are swapped only
if they need to be. If the data is random, a swap is necessary about half the time, so there
will be about N2/4 swaps. (Although in the worst case, with the initial data inversely
sorted, a swap is necessary with every comparison.)
Both swaps and comparisons are proportional to N2. Because constants don't count in
Big O notation, we can ignore the 2 and the 4 and say that the bubble sort runs in O(N2)
time.
 
 */
