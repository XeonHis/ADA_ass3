package A3_1;

/**
 * @author paulalan
 * @create 2019/11/27 15:25
 */
public class RBTree
{
	/**
	 * tree node
	 */
	private RBTreeNode root;
	/**
	 * color: red
	 */
	private final boolean RED = false;
	/**
	 * color: black
	 */
	private final boolean BLACK = true;
	/**
	 * initial minimum number
	 */
	private int minimum = Integer.MAX_VALUE;


	/**
	 * find whether the node is in the tree
	 * @param key number of node
	 * @return node
	 */
	public RBTreeNode contains(int key)
	{
		RBTreeNode tmp = root;
		while (tmp != null)
		{
			if (tmp.getKey() == key)
				return tmp;
			else if (tmp.getKey() > key)
				tmp = tmp.getLeft();
			else
				tmp = tmp.getRight();
		}
		return null;
	}

	/**
	 * add new tree node, initial color: black
	 * @param key current node's number
	 */
	public void add(int key)
	{
		RBTreeNode node = new RBTreeNode(key);
		if (root == null)
		{
			root = node;
			node.setColor(BLACK);
			return;
		}
		RBTreeNode parent = root;
		RBTreeNode son;
		//determine the position
		if (key <= parent.getKey())
		{
			son = parent.getLeft();
		} else
		{
			son = parent.getRight();
		}
		//find the position
		while (son != null)
		{
			parent = son;
			if (key <= parent.getKey())
			{
				son = parent.getLeft();
			} else
			{
				son = parent.getRight();
			}
		}
		if (key <= parent.getKey())
		{
			parent.setLeft(node);
		} else
		{
			parent.setRight(node);
		}
		node.setParent(parent);

		insertFix(node);
	}

	/**
	 * fix balance
	 * @param node current node
	 */
	private void insertFix(RBTreeNode node)
	{
		RBTreeNode father;
		RBTreeNode grandFather;
		//determine the condition
		while ((father = node.getParent()) != null && father.getColor() == RED)
		{
			grandFather = father.getParent();
			if (grandFather.getLeft() == father)
			{
				//left child
				RBTreeNode uncle = grandFather.getRight();
				if (uncle != null && uncle.getColor() == RED)
				{
					setBlack(father);
					setBlack(uncle);
					setRed(grandFather);
					node = grandFather;
					continue;
				}
				if (node == father.getRight())
				{
					leftRotate(father);
					RBTreeNode tmp = node;
					node = father;
					father = tmp;
				}
				setBlack(father);
				setRed(grandFather);
				rightRotate(grandFather);
			} else
			{
				//right child
				RBTreeNode uncle = grandFather.getLeft();
				if (uncle != null && uncle.getColor() == RED)
				{
					setBlack(father);
					setBlack(uncle);
					setRed(grandFather);
					node = grandFather;
					continue;
				}
				if (node == father.getLeft())
				{
					rightRotate(father);
					RBTreeNode tmp = node;
					node = father;
					father = tmp;
				}
				setBlack(father);
				setRed(grandFather);
				leftRotate(grandFather);
			}
		}
		setBlack(root);
	}


	/**
	 * do left rotation to keep balance
	 * @param node root node of unbalance tree
	 */
	private void leftRotate(RBTreeNode node)
	{
		RBTreeNode right = node.getRight();
		parentOperation(node, right);
		node.setRight(right.getLeft());
		if (right.getLeft() != null)
		{
			right.getLeft().setParent(node);
		}
		right.setLeft(node);
	}

	private void parentOperation(RBTreeNode node, RBTreeNode right)
	{
		RBTreeNode parent = node.getParent();
		if (parent == null)
		{
			root = right;
			right.setParent(null);
		} else
		{
			if (parent.getLeft() != null && parent.getLeft() == node)
			{
				parent.setLeft(right);
			} else
			{
				parent.setRight(right);
			}
			right.setParent(parent);
		}
		node.setParent(right);
	}

	/**
	 * do right rotation to keep balance
	 * @param node root node of unbalance tree
	 */
	private void rightRotate(RBTreeNode node)
	{
		RBTreeNode left = node.getLeft();
		parentOperation(node, left);
		node.setLeft(left.getRight());
		if (left.getRight() != null)
		{
			left.getRight().setParent(node);
		}
		left.setRight(node);
	}

	private void setBlack(RBTreeNode node)
	{
		node.setColor(BLACK);
	}

	private void setRed(RBTreeNode node)
	{
		node.setColor(RED);
	}

	public void inOrder()
	{
		inOrder(root);
	}

	/**
	 * In-order traversal
	 * @param node current node
	 */
	private void inOrder(RBTreeNode node)
	{
		if (node == null)
			return;
		inOrder(node.getLeft());
		System.out.println(node);
		inOrder(node.getRight());
	}

	/**
	 * find the minimum number between two closest number
	 */
	public void miniGap()
	{
		leftOperation(root);
	}

	private void leftOperation(RBTreeNode root)
	{
		if (root.getLeft() != null)
		{
			int pMinusL = root.getKey() - root.getLeft().getKey();
			if (pMinusL < minimum)
			{
				minimum = pMinusL;
//				System.out.println("MiniGap " + root.getKey() + " - " + root.getLeft().getKey() + " = " + pMinusL);
			}
			miniGap(root.getLeft());
		}
		if (root.getRight() != null)
		{
			int pMinusR = root.getRight().getKey() - root.getKey();
			if (pMinusR < minimum)
			{
				minimum = pMinusR;
//				System.out.println("MiniGap " + root.getRight().getKey() + " - " + root.getKey() + " = " + pMinusR);
			}
			miniGap(root.getRight());
		}
	}

	public void miniGap(RBTreeNode node)
	{
		leftOperation(node);
	}

	public int getMinimum()
	{
		return minimum;
	}
}

