package org.graph;

import java.util.Iterator;
import java.util.PriorityQueue;

class DVEdge extends Edge
{

	public DVEdge(int soruceIndex, int j, int i) {
		super(soruceIndex,j,i);
	}

	@Override
	public String toString() {
		return "Edge [soruceVertexIndex=" + DirectedWeightedGraph.vertexList[soruceVertexIndex].label+ ", destVertexIndex=" + DirectedWeightedGraph.vertexList[destVertexIndex].label + ", weight="
				+ weight + "]";
	}
}
public class DirectedWeightedGraph 
{	
	PriorityQueue<DVEdge> pQueue=new PriorityQueue<DVEdge>(); 
	private final int MAX_VERTS = 20;
	static WeightedVertex vertexList[]; // array of vertices
	private int adjMat[][]; // adjacency matrix
	private int nVerts; // current number of vertices
	public DirectedWeightedGraph() 
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
		adjMat[start][end] = weight;//Since its a weighted and directed graph			
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
			int previousWeight=vertexList[soruceIndex].weight;
			int currentWeight=adjMat[soruceIndex][j];
			DVEdge edge=new DVEdge(soruceIndex,j,currentWeight+previousWeight);
			if(adjMat[soruceIndex][j]==0 || vertexList[j].isAddedInTree)
			{
					continue;		
			}
			else
			{
				
				if(pQueue.contains(edge))
				{
					Iterator<DVEdge> edgesItr=pQueue.iterator();
					boolean replace=false;
					while(edgesItr.hasNext())
					{
						DVEdge e=edgesItr.next();
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
		vertexList[startVertexPos].weight=0;
		do
		{
			putAdjEdgesInPQueue(startVertexPos);
			Edge peekEdge=pQueue.remove();
			System.out.println(peekEdge);
			vertexList[peekEdge.destVertexIndex].isAddedInTree=true;
			vertexList[peekEdge.destVertexIndex].weight=peekEdge.weight; //also need to update weight so that next time we can add the previous weight.
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
		
		
		System.out.println("Djikstra's Algo: ");
		theGraph.dijkstraAlgo();
	    
	    
	    
	   
	}


}
