package org.graph;

import java.util.LinkedList;
import java.util.Stack;

class Vertex
{
	public char label; // label (e.g. 'A')
	public boolean wasVisited;
	public Vertex(char lable) // constructor
	{
		this.label = lable;
		wasVisited = false;
	}
} // end class Vertex
public class Graph 
{
	Stack<Integer> theStack= new Stack<Integer>();
	LinkedList<Integer> theQueue = new LinkedList<Integer>();

	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // array of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	public Graph() 
	{
		vertexList = new Vertex[MAX_VERTS];// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++)
		{
			for(int k=0; k<MAX_VERTS; k++) // set adjacency matrix to 0
			{
				adjMat[j][k] = 0;
			}				
		}		
	}
	public void addVertex(char lab) // argument is label
	{
		vertexList[nVerts++] = new Vertex(lab);
	}
	public void addEdge(int start, int end)
	{
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	public void displayVertex(int v)
	{		
		System.out.print(vertexList[v].label);	
	}
	// returns an unvisited vertex adjacent to v
	public int getAdjUnvisitedVertex(int v)
	{
		for(int j=0; j<nVerts; j++)
		{
			if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
			{
				return j; // return first such vertex
			}							
		}
		return -1; // no such vertices
		
	}
	public void depthFirstSearch() // depth-first search
	{ // begin at vertex 0
		vertexList[0].wasVisited = true; // mark it
		displayVertex(0); // display it
		theStack.push(0); // push it
		while( !theStack.isEmpty() ) // until stack empty,
		{
		// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex( theStack.peek() );
			if(v == -1) // if no such vertex,
			{
				theStack.pop(); // pop a new one
			}
			else // if it exists,
			{	
				vertexList[v].wasVisited = true; // mark it
				displayVertex(v); // display it
				theStack.push(v); // push it
			}
		} // end while
		// stack is empty, so we're done
		for(int j=0; j<nVerts; j++) // reset flags
		{
			vertexList[j].wasVisited = false;
		}
	
	} // end dfs
	public void breadthFirstSearch() // depth-first search
	{ 
		vertexList[0].wasVisited=true;
		displayVertex(0);
		theQueue.add(0);		
		while(!theQueue.isEmpty())
		{
			int v=getAdjUnvisitedVertex( theQueue.peek() );
			if(v == -1) // if no such vertex,
			{
				theQueue.remove(); // pop a new one
			}
			else // if it exists,
			{	
				vertexList[v].wasVisited = true; // mark it
				displayVertex(v); // display it
				theQueue.add(v); // push it				
			}
		}
		// queue is empty, so we're done
		for(int j=0; j<nVerts; j++) // reset flags
		{
			vertexList[j].wasVisited = false;
		}
		
	} 
	public static void main(String[] args) 
	{
		Graph theGraph=new Graph();
		theGraph.addVertex('A'); // 0 (start for dfs)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		
		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(3, 4); // DE
		
		System.out.println("Printint via depthFirstSearch algo..");
	    theGraph.depthFirstSearch();
	    System.out.println("\nPrintint via breadthFirstSearch algo..");
		theGraph.breadthFirstSearch();
	}

} // end class Graph
