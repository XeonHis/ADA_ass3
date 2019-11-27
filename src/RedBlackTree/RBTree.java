package RedBlackTree;

/**
 * @author paulalan
 * @create 2019/11/27 15:25
 */
public class RBTree
{
	private RBTreeNode root;
	private final boolean RED = false;
	private final boolean BLACK = true;
	private int minimum = Integer.MAX_VALUE;

	public RBTreeNode query(int key)
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

	public void insert(int key)
	{
		RBTreeNode node = new RBTreeNode(key);
		if (root == null)
		{
			root = node;
			node.setColor(BLACK);
			return;
		}
		RBTreeNode parent = root;
		RBTreeNode son = null;
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

		//fix up
		insertFix(node);
	}

	private void insertFix(RBTreeNode node)
	{
		RBTreeNode father, grandFather;
		while ((father = node.getParent()) != null && father.getColor() == RED)
		{
			grandFather = father.getParent();
			if (grandFather.getLeft() == father)
			{
				//F is left child of G
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
				//F is right child of G
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

	public void delete(int key)
	{
		delete(query(key));
	}

	private void delete(RBTreeNode node)
	{
		if (node == null)
			return;
		if (node.getLeft() != null && node.getRight() != null)
		{
			RBTreeNode replaceNode = node;
			RBTreeNode tmp = node.getRight();
			while (tmp != null)
			{
				replaceNode = tmp;
				tmp = tmp.getLeft();
			}
			int t = replaceNode.getKey();
			replaceNode.setKey(node.getKey());
			node.setKey(t);
			delete(replaceNode);
			return;
		}
		RBTreeNode replaceNode = null;
		if (node.getLeft() != null)
			replaceNode = node.getLeft();
		else
			replaceNode = node.getRight();

		RBTreeNode parent = node.getParent();
		if (parent == null)
		{
			root = replaceNode;
			if (replaceNode != null)
				replaceNode.setParent(null);
		} else
		{
			if (replaceNode != null)
				replaceNode.setParent(parent);
			if (parent.getLeft() == node)
				parent.setLeft(replaceNode);
			else
			{
				parent.setRight(replaceNode);
			}
		}
		if (node.getColor() == BLACK)
			removeFix(parent, replaceNode);

	}


	private void removeFix(RBTreeNode father, RBTreeNode node)
	{
		while ((node == null || node.getColor() == BLACK) && node != root)
		{
			if (father.getLeft() == node)
			{
				//S is left child of P
				RBTreeNode brother = father.getRight();
				if (brother != null && brother.getColor() == RED)
				{
					setRed(father);
					setBlack(brother);
					leftRotate(father);
					brother = father.getRight();
				}
				if (brother == null || (isBlack(brother.getLeft()) && isBlack(brother.getRight())))
				{
					setRed(brother);
					node = father;
					father = node.getParent();
					continue;
				}
				if (isRed(brother.getLeft()))
				{
					setBlack(brother.getLeft());
					setRed(brother);
					rightRotate(brother);
					brother = brother.getParent();
				}

				brother.setColor(father.getColor());
				setBlack(father);
				setBlack(brother.getRight());
				leftRotate(father);
				node = root;
			} else
			{
				//S is right child of P
				RBTreeNode brother = father.getLeft();
				if (brother != null && brother.getColor() == RED)
				{
					setRed(father);
					setBlack(brother);
					rightRotate(father);
					brother = father.getLeft();
				}
				if (brother == null || (isBlack(brother.getLeft()) && isBlack(brother.getRight())))
				{
					setRed(brother);
					node = father;
					father = node.getParent();
					continue;
				}
				if (isRed(brother.getRight()))
				{
					setBlack(brother.getRight());
					setRed(brother);
					leftRotate(brother);
					brother = brother.getParent();
				}

				brother.setColor(father.getColor());
				setBlack(father);
				setBlack(brother.getLeft());
				rightRotate(father);
				node = root;
			}
		}

		if (node != null)
			node.setColor(BLACK);
	}

	private boolean isBlack(RBTreeNode node)
	{
		if (node == null)
			return true;
		return node.getColor() == BLACK;
	}

	private boolean isRed(RBTreeNode node)
	{
		if (node == null)
			return false;
		return node.getColor() == RED;
	}

	private void leftRotate(RBTreeNode node)
	{
		RBTreeNode right = node.getRight();
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
		node.setRight(right.getLeft());
		if (right.getLeft() != null)
		{
			right.getLeft().setParent(node);
		}
		right.setLeft(node);
	}

	private void rightRotate(RBTreeNode node)
	{
		RBTreeNode left = node.getLeft();
		RBTreeNode parent = node.getParent();
		if (parent == null)
		{
			root = left;
			left.setParent(null);
		} else
		{
			if (parent.getLeft() != null && parent.getLeft() == node)
			{
				parent.setLeft(left);
			} else
			{
				parent.setRight(left);
			}
			left.setParent(parent);
		}
		node.setParent(left);
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

	private void inOrder(RBTreeNode node)
	{
		if (node == null)
			return;
		inOrder(node.getLeft());
		System.out.println(node);
		inOrder(node.getRight());
	}

	public void miniGap()
	{
		if (root.getLeft() != null)
		{
			int pMinusL = root.getKey() - root.getLeft().getKey();
			if (pMinusL < minimum)
			{
				minimum = pMinusL;
				System.out.println("MiniGap " + root.getKey() + " - " + root.getLeft().getKey() + " = " + pMinusL);
			}
			miniGap(root.getLeft());
		}
		if (root.getRight() != null)
		{
			int pMinusR = root.getRight().getKey() - root.getKey();
			if (pMinusR < minimum)
			{
				minimum = pMinusR;
				System.out.println("MiniGap " + root.getRight().getKey() + " - " + root.getKey() + " = " + pMinusR);
			}
			miniGap(root.getRight());
		}
	}
	public void miniGap(RBTreeNode node)
	{
		if (node.getLeft() != null)
		{
			int pMinusL = node.getKey() - node.getLeft().getKey();
			if (pMinusL < minimum)
			{
				minimum = pMinusL;
				System.out.println("MiniGap " + node.getKey() + " - " + node.getLeft().getKey() + " = " + pMinusL);
			}
			miniGap(node.getLeft());
		}
		if (node.getRight() != null)
		{
			int pMinusR = node.getRight().getKey() - node.getKey();
			if (pMinusR < minimum)
			{
				minimum = pMinusR;
				System.out.println("MiniGap " + node.getRight().getKey() + " - " + node.getKey() + " = " + pMinusR);
			}
			miniGap(node.getRight());
		}
	}

	public int getMinimum()
	{
		return minimum;
	}
}

