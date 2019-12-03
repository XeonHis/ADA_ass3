package A3_2;

import java.util.HashMap;

/**
 * @author paulalan
 * @create 2019/11/30 17:03
 */
public class Util
{
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
}
