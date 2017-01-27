package org.graph;

import java.util.Iterator;
import java.util.PriorityQueue;

class DirectedWeightedVertex extends Vertex
{
	public int overallWeight;
	public boolean isAddedInTree;
	public DirectedWeightedVertex(char lable) {
		super(lable); 
	}
	
}
class EdgeV2 extends Edge implements Comparable<Edge>
{
	
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (destVertexIndex==((Edge)(obj)).destVertexIndex);
	}
	@Override
	public int compareTo(Edge o) {
		
		return new Integer(weight+DirectedWeightedGraph.vertexList[soruceVertexIndex].overallWeight).compareTo(new Integer(DirectedWeightedGraph.vertexList[o.soruceVertexIndex].overallWeight));
	}
	@Override
	public String toString() {
		return "Edge [soruceVertexIndex=" + WeightedGraph.vertexList[soruceVertexIndex].label+ ", destVertexIndex=" + WeightedGraph.vertexList[destVertexIndex].label + ", weight="
				+ weight + "]";
	}
	
	
}
public class DirectedWeightedGraph 
{	
	PriorityQueue<Edge> pQueue=new PriorityQueue<Edge>(); 
	private final int MAX_VERTS = 20;
	static DirectedWeightedVertex vertexList[]; // array of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	public DirectedWeightedGraph() 
	{
		vertexList = new DirectedWeightedVertex[MAX_VERTS];// adjacency matrix
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
		vertexList[nVerts++] = new DirectedWeightedVertex(lab);
	}
	public void addEdge(int start, int end,int weight)
	{
		adjMat[start][end] = weight;// Since its a weighted as well as directed graph			
	}
	public void displayVertex(int v)
	{		
		System.out.print(vertexList[v].label);	
	}
	// returns an unvisited vertex adjacent to v
	public void putAdjEdgesInPQueue(int soruceIndex)
	{
		
		for(int j=0; j<nVerts; j++)
		{
			Edge edge=new Edge(soruceIndex,j,adjMat[soruceIndex][j]);
			if(adjMat[soruceIndex][j]==0 || vertexList[j].isAddedInTree)
			{
					continue;		
			}
			else
			{
				
				if(pQueue.contains(edge))
				{
					Iterator<Edge> edgesItr=pQueue.iterator();
					boolean replace=false;
					while(edgesItr.hasNext())
					{
						Edge e=edgesItr.next();
						if(e.compareTo(edge) > 0)
						{
							replace=true;
							break;
						}
					}
					if(replace)
					{
						pQueue.remove(edge);//This seems confusing but it will remove previously added edge (comparison is done on equals() basis) and add new edge.
						pQueue.add(edge);
					}
		 
				}
				else
				{
					pQueue.add(edge);
				}
		
			}
		}			
		
	}
	
	private void dijkstraAlgo() 
	{				
		int startVertexPos=0;
		vertexList[startVertexPos].isAddedInTree=true;		
		do
		{
			putAdjEdgesInPQueue(startVertexPos);
			Edge peekEdge=pQueue.remove();
			System.out.println(peekEdge);
			vertexList[peekEdge.destVertexIndex].isAddedInTree=true;
			startVertexPos=peekEdge.destVertexIndex;
		}while(!(pQueue.isEmpty()));
		
	}
		
	public static void main(String[] args) 
	{
		DirectedWeightedGraph theGraph=new DirectedWeightedGraph();
		theGraph.addVertex('A'); // 0 (start for dfs)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addVertex('F'); // 5
		theGraph.addVertex('G'); // 6
		theGraph.addVertex('H'); // 7
		
		theGraph.addEdge(0, 1, 50);// AB
		theGraph.addEdge(0, 3, 80);// AD
		theGraph.addEdge(1, 2, 60);// BC
		theGraph.addEdge(1, 3, 90); // BD
		theGraph.addEdge(4, 1, 50); // BE
		theGraph.addEdge(3, 2, 20); // CD
		theGraph.addEdge(2, 4, 40); // CE		
		theGraph.addEdge(3, 4, 70);// DE		
		
		
		System.out.println("Minimum spanning tree: ");
		theGraph.dijkstraAlgo();
	    
	    
	    
	   
	}


}
