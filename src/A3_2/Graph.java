package A3_2;

import java.util.*;

/**
 * @author paulalan
 * @create 2019/11/27 18:42
 */
public class Graph
{
	private final int MAX_VERTEX = 20;
	private Vertex[] vertexList;
	private int[][] adjacentMatrix;
	private int vertexNum;
	private Stack stack;
	private int countNum = 0;
	private HashMap<Vertex, Integer> displayMap = new HashMap<>();
	private HashMap<Vertex, Integer> minimumMap = new HashMap<>();

	public Graph()
	{
		vertexList = new Vertex[MAX_VERTEX];
		adjacentMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		vertexNum = 0;
		for (int i = 0; i < adjacentMatrix.length; i++)
		{
			Arrays.fill(adjacentMatrix[i], 0);
		}
		stack = new Stack();
	}

	public void addVertex(char c)
	{
		vertexList[vertexNum++] = new Vertex(c);
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
//		System.out.print(vertexList[vertex].getLabel()+" ");
	}

	public void DFS()
	{
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

		for (int i = 0; i < vertexNum; i++)
		{
			vertexList[i].setVisited(false);
		}

		System.out.println("Original " + displayMap);
		minimumMap = displayMap;
		Collection<Integer> values = displayMap.values();
		ArrayList<Integer> temp = new ArrayList<>(values);
		Collections.sort(temp);
		for (int i = 0; i < temp.size(); i++)
		{
			System.out.printf("%4s", Util.getKey(displayMap, temp.get(i)).getLabel());
		}
		System.out.println();
		for (int i = 0; i < temp.size(); i++)
		{
			System.out.printf("%4s", temp.get(i));
		}
		System.out.println();

	}


	public int getAdjUnvisitedVertex(int v)
	{
		for (int i = 0; i < vertexNum; i++)
		{
			if (adjacentMatrix[v][i] == 1 && !vertexList[i].isVisited())
			{
				adjacentMatrix[i][v] = 0;
//				adjacentMatrix[v][i] = 0;
				return i;
			}
		}
		return -1;
	}

	public void miniVCalculate()
	{
		System.out.println("----------------------------------------");
		Collection<Integer> values = minimumMap.values();
		ArrayList<Integer> temp = new ArrayList<>(values);
		Collections.sort(temp);
		System.out.println(temp);
		for (int i = 0; i < adjacentMatrix.length; i++)
		{
			System.out.println(i + " " + Arrays.toString(adjacentMatrix[i]));
		}
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

//			System.out.println();
		}
		System.out.println("----------------------------------------");
		System.out.println(temp);
//		System.out.println(minimumMap.values());
	}

}
