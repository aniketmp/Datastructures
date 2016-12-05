package org.stack;

public class StackUsingArray {
	int size=4;
	int stk[]=new int[size];
	int sp=0; //stack pointer pointing to initial array element.
	
	
	void push(int v)
	{		
		System.out.println("Pushing "+v+" into stack...");
		if(sp>=size)  //checking if stack is overflowing..
		{
			System.out.println("Stack overflow occurs!");
			return;
		}		
		stk[sp]=v;
		sp++;
		
	}
	int pop()
	{
		sp--;
		if(sp<0)  //checking if stack is empty..
		{			
			return -1;
		}		
		System.out.println("Popped out "+stk[sp]+" from stack...");
		return stk[sp];
	}
	void printStack()
	{
		System.out.println("\nPrinting the content of stack:");
		if(sp<=0) //This means stack is empty
		{
			System.out.println("|    |");
			System.out.println(" ----");
			System.out.println("Stack is empty!");
			return;
		}			
		for(int i=sp-1;i>=0;i--)
		{			
			System.out.println("| "+stk[i]+" |");
			System.out.println(" ---- ");
		}
		System.out.print("\n\n");		
	}
	public static void main(String[] args) {
		StackUsingArray cs=new StackUsingArray();
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
		
		
		
	}

}
