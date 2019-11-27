package RedBlackTree;

/**
 * @author paulalan
 * @create 2019/11/27 15:26
 */
public class testRBTree
{
	public static void main(String[] args)
	{
		RBTree rbTree = new RBTree();
//		int[] testArray = {10, 20, -10, 15, 17, 40, 50, 60};
		int[] testArray = {72,0,61,80,77,82,82,95,30,40,0};
		for (int value : testArray)
		{
			rbTree.insert(value);
		}
		rbTree.inOrder();
		rbTree.miniGap();
		System.out.println(rbTree.getMinimum());
	}
}
