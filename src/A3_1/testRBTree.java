package A3_1;

import java.util.Scanner;

/**
 * @author paulalan
 * @create 2019/11/27 15:26
 */
public class testRBTree
{
	public static void main(String[] args)
	{
		RBTree rbTree = new RBTree();
		System.out.print("Please input integer, split by ',': ");
		Scanner sc = new Scanner(System.in);
		String[] inputArray=sc.nextLine().split(",");
		int[] testArray = new int[inputArray.length];
		for (int i = 0; i < testArray.length; i++)
		{
			testArray[i]=Integer.parseInt(inputArray[i]);
		}
		for (int value : testArray)
		{
			rbTree.add(value);
		}
		rbTree.inOrder();
		rbTree.miniGap();
		System.out.println("The minimum gap is "+rbTree.getMinimum());
	}

}
