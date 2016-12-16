package org.sorting;

import java.util.ArrayList;

public class MergeSort {

static int a[] = { 14, 1, 91, 62, 72, 31, 33, 53, 42, 74 };
static int b[]=new int[10];

	public static void main(String str[]) { 
	   int i;
	   int max=10;
	   System.out.println("List before sorting\n");
	   
	   for(i = 0; i < max; i++)
		   System.out.print(" "+ a[i]);
	   System.out.println();
	   sort(0, max-1);
	
	   System.out.println("\nList after sorting\n");
	   
	   for(i = 0; i < max; i++)
		   System.out.print(" "+a[i]);
	}

	private static void sort(int lowerPtr, int higherPtr) {
		// TODO Auto-generated method stub
		int midPtr=(lowerPtr+higherPtr)/2;
		System.out.println("Sorting "+lowerPtr+" "+midPtr+" "+higherPtr);
		if(lowerPtr==higherPtr)
			return;
		System.out.println("lower half:"+lowerPtr+" "+midPtr);
		sort(lowerPtr,midPtr);
		System.out.println("higher half:"+(midPtr+1)+" "+higherPtr);
		sort(midPtr+1,higherPtr);		
		System.out.println("Merging "+lowerPtr+" "+midPtr+" "+(midPtr+1)+" "+higherPtr);
		merge(lowerPtr,midPtr,midPtr+1,higherPtr);
		
	}

	private static void merge(int lowerPtr, int lmidPtr, int hmidPtr, int higherPtr) {
	// TODO Auto-generated method stub
		int i,j,k;
		for(i=lowerPtr,j=hmidPtr,k=lowerPtr;i<=lmidPtr&&j<=higherPtr;k++)
		{
			System.out.println("a[i]="+a[i]+"  a[j]:"+a[j]);
			if(a[i]<a[j])
			{
				b[k]=a[i++];
			}
			else
			{
				b[k]=a[j++];
			}
		}
		while(i<=lmidPtr)
		{
			b[k++]=a[i++];
		}
		while(j<=hmidPtr)
		{
			b[k++]=a[j++];
		}
		for(i=lowerPtr;i<=higherPtr;i++)
		{			
			a[i]=b[i];
		}
		System.out.print("a[");
		for(i=lowerPtr;i<=higherPtr;i++)
		{			
			System.out.print(" "+a[i]);
		}
		System.out.println("]");
		System.out.print("b[");
		for(i=lowerPtr;i<=higherPtr;i++)
		{			
			System.out.print(" "+b[i]);
		}
		System.out.println("]");
		
	}
}
