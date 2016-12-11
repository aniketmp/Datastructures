package org.searching;

public class BinarySearchWithRecursion {
	static int size=10;
	static int arr[]={1,2,4,4,5,6,7,8,9,10}; //sorted array
	public static void main(String[] args) {
	
		
		System.out.println("Position of 6 is "+ search(6,0,size-1));
		System.out.println("Position of 2 is "+ search(2,0,size-1));
	
	}
	
	
	private static int search(int no,int lower,int upper) {
		// TODO Auto-generated method stub
			
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
				return search(no,current+1,upper);
			}
			else
			{
				return search(no,lower,current);
			}
					
			
	}	
	
}
