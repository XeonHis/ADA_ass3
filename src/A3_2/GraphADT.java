package A3_2;


import java.util.*;

/**
 * @author paulalan
 * @create 2019/11/27 18:42
 */
public class GraphADT implements Cloneable
{
	/**
	 * limitation of vertex's number
	 */
	private final int MAX_VERTEX = 15;
	/**
	 * vertex list
	 */
	private Vertex[] vertexList;
	/**
	 * adjacent matrix(will be modified)
	 */
	private int[][] adjacentMatrix;
	/**
	 * adjacent matrix(fixed)
	 */
	private int[][] staticMatrix;
	/**
	 * number of added vertex
	 */
	private int vertexNum;
	/**
	 * stack to dfs
	 */
	private Stack stack;
	/**
	 * add sequence of stack
	 */
	private int countNum = 0;
	/**
	 * map contains vertex and visited sequence
	 */
	private HashMap<Vertex, Integer> displayMap = new HashMap<>();
	private HashMap<Vertex, Integer> minimumMap = new HashMap<>();
	/**
	 * present the sequence the vertex has been visited
	 */
	int[] dfn = new int[MAX_VERTEX];
	/**
	 * the minimum number which goes through non-negative sub-edge
	 */
	int[] low = new int[MAX_VERTEX];
	/**
	 * input count in dfn and low list
	 */
	static int count = 0;
	/**
	 * store articulation point
	 */
	HashSet<Character> aPoint = new HashSet<>();
	/**
	 * the number of bridge
	 */
	int bridgeCount = 0;


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

	/**
	 * add vertex into vertex list
	 *
	 * @param c label of vertex
	 */
	public void addVertex(char c)
	{
		vertexList[vertexNum++] = new Vertex<>(c);
	}

	/**
	 * add edge into adjacent matrix
	 *
	 * @param row start or end
	 * @param col end or start
	 */
	public void addEdge(int row, int col)
	{
		adjacentMatrix[row][col] = 1;
		adjacentMatrix[col][row] = 1;
	}

	/**
	 * add vertex into map
	 *
	 * @param vertex vertex object
	 */
	public void addIntoMap(int vertex)
	{
		displayMap.put(vertexList[vertex], countNum);
		countNum++;
	}

	private int findVertex(char label)
	{
		for (int i = 0; i < vertexList.length; i++)
		{
			if (vertexList[i].getLabel().equals(label))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * use dfs to calculate the depths
	 *
	 * @return arraylist of visiting sequence
	 */
	public ArrayList<Integer> calculateDepths(char startVertexLabel)
	{
		for (int i = 0; i < adjacentMatrix.length; i++)
		{
			staticMatrix[i] = adjacentMatrix[i].clone();
		}
		int currentPosition = findVertex(startVertexLabel);
		vertexList[currentPosition].setVisited(true);
		addIntoMap(currentPosition);
		stack.push(currentPosition);

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
		Util.outputSequences = new char[temp.size()];
		for (int i = 0; i < temp.size(); i++)
		{
			Util.outputSequences[i] = (char) Util.getKey(displayMap, temp.get(i)).getLabel();
		}
		return temp;
	}

	/**
	 * check whether the graph is connected
	 */
	public void checkConnection()
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
		for (Vertex vertex : vertexList)
		{
			if (vertex != null)
			{
				vertex.setVisited(false);
			}
		}
	}


	/**
	 * find the position of given vertex in adjacent matrix
	 *
	 * @param v number of vertex in vertex list
	 * @return position
	 */
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

	/**
	 * calculate the m(v)
	 *
	 * @return arraylist of m(v)
	 */
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
		return temp;
	}



	/**
	 * analyze whether the graph is connected
	 * @param startVertex start vertex
	 */
	public void analyzeGraph(char startVertex)
	{
		Vertex currentVertex = vertexList[findVertex(startVertex)];
		int u = startVertex - 65;
		int children = 0;
		dfn[u] = low[u] = ++count;
		currentVertex.setVisited(true);

		ArrayList<Vertex> adjacentVertex = new ArrayList<>();
		for (int i = 0; i < staticMatrix[u].length; i++)
		{
			if (staticMatrix[u][i] == 1)
			{
				adjacentVertex.add(vertexList[findVertex((char) (i + 65))]);
			}
		}
		for (Vertex v : adjacentVertex)
		{
			if (!v.isVisited())
			{
				children++;
				v.setAreParent(currentVertex);
				analyzeGraph((char) v.getLabel());
				if (low[u] == low[(char) v.getLabel() - 65])
				{
					bridgeCount++;
				}
				low[u] = Math.min(low[u], low[(char) v.getLabel() - 65]);

				if (currentVertex.getAreParent() != null && children >= 2
						|| currentVertex.getAreParent() != null && low[(char) v.getLabel() - 65] >= dfn[u])
				{
					aPoint.add((char) (u + 65));
					System.out.println("Articulation point: " + (char) (u + 65));
				}
			} else if (v != currentVertex.getAreParent())
			{
				low[u] = Math.min(low[u], dfn[(char) v.getLabel() - 65]);
			}
		}
	}


	public int getBridgeCount()
	{
		return bridgeCount;
	}

	public HashSet<Character> getaPoint()
	{
		return aPoint;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
