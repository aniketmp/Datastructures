package org.tree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Heap implements Serializable
{	
	
	private int maxSize=8; // size of array
	private BNode[] heapArray=new BNode[maxSize];	
	private int currentSize=0; // number of nodes in array
	public void displayHeap()
	{
		System.out.print("heapArray: "); // array format	
		for(int m=0; m<currentSize; m++)
			if(heapArray[m] != null)
				System.out.print( heapArray[m].data + " ");
			else
				System.out.print( "-- ");
		System.out.println();
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item
		String dots = "...............................";
		System.out.println(dots+dots); // dotted top line
		while(currentSize > 0) // for each heap item
		{
			if(column == 0) // first item in row?
				for(int k=0; k<nBlanks; k++) // preceding blanks
					System.out.print(' ');
		// display item
				System.out.print(heapArray[j].data);
			if(++j == currentSize) // done?
				break;
			if(++column==itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			}
			else // next item on row
				for(int k=0; k<nBlanks*2-2; k++)
					System.out.print(' '); // interim blanks
		} // end for
		System.out.println("\n"+dots+dots); // dotted bottom line
	} // end displayHeap()
	public void insertItem(int no)
	{
		if(currentSize>=heapArray.length)
		{
			System.out.println("Cannot insert element...Array is full!");
			return;
		}
		BNode node=new BNode(no);
		heapArray[currentSize++]=node;
		heapify(currentSize-1);
		displayHeap();
	}
	private void heapify(int index) {
		int i=index;		
		while(i >0 && heapArray[((i-1)/2)]!=null && heapArray[((i-1)/2)].data < heapArray[i].data)
		{
			
			BNode temp=heapArray[((i-1)/2)];
			heapArray[((i-1)/2)]=heapArray[i];
			heapArray[i]=temp;
			i=((i-1)/2);
		}
	}
	private void deleteNode() 
	{
		if(currentSize<=0)
		{
			System.out.println("Tree is empty!");
			return;
		}
		BNode temp=heapArray[0];
		heapArray[0]=heapArray[currentSize-1];
		heapArray[currentSize-1]=temp;
		currentSize--;
		compareDown(0);
		System.out.println("Node +"+heapArray[currentSize]+" removed successfully...");
	}
	private void compareDown(int index) {
		int i=index;
		int largeIndex=i;
		BNode topNode=heapArray[i];	
		while(i<currentSize/2)
		{
			BNode leftChild=heapArray[(i*2)+1];
			BNode rightChild=heapArray[(i*2)+2];
			BNode currentNode=heapArray[i];				
		
			if((i*2)+2< currentSize && rightChild.data >leftChild.data )
			{
				largeIndex=(i*2)+2;
				
			}
			else
			{
				largeIndex=(i*2)+1;

			}
			if(currentNode.data>heapArray[largeIndex].data)
			{
				largeIndex=i;
				break;
			}
						
			heapArray[i]=heapArray[largeIndex];			
			i=largeIndex;			
		}		
		heapArray[largeIndex]=topNode;
	}
	private void changePriority(int index, int newPriority) {
		// TODO Auto-generated method stub
		int prevPriority=heapArray[index].data;
		if(index<0 || index>=currentSize || newPriority==prevPriority)
			return;
		heapArray[index].data=newPriority;
		if(newPriority>prevPriority)
		{
			
			heapify(index);
		}
		else
		{			
			compareDown(index);
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		
		Heap h=new Heap();
		Scanner sc=new Scanner(System.in);				
		System.out.println("-------------Heap tree instructions--------------");
		int isExit=9;
		while(isExit!=0)
		{
			System.out.println("Please press one of the options...");
			System.out.println("0:Exit");
			System.out.println("1:Insertion");							
			System.out.println("2:Display the tree");
			System.out.println("3:Save the Binary Tree");
			System.out.println("4:Retrieve the Binary Tree from previously saved state");			
			System.out.println("5:Remove the node from data");
			System.out.println("6:Change the priority");
			
			int option=sc.nextInt();			
			if(option==0)
			{
				System.out.println("Bye bye!");
				isExit=0;
				break;
			}
			
				
			switch(option)
			{
				case 1:
				{
					System.out.println("Enter element to insert:");
					int no=sc.nextInt();
					h.insertItem(no);
					break;
				}
				case 2:
				{
					System.out.println("displaying the tree....");					
					h.displayHeap();
					break;					
				}
				case 3:
				{
					System.out.println("Saving the tree....");					
					h.saveTree();
					break;					
				}
				case 4:
				{
					System.out.println("Retrieving the tree....");					
					h.retrieveTree();
					break;				
				}
				
				case 5:
				{
					System.out.println("Removing the node...");//here we cannot randomely delete the node. We have to delete root node.					
					h.deleteNode();					
					break;					
				}
				case 6:
				{
					System.out.println("Enter the index of the node to change priority and new priority..");					
					System.out.println("Enter the index first:");//here we cannot randomely delete the node. We have to delete root node.
					int index=sc.nextInt();
					System.out.println("Priority of node positioned at "+index+" to be changed. Now enter the new priority");
					int no=sc.nextInt();
					System.out.println("New priority that you enter is "+no);
					h.changePriority(index,no);
					break;					
				}
				
				default:
				{
					System.out.println("Please enter valid no...");
				}
				
			}
		}
	}
	
	private void retrieveTree() {
		FileInputStream fio;
		ObjectInputStream oip;
		try {
			fio = new FileInputStream("heap_tree.txt");
			oip=new ObjectInputStream(fio);
			Heap heap=(Heap) oip.readObject();
			this.heapArray=heap.heapArray;
			this.currentSize=heap.currentSize;
			this.maxSize=heap.maxSize;			
			System.out.println("Tree retrieved successfully....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void saveTree() {
		// TODO Auto-generated method stub
		
		try {
			FileOutputStream fio=new FileOutputStream("heap_tree.txt");
			ObjectOutputStream Oout=new ObjectOutputStream(fio);
			Oout.writeObject(this);
			System.out.println("Tree saved successfully....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

