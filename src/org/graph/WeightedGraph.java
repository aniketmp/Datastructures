package org.graph;

import java.util.Iterator;
import java.util.PriorityQueue;

import javax.sound.midi.Soundbank;
class WeightedVertex extends Vertex
{
	public int weight;//This will use in djikstra's algo
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
	
	public Edge(int soruceVertexIndex, int destVertexIndex, int weight) {
		this.soruceVertexIndex=soruceVertexIndex;
		this.destVertexIndex=destVertexIndex;
		this.weight=weight;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (destVertexIndex==((Edge)(obj)).destVertexIndex);
	}
	@Override
	public int compareTo(Edge o) {
		
		return new Integer(weight).compareTo(new Integer(o.weight));
	}
	@Override
	public String toString() {
		return "Edge [soruceVertexIndex=" + WeightedGraph.vertexList[soruceVertexIndex].label+ ", destVertexIndex=" + WeightedGraph.vertexList[destVertexIndex].label + ", weight="
				+ weight + "]";
	}
	
	
}
public class WeightedGraph 
{	
	PriorityQueue<Edge> pQueue=new PriorityQueue<Edge>(); 
	private final int MAX_VERTS = 20;
	static WeightedVertex vertexList[]; // array of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	public WeightedGraph() 
	{
		vertexList = new WeightedVertex[MAX_VERTS];// adjacency matrix
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
	
	private void minimubSpanningAlgo() 
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
		theGraph.minimubSpanningAlgo();
	    
	    
	    
	   
	}


}
