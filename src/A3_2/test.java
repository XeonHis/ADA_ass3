package A3_2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author paulalan
 * @create 2019/11/30 15:26
 */
public class test
{
	public static void main(String[] args)
	{
		GraphADT graphADT = new GraphADT();
		graphADT.addVertex('A');
		graphADT.addVertex('B');
		graphADT.addVertex('C');
		graphADT.addVertex('D');
		graphADT.addVertex('E');
		graphADT.addVertex('F');
		graphADT.addVertex('G');
		graphADT.addVertex('H');
		graphADT.addVertex('I');
		graphADT.addVertex('J');
		graphADT.addVertex('K');
		graphADT.addVertex('L');
		graphADT.addVertex('M');

		graphADT.addEdge(0, 1);//AB
		graphADT.addEdge(1, 2);//BC
		graphADT.addEdge(2, 3);//CD
		graphADT.addEdge(1, 3);//BD
		graphADT.addEdge(2, 4);//CE
		graphADT.addEdge(4,5);//EF
		graphADT.addEdge(5,6);//FG
		graphADT.addEdge(4,6);//EG
		graphADT.addEdge(4,7);//EH
		graphADT.addEdge(7,8);//HI
		graphADT.addEdge(4,9);//EJ
		graphADT.addEdge(9,10);//JK
		graphADT.addEdge(9,11);//JL
		graphADT.addEdge(9,12);//JM
		graphADT.addEdge(10,11);//KL
		graphADT.addEdge(11,12);//LM


		ArrayList<Integer> dfslist = graphADT.calculateDepths('A');
		System.out.println("d(v) as follow:");
		Util.formatOutput(dfslist);
		ArrayList<Integer> miniList = graphADT.miniVCalculate();
		System.out.println("\n----------------------------------------\nm(v) as follow:");
		Util.formatOutput(miniList);
		graphADT.checkConnection();
		graphADT.analyzeGraph('A');
		System.out.println("\nArticulation point number is "+graphADT.getaPoint().size());
		System.out.println("Bridge number is "+graphADT.getBridgeCount());
	}
}
