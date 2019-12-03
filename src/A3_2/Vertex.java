package A3_2;

/**
 * @author paulalan
 * @create 2019/11/30 14:44
 */
public class Vertex
{
	private char label;
	private boolean isVisited;

	public Vertex(char label)
	{
		this.label = label;
		isVisited = false;
	}

	public void setVisited(boolean visited)
	{
		isVisited = visited;
	}

	public char getLabel()
	{
		return label;
	}

	public boolean isVisited()
	{
		return isVisited;
	}
}
