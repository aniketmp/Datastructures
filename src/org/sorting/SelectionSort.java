package org.sorting;

public class SelectionSort {
	static int[] a={5,3,2,7,1}; // ref to array a
	static int nElems=a.length;
	static int min;

	static public void selectionSort()
	{		
		for(int i=0; i<nElems-1; i++) // outer loop (backword)
		{
			min=i;
			for(int j=i; j<nElems; j++) // inner loop (forward)
			{
				if( a[j] < a[min] ) // out of order?
					min=j; // swap them
			}
			swap(i,min);
		}		
	} 
		 
	

	static void swap(int one, int two) {
		int temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println("Array Before Selection Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
        System.out.println();  
          
        selectionSort();//sorting array elements using bubble sort  
         
        System.out.println("Array After Selection Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
	}
}


/*
 
The selection sort performs the same number of comparisons as the bubble sort: N*(N–
1)/2. For 10 data items, this is 45 comparisons. However, 10 items require fewer than 10
swaps. With 100 items, 4,950 comparisons are required, but fewer than 100 swaps. For
large values of N, the comparison times will dominate, so we would have to say that the
selection sort runs in O(N2) time, just as the bubble sort did. However, it is unquestionably
faster because there are so few swaps. For smaller values of N, it may in fact be
considerably faster, especially if the swap times are much larger than the comparison
times.
 */
