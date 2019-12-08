package A3_2;

import java.util.*;

/**
 * @author paulalan
 * @create 2019/11/27 18:42
 */
public class GraphADT implements Cloneable
{
	private final int MAX_VERTEX = 15;
	private Vertex[] vertexList;
	private int[][] adjacentMatrix;
	private int[][] staticMatrix;
	private int vertexNum;
	private Stack stack;
	private int countNum = 0;
	private HashMap<Vertex, Integer> displayMap = new HashMap<>();
	private HashMap<Vertex, Integer> minimumMap = new HashMap<>();

	public GraphADT()
	{
		vertexList = new Vertex[MAX_VERTEX];
		adjacentMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		staticMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		vertexNum = 0;
		for (int[] matrix : adjacentMatrix)
		{
			Arrays.fill(matrix, 0);
		}
		stack = new Stack();
	}

	public void addVertex(char c)
	{
		vertexList[vertexNum++] = new Vertex<>(c);
	}

	public void addEdge(int row, int col)
	{
		adjacentMatrix[row][col] = 1;
		adjacentMatrix[col][row] = 1;
	}

	public void addIntoMap(int vertex)
	{
		displayMap.put(vertexList[vertex], countNum);
		countNum++;
	}

	public int[][] getAdjacentMatrix()
	{
		return adjacentMatrix;
	}

	public ArrayList<Integer> DFS()
	{
		for (int i = 0; i < adjacentMatrix.length; i++)
		{
			staticMatrix[i] = adjacentMatrix[i].clone();
		}
		vertexList[0].setVisited(true);
		addIntoMap(0);
		stack.push(0);

		while (!stack.isEmpty())
		{
			int v = getAdjUnvisitedVertex(stack.peek());
			if (v == -1)
			{
				stack.pop();
			} else
			{
				vertexList[v].setVisited(true);
				addIntoMap(v);
				stack.push(v);
			}
		}


		minimumMap = displayMap;
		Collection<Integer> values = displayMap.values();
		ArrayList<Integer> temp = new ArrayList<>(values);
		Collections.sort(temp);
		System.out.println("d(v) as follow:");
		formatOutput(temp);
		return temp;
	}

	private void checkConnection()
	{
		for (int i = 0; i < vertexNum; i++)
		{
			if (!vertexList[i].isVisited())
			{
				System.out.println("\nUnconnected Graph!");
				System.exit(0);
			}
		}
		System.out.println("\nConnected Graph");
	}

	private void formatOutput(ArrayList<Integer> temp)
	{
		for (int i = 0; i < temp.size(); i++)
		{
			System.out.printf("%4s", Character.toString(65 + i));
		}
		System.out.println();
		for (int i = 0; i < temp.size(); i++)
		{
			System.out.printf("%4s", temp.get(i));
		}
	}


	public int getAdjUnvisitedVertex(int v)
	{
		for (int i = 0; i < vertexNum; i++)
		{
			if (adjacentMatrix[v][i] == 1 && !vertexList[i].isVisited())
			{
				adjacentMatrix[i][v] = 0;
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Integer> miniVCalculate()
	{
		Collection<Integer> values = minimumMap.values();
		ArrayList<Integer> temp = new ArrayList<>(values);
		Collections.sort(temp);
		for (int i = temp.size() - 1; i >= 0; i--)
		{
			int tempMin = Integer.MAX_VALUE;
			for (int j = temp.size() - 1; j >= 0; j--)
			{
				if (adjacentMatrix[i][j] == 1)
				{
					if (tempMin > temp.get(j))
					{
						tempMin = temp.get(j);
					}
				}
			}
			if (temp.get(i) > tempMin)
			{
				temp.set(i, tempMin);
			}

		}
		System.out.println("\n----------------------------------------");
		System.out.println("m(v) as follow:");
		formatOutput(temp);
		return temp;
	}

	public void analyzeGraph(ArrayList<Integer> dfsList, ArrayList<Integer> miniList)
	{
		int artPoint = 0;
		int bridge = 0;
		int rootNodeCount = rootNodeCount();
		checkConnection();
		for (int i = 0; i < dfsList.size(); i++)
		{
			if (miniList.get(i) >= dfsList.get(i))
			{
				artPoint++;
			}
		}
		artPoint -= rootNodeCount;
		if (artPoint > 1)
		{
			bridge = artPoint - 1;
		}
		System.out.println("\nArticulation point: " + artPoint);
		System.out.println("Bridge: " + bridge);
	}

	/**
	 * Calculate the one-side tree node
	 * @return count of node
	 */
	private int rootNodeCount()
	{
		int rootCount = 0;
		for (int i = 0; i < countNum; i++)
		{
			int innerCount = 0;
			for (int j = 0; j < countNum; j++)
			{
				if (staticMatrix[i][j] == 1)
				{
					innerCount++;
				}
			}
			if (innerCount < 2)
			{
				rootCount++;
			}
		}
		return rootCount;
	}


	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
