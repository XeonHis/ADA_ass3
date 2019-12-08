package A3_2;

import java.util.Vector;

/**
 * @author paulalan
 * @create 2019/11/30 14:51
 */
public class Stack
{
	private Vector<Integer> vector = new Vector<>();
	private int top;

	public Stack()
	{
		top = -1;
	}

	public void push(int j)
	{
		vector.add(++top, j);
	}

	public void pop()
	{
		top--;
	}

	public int peek()
	{
		return vector.get(top);
	}

	public boolean isEmpty()
	{
		return (top == -1);
	}

}
