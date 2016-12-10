package org.queue;


class CircularQueueUsingArray{
	static int SIZE=3;
	static int front=-1,rear=-1;
	static int cQueue[]=new int[SIZE];
	public static void main(String[] args) {
//		display();
		enQueue(10);
		display();
//		enQueue(20);
//		display();
//		enQueue(30);
//		display();
//		enQueue(40);
//		display();
		
		deQueue();
		display();
		deQueue();
		display();
		deQueue();
		display();
		deQueue();
		display();
		
	}
	static void enQueue(int value)
	{
		if(front==(rear+1)%SIZE)
		{
			System.out.println("queue is full!");
			return;
		}		
		rear=(rear+1)%SIZE;
		cQueue[rear]=value;
		System.out.println("enqued "+cQueue[rear]);
		if(front==-1)
		{
			front=0;
		}
		
	}
	static void deQueue()
	{
		if(front==-1)
		{
			System.out.println("queue is empty!");
			return;
		}
		
		System.out.println("dequeuing "+cQueue[front]);
		front=(front+1)%SIZE;
		if(front==rear+1 || (front==0 && rear==SIZE-1))
		{
			front=rear=-1;
		}
		
		
	}
	static void display()
	{
		System.out.println("front:"+front+"  rear"+rear);
	   if(front == -1)
		   System.out.println("\nCircular Queue is Empty!!!\n");
	   else{
	      int i = front;
	      System.out.println("\nCircular Queue Elements are : \n");
	      if(front <= rear){
		 while(i <= rear)
			 System.out.println(" "+cQueue[i++]);
	      }
	      else{
		 while(i <= SIZE - 1)
			 System.out.println(" "+cQueue[i++]);
		 i = 0;
		 while(i <= rear)
			 System.out.println(" "+cQueue[i++]);
	      }
	   }
	   
	}
	

}

