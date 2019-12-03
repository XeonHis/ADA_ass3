package A3_2;

/**
 * @author paulalan
 * @create 2019/11/30 15:26
 */
public class test
{
	public static void main(String[] args)
	{
		Graph graph = new Graph();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		graph.addVertex('G');
		graph.addVertex('H');
		graph.addVertex('I');
		graph.addVertex('J');
		graph.addVertex('K');
		graph.addVertex('L');
		graph.addVertex('M');

		graph.addEdge(0, 1);//AB
		graph.addEdge(1, 2);//BC
		graph.addEdge(2, 3);//CD
		graph.addEdge(1, 3);//BD
		graph.addEdge(2, 4);//CE
		graph.addEdge(4,5);//EF
		graph.addEdge(5,6);//FG
		graph.addEdge(4,6);//EG
		graph.addEdge(4,7);//EH
		graph.addEdge(7,8);//HI
		graph.addEdge(4,9);//EJ
		graph.addEdge(9,10);//JK
		graph.addEdge(9,11);//JL
		graph.addEdge(9,12);//JM
		graph.addEdge(10,11);//KL
		graph.addEdge(11,12);//LM

//		graph.addVertex('A');
//		graph.addVertex('B');
//		graph.addVertex('C');
//		graph.addVertex('D');
//		graph.addVertex('E');
//		graph.addVertex('F');
//		graph.addVertex('G');
//		graph.addVertex('H');
//		graph.addVertex('S');
//
//		graph.addEdge(0, 1);//AB
//		graph.addEdge(0, 8);//AS
//		graph.addEdge(8, 2);//SC
//		graph.addEdge(8, 6);//SG
//		graph.addEdge(2, 3);//CD
//		graph.addEdge(2, 4);//CE
//		graph.addEdge(2, 5);//CF
//		graph.addEdge(5, 6);//GF
//		graph.addEdge(6, 7);//GH
//		graph.addEdge(7, 4);//HE


		graph.DFS();
		graph.miniVCalculate();

	}
}
