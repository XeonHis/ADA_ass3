package A3_2;

import java.util.Vector;

/**
 * @author paulalan
 * @create 2019/11/30 14:51
 */
public class Stack
{
	private Vector size = new Vector();
	private int[] stack;
	private int top;

	public Stack()
	{
		stack = new int[size.capacity()];
		top = -1;
	}

	public void push(int j)
	{
		stack[++top] = j;
	}

	public void pop()
	{
		top--;
	}

	public int peek()
	{
		return stack[top];
	}

	public boolean isEmpty()
	{
		return (top == -1);
	}

}
