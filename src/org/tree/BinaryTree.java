package org.tree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Node implements Serializable
{
	Node left, right;
    int data;
    public static final long serialVersionUID = -5225085453768948691L;

    public Node(int data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return this.data==((Node)obj).data;
	}
    
}

class BTreePrinter {

    public static void printNode(Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
public class BinaryTree {
	static Node root;
	static ArrayList<Node> nodeList=null;
	static int index=0;
	public static void insert(int val)
	{
		Node currentPtr=root;
		Node parentPtr=null;
		if(root==null)
		{
			root=new Node(val);
		}
		else
		{
			while(true)
			{
				parentPtr=currentPtr;
				if(val<currentPtr.data)
				{
					currentPtr=currentPtr.left;
					if(currentPtr==null)
					{
						parentPtr.left=new Node(val);
						break;
					}
					
				}
				else
				{
					currentPtr=currentPtr.right;
					if(currentPtr==null)
					{
						parentPtr.right=new Node(val);
						break;
					}
				}
				
			
			}
		}
		System.out.println("Node inserted successfully!");
	}
	public static void save(Node root) throws Exception
	{		
		nodeList=new ArrayList<Node>();
		
		if(root==null)
		{
			System.out.println("There is no elements in the list to save.");
			return;
		}
		Node n=root;
		saveRec(n);	
		System.out.println("Tree is:"+nodeList);
		FileOutputStream fio=new FileOutputStream("tree.txt");
		ObjectOutputStream Oout=new ObjectOutputStream(fio);
		Oout.writeObject(nodeList);
		nodeList=null;
	}
	public static void saveRec(Node node)
	{		
		if(node==null)
		{
			nodeList.add(null);
			return;
		}
			
				
		nodeList.add(node);
		saveRec(node.left);
		saveRec(node.right);

	}
	public static void retrieve() throws Exception
	{	
		FileInputStream fio=new FileInputStream("tree.txt");
		ObjectInputStream oip=new ObjectInputStream(fio);
		index=0;
		nodeList=(ArrayList<Node>) oip.readObject();
		System.out.println("Retrieved list from file is :"+nodeList);
		root=nodeList.get(index++);
		if(root==null)
		{
			System.out.println("There is no elements in the list to retrieve.");
			return;
		}
		
		retrieveRec(root);	
		System.out.println("Tree is:"+nodeList);
		
		
	}
	private static void retrieveRec(Node node) {
			
		Node n=nodeList.get(index++);
		if(n==null)
		{			
			return;
		}
		
		retrieveRec(n.left);
		retrieveRec(n.right);
				
		
	}
	private static Node findNode(Node root,int val)
	{
		if(root==null)
		{
			System.out.println("Tree is empty.");
			return null;
		}
		else
		{
			
			Node current=null;
			current=root;
			while(current!=null)
			{
				if(current.data==val)
				{
					return current;
				}
				else if(val< current.data)
				{					
					current=current.left;
				}
				else
				{				
					current=current.right;
				}
			}
			
		}
		return null;
			
	}
	public static void main(String[] args) throws Exception {
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
					insert(no);
					break;
				}
				case 4:
				{
					System.out.println("displaying the tree....");					
					BTreePrinter.printNode(root);
					break;					
				}
				case 5:
				{
					System.out.println("Saving the tree....");					
					save(root);
					break;					
				}
				case 6:
				{
					System.out.println("Retrieving the tree....");					
					retrieve();
					break;				
				}
				case 7:
				{
					System.out.println("Enter the data to find the node:");
					int no=sc.nextInt();
					System.out.println(findNode(root, no));
					break;
					
				}
				case 8:
				{
					System.out.println("Enter the data to delete the node:");
					int no=sc.nextInt();
					deleteNode(no);					
					break;
					
				}
				
			}
		}
		
	}
	private static void deleteNode(int no) {			
		if(root==null)
		{
			System.out.println("Tree is empty.");			
			return;
		}
		Node current=root;
		Node parent=root;
		boolean isLeft=false;
		boolean isRight=false;
		while(current!=null && current.data!=no)
		{
						
			if(no<current.data)
			{
				parent=current;				
				isRight=false;
				current=current.left;
			}
			else
			{				
				parent=current;				
				isRight=true;
				current=current.right;
			}			
		}
		if(current==null)
		{
			System.out.println("Node not found!");
		}
		else
		{
			System.out.println("Node is:"+current+", Its parent:"+parent+" left:"+isLeft+"  right:"+isRight);
			//Case 1:check whether it has a child or not..
			if(current.left==null&&current.right==null) 
			{
				//check whether node to be deleted is ROOT node or not.
				if(current==root)
				{
					//make ROOT =null
					root=null;					
				}
				else
				{
					if(isRight)
					{
						parent.right=null;
					}
					else
					{
						parent.left=null;
					}
					
				}
			}
			//Case 2:check whether it has a only right child
			else if(current.left==null && current.right!=null)
			{				
				if(current==root)
				{
					root=current.right;
				}
				else
				{
					if(isRight)
						parent.right=current.right;
					else
						parent.left=current.right;
				}
			}
			//Case 3:check whether it has a only left child
			else if(current.left!=null && current.right==null)
			{				
				if(current==root)
				{
					root=current.left;
				}
				else
				{
					if(isRight)
						parent.right=current.left;
					else
						parent.left=current.right;
				}
			}
			//Case 4:It must has a right & left both child i.e current.left!=null && current.right!=null
			else 
			{
				//find the successor of node to be deleted.
				Node successor=current.right;
				Node successorsParent=null;
				
				while(successor.left!=null)
				{
					successorsParent=successor;
					successor=successor.left;
				}
				System.out.println("Successor:"+successor+" successor's parent:"+successorsParent);
				if(current==root)
				{
					if(current.right==successor)
					{
						successor.left=current.left;
						root=successor;
					}
					else
					{
						successorsParent.left=successor.right;
						successor.right=root.right;
						successor.left=root.left;
						root=successor;
					}
				}
				else
				{
					if(current.right==successor)
					{
						successor.left=current.left;
						root=successor;
					}
					else
					{
						successorsParent.left=successor.right;
						successor.right=current.right;
						successor.left=current.left;
						if(isRight)
							parent.right=successor;
						else
							parent.left=successor;
					}
				}
			}
			System.out.println("Node deleted Successfully!");
		}
		
	}
}
