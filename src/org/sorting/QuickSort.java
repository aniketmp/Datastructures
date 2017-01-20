package org.sorting;

public class QuickSort {
	static int[] a={31,14,43,22,9}; // ref to array a

	private static void quickSort(int first,int last)
	{		
		if(last-first<=0) 
		{
			return;
		}
			
		int partition=partition(first, last);
		quickSort(first, partition-1);
		quickSort(partition+1, last);
	}
	private static int partition(int first,int last) {
		int rightArr=last;
		int pivot=first;
		int leftArr=first+1;
		while(true)
		{
			while(leftArr<=rightArr)
			{
				if(a[leftArr]>a[pivot])
				{
					break;
				}
				leftArr++;
			}
			while(rightArr>=leftArr)
			{
				if(a[rightArr]<a[pivot])
				{
					break;
				}
				rightArr--;
			}
			if(leftArr>rightArr)
			{
				swap(pivot,rightArr);
				pivot=rightArr;
				return pivot;
			}
			else
			{
				swap(leftArr,rightArr);
			}
		}
		
	}
	
	static void swap(int one, int two) {
		int temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println("Array Before Quick Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
        System.out.println();  
          
        quickSort(0,a.length-1);//sorting array elements using bubble sort  
         
        System.out.println("Array After Quick Sort");  
        for(int i=0; i < a.length; i++){  
                System.out.print(a[i] + " ");  
        }  
	}

	
}
