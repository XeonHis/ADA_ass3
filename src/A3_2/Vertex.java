package A3_2;

/**
 * @author paulalan
 * @create 2019/11/30 14:44
 */
public class Vertex<E>
{
	private E label;
	private boolean isVisited;
	private Vertex areParent;

	public Vertex getAreParent()
	{
		return areParent;
	}

	public void setAreParent(Vertex areParent)
	{
		this.areParent = areParent;
	}

	public Vertex(E label)
	{
		this.label = label;
		this.isVisited = false;
	}

	public void setVisited(boolean visited)
	{
		isVisited = visited;
	}

	public E getLabel()
	{
		return label;
	}

	public boolean isVisited()
	{
		return isVisited;
	}
}
