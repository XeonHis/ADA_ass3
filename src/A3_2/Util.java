package A3_2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author paulalan
 * @create 2019/11/30 17:03
 */
public class Util
{
	static char[] outputSequences;
	static Vertex getKey(HashMap<Vertex, Integer> map, int value)
	{
		Vertex key = null;
		for (Vertex getKey : map.keySet())
		{
			if (map.get(getKey) == value)
			{
				key = getKey;
			}
		}
		return key;
	}
	/**
	 * format output
	 *
	 * @param temp input arraylist
	 */
	static void formatOutput(ArrayList<Integer> temp)
	{

		for (int i = 0; i < temp.size(); i++)
		{
			System.out.printf("%4s", outputSequences[i]);
		}
		System.out.println();
		for (int i = 0; i < temp.size(); i++)
		{
			System.out.printf("%4s", temp.get(i));
		}
	}

}