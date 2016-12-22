package org.sorting;

public class ShellSort {
	static int[] a={5,4,9,2}; // ref to array a
	static int temp;
	static int pos;
	static int step;

	static public void insertionSort()
	{		
		step=a.length/2;
		while(step>=1)
		{
			for(int i=0; i<a.length; i=i+step) 
			{							
				pos=i;
				temp=a[pos];				
				while(pos>=0 && (pos-step)>=0 && a[pos-step]>temp)
				{
					a[pos]=a[pos-step];
					pos=pos-step;
				}
				a[pos]=temp;
			}
			step=step/2;
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


