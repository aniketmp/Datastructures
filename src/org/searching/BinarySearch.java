package org.searching;

public class BinarySearch {
	static int size=10;
	static int arr[]={1,2,4,4,5,6,7,8,9,10}; //sorted array
	public static void main(String[] args) {
	
		
		System.out.println("Position of 3 is "+ search(3));
		System.out.println("Position of 2 is "+ search(2));
	
	}
	
	private static int search(int no) {
		// TODO Auto-generated method stub
		int lower=0;
		int upper=size-1;		
		while(true)
		{
			
			int current=(lower+upper)/2;
			System.out.println("Lower:"+lower+" Current:"+current+" Upper:"+upper);
			if(lower>current)
			{
				return -1;
			}
			if(no==arr[current])
			{
				return current;
			}
			if(no>arr[current])
			{
				lower=current+1;			
			}
			else
			{
				upper=current-1;			
			}
		}		
			
	}

}
