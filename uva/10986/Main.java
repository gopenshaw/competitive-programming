
import java.util.*;

public class Main 
{
	private final Long INFINITY = Long.MAX_VALUE;
	
	public static void main(String[] args)
	{
		Main mySolution = new Main();
		mySolution.begin();
	}
	
	private void begin()
	{
		Scanner conIn = new Scanner(System.in);
		int numberOfTestCases = conIn.nextInt();
		for (int n = 1; n <= numberOfTestCases; n++)
		{
			int numberOfServers = conIn.nextInt();
			Vertex[] serverArray = new Vertex[numberOfServers];
			for (int i = 0; i < serverArray.length; i++)
			{
				serverArray[i] = new Vertex();
			}
			int numberOfConnections = conIn.nextInt();
			int indexOfSource = conIn.nextInt();
			int indexOfTarget = conIn.nextInt();
			
			
			//--Get connections
			for (int i = 0; i < numberOfConnections; i++)
			{
				int source = conIn.nextInt();
				int target = conIn.nextInt();
				int latency = conIn.nextInt();
				Vertex sourceServer = serverArray[source];
				Vertex targetServer = serverArray[target];
				sourceServer.adjacencyList.add(new Edge(targetServer, latency));
				targetServer.adjacencyList.add(new Edge(sourceServer, latency));
			}
			
			//--Set up vertexQueue
			PriorityQueue<Vertex> serverQueue = new PriorityQueue<Vertex>();
			for (Vertex server : serverArray)
			{
				server.estimatedDistance = INFINITY;
			}
			serverArray[indexOfSource].estimatedDistance = 0;
			for (Vertex server : serverArray)
			{
				serverQueue.add(server);
			}
			
			//--Djiksras
			while (!serverQueue.isEmpty())
			{
				Vertex currentServer = serverQueue.poll();
				long toHere = currentServer.estimatedDistance;
				if (toHere == this.INFINITY)
				{
					continue;
				}
				
				for (Edge edge : currentServer.adjacencyList)
				{
					long thisDistance = toHere + edge.distance;
					Vertex target = edge.target;
					if (thisDistance < target.estimatedDistance)
					{
						serverQueue.remove(target);
						target.estimatedDistance = thisDistance;
						serverQueue.add(target);
					}
				}
			}
			long result = serverArray[indexOfTarget].estimatedDistance;
			if (result == this.INFINITY)
			{
				System.out.println("Case #" + n + ": unreachable");
			}
			else
			{
				System.out.println("Case #" + n + ": " + result);
			}
		}
	}
}

class Vertex implements Comparable<Vertex>
{
	public ArrayList<Edge> adjacencyList;
	public long estimatedDistance;
	
	public Vertex()
	{
		this.adjacencyList = new ArrayList<Edge>();
	}
	
	@Override
	public int compareTo(Vertex otherCell) 
	{
		if (this.estimatedDistance > otherCell.estimatedDistance)
		{
			return 1;
		}
		else return -1;
	}
}

class Edge
{
	public int distance;
	public Vertex target;
	
	public Edge(Vertex target, int distance)
	{
		this.distance = distance;
		this.target = target;
	}
}