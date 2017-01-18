package org.tree;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

class Node implements Serializable
{
	private Node parent;
	private boolean isRoot=false;	
	private Node childs[]=new Node[4];
	private int numChilds;
	private DataItem dataItems[]=new DataItem[3];
	private int numItems;
	@Override
	public String toString() {
		String str="[";
		if(isDataItemsEmpty())
			return str+="]";
		for(int i=0;i<numItems;i++)
		{
			if(i==numItems-1)
				str+=dataItems[i]+"]";
			else
				str+=dataItems[i]+"|";
		}
		return str;
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isDataItemsFull()
	{
		return (numItems==3) ? true : false; 
		
	}
	public boolean isDataItemsEmpty()
	{
		return (numItems==0) ? true : false; 
		
	}
	public boolean isLeaf()
	{
		return (childs[0]==null) ? true : false; 
	}
	public int getNumItems()
	{ 
		return numItems; 
	}
	
	public int getNumChilds() {
		return numChilds;
	}
	public Node getChild(int childNo)
	{
		return childs[childNo];
	}
	public void addChild(Node childNode)
	{
		childs[numChilds++]=childNode;		
		
	}
	public void setNumChilds(int numChilds) {
		this.numChilds = numChilds;
	}
	public DataItem getDataItem(int itemNo)
	{
		return dataItems[itemNo];
	}
	public void insertItem(DataItem dataItem)
	{		
		if(isDataItemsFull())
		{
			System.out.println("DataItem is Full!");
			//We have to take different approaches when current node is root node.			
			if(isRoot)
			{
				//create three different nodes.
				isRoot=false;
				Node rootNode=new Node();				
				rootNode.isRoot=true;
				DataItem rootData=new DataItem(dataItems[1].data);				
				rootNode.insertItem(rootData);								
				TwoThreeFourTree.root=rootNode;
				
				Node rootLeftNode=new Node();				
				DataItem rootLeftData=new DataItem(dataItems[0].data);
				rootLeftNode.insertItem(rootLeftData);				
				rootNode.childs[0]=rootLeftNode;
				rootLeftNode.setParent(rootNode);
				
				Node rootRightNode=new Node();				
				DataItem rootRightData=new DataItem(dataItems[2].data);
				rootRightNode.insertItem(rootRightData);				
				rootNode.childs[1]=rootRightNode;
				rootRightNode.setParent(rootNode);
				
				rootNode.setNumChilds(2);		
				
				//now insert the new element
				if(dataItem.data<rootData.data)
				{
					rootLeftData=new DataItem(dataItem.data);
					rootNode.childs[0].insertItem(rootLeftData);	
				}
				else
				{
					rootRightData=new DataItem(dataItem.data);
					rootNode.childs[1].insertItem(rootRightData);
				}
				
			}
			//else normal routine can be invoked.
			else
			{
				//Remove current child from its parent.
				this.parent.removeChild(this);
				//create three different nodes.
				Node parentNode=new Node();								
				DataItem data=new DataItem(dataItems[1].data);				
				parentNode.insertItem(data);								
				this.parent.addChild(parentNode);
				
				Node leftNode=new Node();				
				DataItem leftData=new DataItem(dataItems[0].data);
				leftNode.insertItem(leftData);				
				parentNode.addChild(leftNode);
				leftNode.setParent(parentNode);
				
				Node rightNode=new Node();				
				DataItem rightData=new DataItem(dataItems[2].data);
				rightNode.insertItem(rightData);				
				parentNode.addChild(rightNode);
				rightNode.setParent(parentNode);
				
				parentNode.setNumChilds(2);		
				
				//now insert the new element
				if(dataItem.data<data.data)
				{
					data=new DataItem(dataItem.data);
					parentNode.childs[0].insertItem(leftData);	
				}
				else
				{
					rightData=new DataItem(dataItem.data);
					parentNode.childs[1].insertItem(rightData);
				}
			}
		}
		else if(isDataItemsEmpty())
		{
			dataItems[0]=dataItem;
			numItems++;
		}
		else
		{
			for(int i=0;i<numItems;i++)
			{
				if(dataItem.data > dataItems[i].data)
				{
					continue;
				}
				else
				{
					//insert and shift all elements to right
					for(int j=2;j>i;j--)
					{						
						dataItems[j]=dataItems[j-1];
					}
					dataItems[i]=dataItem;
					numItems++;
					System.out.println("Data Inserted successfully!");
					return;
				}
				
			}
			//This means all the elements are lesser than current dataItem. So insert at last position.
			dataItems[numItems++]=dataItem;
			System.out.println("Data Inserted successfully!");
		}
		
	}

	
	private void removeChild(Node childNode) 
	{
		for(int i=0;i<numChilds;i++)
		{
			if(childs[i]==childNode)
			{
				childs[i]=null;
				numChilds--;
				for(int j=i;j<3;j++)
				{
					childs[j]=childs[j+1];
				}
				return;
			}
		}
	}
	private void deleteItem(Node delNode,DataItem dataItem) 
	{
		if(delNode.isRoot)
		{
			if(delNode.isLeaf())
			{
				for(int i=0;i<numItems;i++)
				{
					if(delNode.getDataItem(i).equals(dataItem.data))
					{
						if(i==numItems-1)
						{
							delNode.dataItems[i]=null;
						}
						else
						{
							for(int j=i;j<numItems-1;j++)
							{
								delNode.dataItems[j]=delNode.dataItems[j+1];
							}
						}
						
					}
				}
				
			}
		}
	}

	private void setParent(Node node) {
		// TODO Auto-generated method stub
		this.parent=node;
		
	}
	
}
class DataItem implements Serializable
{
	int data;
	public DataItem(int no) {
		this.data=no;
	}
	@Override
	public String toString() {
		return Integer.toString(data);
	}
	
}
public class TwoThreeFourTree {
	static Node root=null;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);				
		System.out.println("-------------Binary tree instructions--------------");
		int isExit=9;
		while(isExit!=0)
		{
			System.out.println("Please press one of the options...");
			System.out.println("0:Exit");
			System.out.println("1:Insertion");
			System.out.println("2:Updation");
			System.out.println("3:Deletion");			
			System.out.println("4:Display the tree");
			System.out.println("5:Save the Binary Tree");
			System.out.println("6:Retrieve the Binary Tree from previously saved state");
			System.out.println("7:Find the node from data");
			System.out.println("8:Delete the node from data");
			
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
					insertDataItem(no);
					break;
				}
				case 4:
				{
					System.out.println("displaying the tree....");					
					displayTree();
					break;					
				}
				case 5:
				{
					System.out.println("Saving the tree....");					
					//save(root);
					break;					
				}
				case 6:
				{
					System.out.println("Retrieving the tree....");					
					//retrieve();
					break;				
				}
				
				case 7:
				{
					System.out.println("Enter the data to find the node:");
					int no=sc.nextInt();
					System.out.println("Searching through the tree....");					
					System.out.println(searchNode(root,no));
					break;					
				}
				case 8:
				{
					System.out.println("Enter the data to delete the node:");
					int no=sc.nextInt();
				//	deleteNode(no);					
					break;					
				}
				
			}
		}
	}
	
	private static void insertDataItem(int no)
	{
		if(root==null)
		{
			root=new Node();
			root.setRoot(true);
			DataItem d=new DataItem(no);
			root.insertItem(d);						
		}
		else
		{
			Node n=searchNode(root,no);
			DataItem d=new DataItem(no);		
			n.insertItem(d);
			
		}
	}
	 private static Node searchNode(Node root,int no) {
		// TODO Auto-generated method stub
		 DataItem dataItem=null;
		 Node n=root;
		 Node parent=root;
		 while(n!=null)
		 {
			 parent=n;
			 int numItems=n.getNumItems();
			 int pos=0;
			 for(pos=0;pos<numItems;pos++)
			 {
				 dataItem=n.getDataItem(pos);
				 if(no<=dataItem.data)
					 break;
			 }
			 if(dataItem.data==no)
			 {				 
				 return n;
			 }
				 
			 n=n.getChild(pos); 
		 }
		 
		return parent;
	}

	public static void displayTree()
     {
		 if(root==null)
		 {
			 System.out.println("No tree to display!");
			 return;
		 }		 
		 else
		 {			
			 recDisplayTree(root, 0, 0);
		 }
			 
     }
//-------------------------------------------------------------
	private static void recDisplayTree(Node thisNode, int level,int childNumber)
	{
		System.out.print("level="+level+" child="+childNumber+"");
		System.out.println(thisNode);
			// call ourselves for each child of this node
		int numItems = thisNode.getNumItems();
		for(int j=0; j<numItems+1; j++)
		{
			Node nextNode = thisNode.getChild(j);
			if(nextNode != null)
			recDisplayTree(nextNode, level+1, j);
			else
			return;
		}
	} // end
																																												// recDisplayTree()
	
	}
