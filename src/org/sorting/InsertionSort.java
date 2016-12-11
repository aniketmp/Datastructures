package org.sorting;

public class InsertionSort {
	static int[] a={5,4,9,2}; // ref to array a
	static int temp;
	static int pos;

	static public void insertionSort()
	{		
		for(int i=1; i<a.length; i++) // outer loop (backword)
		{			
			pos=i;
			temp=a[pos];
			while(pos>0 && a[pos-1]>temp)
			{
				a[pos]=a[pos-1];
				pos--;
			}
			a[pos]=temp;
		}
		
	} 
		 
	

	static void swap(int one, int two) {
		int temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println("Array Before InsertionSort Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
        System.out.println();  
          
        insertionSort();//sorting array elements using bubble sort  
         
        System.out.println("Array After InsertionSort Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
	}
}


/*
 
How many comparisons and copies does this algorithm require? On the first pass, it
compares a maximum of one item. On the second pass, it’s a maximum of two
items, and so on, up to a maximum of N-1 comparisons on the last pass. This is
1 + 2 + 3 + … + N-1 = N*(N-1)/2
However, because on each pass an average of only half of the maximum number of
items are actually compared before the insertion point is found, we can divide by 2,
which gives
N*(N-1)/4
The number of copies is approximately the same as the number of comparisons.
However, a copy isn’t as time-consuming as a swap, so for random data this algorithm
runs twice as fast as the bubble sort and faster than the selection sort.
In any case, like the other sort routines in this chapter, the insertion sort runs in
O(N2) time for random data.
For data that is already sorted or almost sorted, the insertion sort does much better.
When data is in order, the condition in the while loop is never true, so it becomes a
simple statement in the outer loop, which executes N-1 times. In this case the algorithm
runs in O(N) time. If the data is almost sorted, insertion sort runs in almost
O(N) time, which makes it a simple and efficient way to order a file that is only
slightly out of order.
However, for data arranged in inverse sorted order, every possible comparison and
shift is carried out, so the insertion sort runs no faster than the bubble sort. You can
check this using the reverse-sorted data option (toggled with New) in the InsertSort
Workshop applet.
 
 */
