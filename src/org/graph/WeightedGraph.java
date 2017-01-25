package org.graph;

import java.util.Iterator;
import java.util.PriorityQueue;

import javax.sound.midi.Soundbank;
class WeightedVertex extends Vertex
{
	public boolean isAddedInTree;
	public WeightedVertex(char lable) {
		super(lable);
		// TODO Auto-generated constructor stub
	}
	
}
class Edge implements Comparable<Edge>
{
	public int weight;
	int soruceVertexIndex;
	int destVertexIndex;
	
	public Edge(int soruceVertexIndex, int destVertexIndex) {
		this.soruceVertexIndex=soruceVertexIndex;
		this.destVertexIndex=destVertexIndex;
	}
	public Edge()
	{
		
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (soruceVertexIndex==((Edge)(obj)).soruceVertexIndex && destVertexIndex==((Edge)(obj)).destVertexIndex);
	}
	@Override
	public int compareTo(Edge o) {
		
		return new Integer(weight).compareTo(new Integer(o.weight));
	}
	
	
}
public class WeightedGraph 
{	
	PriorityQueue<Edge> pQueue=new PriorityQueue<Edge>(); 
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // array of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	public WeightedGraph() 
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
		vertexList[nVerts++] = new WeightedVertex(lab);
	}
	public void addEdge(int start, int end,int weight)
	{
		adjMat[start][end] = weight;
		adjMat[end][start] = weight; //Since its a weighted non-directed graph		
	}
	public void displayVertex(int v)
	{		
		System.out.print(vertexList[v].label);	
	}
	// returns an unvisited vertex adjacent to v
	public Edge getAdjEdges(int soruceIndex)
	{
		for(int j=0; j<nVerts; j++)
		{
			Edge edge;
			if(adjMat[soruceIndex][j]!=0 )
			{
				edge=new Edge(soruceIndex,j);
				if(pQueue.contains(edge))
				{
					edge.weight=adjMat[soruceIndex][j];
					return edge; 
				}
					
			}							
		}
		return null; // no such vertices
		
	}
	
	private void dijkstraAlgo() 
	{				
		Edge e=getAdjEdges(0);
		if(e==null)
		{
			
		}
		else
		{
			if(pQueue.contains(e))
			{
				Iterator< Edge> etr=pQueue.iterator();
				while(etr.hasNext())
				{
					Edge elem=etr.next();
					if(elem.equals(e))
					{
						if(e.weight< elem.weight)
						{
							pQueue.remove(elem);
							pQueue.add(e);
							break;
						}							
						else
						{
							break;
						}
					}
				}
			}
		}
		
	}
	
	private int getVertexWeight(int row,int col) {
		// TODO Auto-generated method stub
		return adjMat[row][col];
	}
	public static void main(String[] args) 
	{
		WeightedGraph theGraph=new WeightedGraph();
		theGraph.addVertex('A'); // 0 (start for dfs)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addVertex('F'); // 5
		theGraph.addVertex('G'); // 6
		theGraph.addVertex('H'); // 7
		
		theGraph.addEdge(0, 1, 6); // AB 6
		theGraph.addEdge(0, 3, 4); // AD 4
		theGraph.addEdge(1, 2, 10); // BC 10
		theGraph.addEdge(1, 3, 7); // BD 7
		theGraph.addEdge(1, 4, 7); // BE 7
		theGraph.addEdge(2, 3, 8); // CD 8
		theGraph.addEdge(2, 4, 5); // CE 5
		theGraph.addEdge(2, 5, 6); // CF 6
		theGraph.addEdge(3, 4, 12); // DE 12
		theGraph.addEdge(4, 5, 7); // EF 7
		
		
		System.out.println("Minimum spanning tree: ");
		theGraph.dijkstraAlgo();
	    
	    
	    
	   
	}


}
