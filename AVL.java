public class AVL<X extends Comparable<X>>
{
	Node root;
	class Node
	{
		X data;
		Node left, right, parent;
		int height;
		
		public Node(X d)
		{
			data = d;
			left = right = parent = null;
			height = 0;
		}
	}
	
	public void setRoot(Node n)
	{
		root = n;
	}
	
	public AVL subtree(Node n)
	{
		AVL<X> tree = new AVL<X>();
		tree.setRoot(n);
		return tree;
	}
	
	public Node find(X d)
	{
		if(root == null) return null;
		if(root.data.compareTo(d) == 0)
			return root;
		if(root.data.compareTo(d) < 0)
			return subtree(root.right).find(d);
		return subtree(root.left).find(d);		
	}
}
