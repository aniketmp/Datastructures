package org.stack;
class Node
{
	int v;
	Node nextNode;	
}
public class StackUsingLinkedList {
	Node head;
	
	void push(int v)
	{		
		System.out.println("Pushing "+v+" into stack...");
		Node node=new Node();
		node.v=v;
		node.nextNode=head;
		head=node;
			
	}
	int pop()
	{
		if(head==null)
		{
			return -1;
		}
		int value=head.v;
		System.out.println("Popped out "+value+" from stack...");
		head=head.nextNode;
		return value;
	}
	void printStack()
	{
			System.out.println("\nPrinting the content of stack:");			
			System.out.print("head [X]");
			if(head==null)
			{
				System.out.print(" empty..\n\n");
				return;
			}
			Node printPtr=head;
			while(printPtr!=null)
			{				
				System.out.print("--->["+printPtr.v+"]");
				printPtr=printPtr.nextNode;
			}
			System.out.println("\n");
	}
	public static void main(String[] args) {
		StackUsingLinkedList cs=new StackUsingLinkedList();
		cs.printStack();
		cs.push(30);
		cs.printStack();
		cs.push(60);
		cs.printStack();
		cs.push(10);
		cs.printStack();
		cs.push(50);
		cs.printStack();
		
		cs.pop();
		cs.printStack();
		cs.pop();
		cs.printStack();
		cs.pop();
		cs.printStack();
		cs.pop();
		cs.printStack();
		cs.pop();
		cs.printStack();
		
		
	}
}
