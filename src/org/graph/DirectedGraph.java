package org.graph;

import java.util.LinkedList;
import java.util.Stack;


public class DirectedGraph 
{
	Stack<Integer> theStack= new Stack<Integer>();
	LinkedList<Integer> theQueue = new LinkedList<Integer>();

	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // array of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	private int originalVerts; // current number of vertices
	public DirectedGraph() 
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
		adjMat[start][end] = 1;//Since its a directed graph , it has only one way edges.		
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
	public void topologicalSort() // depth-first search
	{ // begin at vertex 0

		originalVerts=nVerts; // current number of ertices
		while(nVerts > 0) // until stack empty,
		{
			int v=getNoSuccessorVertex();
			if(v == -1) // if no such vertex,
			{
				System.out.println("Graph has a cycle..");
			}
			else // if it exists,
			{	
				vertexList[v].wasVisited = true; // mark it
				displayVertex(v); // display it
				deleteVertex(v); // delete it				
			}
		} // end while
		
		for(int j=0; j<nVerts; j++) // reset flags
		{
			vertexList[j].wasVisited = false;
		}
	
	}
	private void deleteVertex(int v) 
	{
		
		if(v!=nVerts-1)//That means this is not the last vertex. Hence some arrangement is needed
		{
			Vertex temp=vertexList[v];
			vertexList[v]=vertexList[nVerts-1];
			vertexList[nVerts-1]=temp;
			for(int i=0;i<nVerts;i++)
			{
				adjMat[v][i]=adjMat[nVerts-1][i];
				adjMat[nVerts-1][i]=0;
			}
			for(int i=0;i<nVerts;i++)
			{
				adjMat[i][v]=adjMat[i][nVerts-1];
				adjMat[i][nVerts-1]=0;
			}
			
		}
		nVerts--;
		
	}
	private int getNoSuccessorVertex() 
	{
		
		for(int i=0;i<nVerts;i++)
		{
			boolean hasSuccessor=false;
			for(int j=0;j<nVerts;j++)
			{				
				if(adjMat[i][j]==1)
				{
					hasSuccessor=true;
					break;
				}				
			}
			if(!hasSuccessor)
			{
				return i;
			}
		}
		return -1;
	}
	private void displayOrderWise() 
	{
		
		for(int i=0;i<originalVerts;i++)
		{
			displayVertex(i);
		}
	}
	
	public static void main(String[] args) 
	{
		DirectedGraph theGraph=new DirectedGraph();
		theGraph.addVertex('A'); // 0 (start for dfs)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addVertex('F'); // 5
		theGraph.addVertex('G'); // 6
		theGraph.addVertex('H'); // 7
		
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(1, 4); // BE
		theGraph.addEdge(3, 6); // DG
		theGraph.addEdge(4, 6); // EG
		theGraph.addEdge(6, 7); // GH
		theGraph.addEdge(2, 5); // CF
		theGraph.addEdge(5, 7); // FH
		
		
		System.out.println("Sorting through topological sort algo..");
	    theGraph.topologicalSort();
	    System.out.println("\nDisplaying edges..");
	    theGraph.displayOrderWise();
	   
	}
	

} // end class Graph
