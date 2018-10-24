import java.util.*;

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
	
	private Node find(Node r, X d)
	{
		if(r == null) return null;
		if(r.data.compareTo(d) == 0)
			return r;
		if(r.data.compareTo(d) < 0)
			return find(r.right, d);
		return find(r.left, d);		
	}

	public boolean isMember(X d)
	{
		return (find(root, d) != null);
	}

	private Node bstInsert(Node r, Node d)
	{
		if(r == null) return d;
		if(d.data.compareTo(r.data) <= 0)
		{
			Node tmp = bstInsert(r.left, d);
			tmp.parent = r;
			r.left = tmp;
		}
		else
		{
			Node tmp = bstInsert(r.right, d);
			tmp.parent = r;
			r.right = tmp;
		}
		return r;
	}

	private int newHeight(Node n)
	{
		if(n == null) return -1;
		int l,r;
		if(n.left != null) l = n.left.height;
		else l = -1;
		if(n.right != null) r = n.right.height;
		else r = -1;
		return 1 + Math.max(l, r);
	}

	private boolean isImbalanced(Node n)
	{
		if(n == null) return false;
		int l,r;
		if(n.left != null) l = n.left.height;
		else l = -1;
		if(n.right != null) r = n.right.height;
		else r = -1;
		return (Math.abs(l-r) > 1);
	}

	private Node higherChild(Node n)
	{
		if(n == null) return null;
		if(n.right == null && n.left == null) return null;
		if(n.right == null) return n.left;
		if(n.left == null) return n.right;
		if(n.left.height >= n.right.height) return n.left;
		return n.right;
	}

	public void Insert(X d)
	{
		Node n = new Node(d);
		root = bstInsert(root, n);
		n = n.parent;
		while(n != null)
		{
			if(n.height == newHeight(n)) return;
			n.height = newHeight(n);
			if(n.parent == null) return;
			if(isImbalanced(n.parent)) break;
			n = n.parent;
		}
		if(n == null) return;
		Node z = n.parent, y = n;
		Node x = higherChild(y);
		if(y == z.right && x == y.left)
		{
			x.parent = z.parent;
			if(z.parent != null)
			{
				if(z.parent.right == z) z.parent.right = x;
				else z.parent.left = x;
			}
			else
				root = x;
			Node leftx = x.left, rightx = x.right;
			x.left = z; x.right = y;
			y.parent = z.parent = x;
			z.right = leftx; y.left = rightx;
			if(leftx != null) leftx.parent = z;
			if(rightx != null) rightx.parent = y;
			int h = y.height;
			x.height = h; y.height = z.height = h-1;
		}

		else if(y == z.right && x == y.right)
		{
			y.parent = z.parent;
			if(z.parent != null)
			{
				if(z.parent.right == z) z.parent.right = y;
				else z.parent.left = y;
			}
			else
				root = y;
			Node lefty = y.left;
			y.left = z; y.right = x;
			z.parent = x.parent = y;
			z.right = lefty;
			if(lefty != null) lefty.parent = z; 
			int h = y.height;
			x.height = z.height = h-1;
		}

		else if(y == z.left && x == y.right)
		{
			x.parent = z.parent;
			if(z.parent != null)
			{
				if(z.parent.right == z) z.parent.right = x;
				else z.parent.left = x;
			}
			else
				root = x;
			Node leftx = x.left, rightx = x.right;
			x.left = y; x.right = z;
			y.parent = z.parent = x;
			y.right = leftx; z.left = rightx;
			if(leftx != null) leftx.parent = y;
			if(rightx != null) rightx.parent = z;
			int h = y.height;
			x.height = h; y.height = z.height = h-1;
		}

		else
		{
			y.parent = z.parent;
			if(z.parent != null)
			{
				if(z.parent.right == z) z.parent.right = y;
				else z.parent.left = y;
			}
			else
				root = y;
			Node righty = y.right;
			y.left = x; y.right = z;
			z.parent = x.parent = y;
			z.left = righty;
			if(righty != null) righty.parent = z; 
			int h = y.height;
			x.height = z.height = h-1;
		}
	}

	private void inOrder(Node r, MyLinkedList<X> list)
	{
		if(r != null)
		{
			inOrder(r.left, list);
			list.Insert(r.data);
			inOrder(r.right, list);
		}
	}

	private void preOrder(Node r, MyLinkedList<X> list)
	{
		if(r != null)
		{
			list.Insert(r.data);
			preOrder(r.left, list);
			preOrder(r.right, list);
		}
	}

	private void postOrder(Node r, MyLinkedList<X> list)
	{
		if(r != null)
		{
			postOrder(r.left, list);
			postOrder(r.right, list);
			list.Insert(r.data);
		}
	}

	public MyLinkedList<X> getElements()
	{
		MyLinkedList<X> list = new MyLinkedList<X>();
		inOrder(root, list);
		return list;
	}

	public X inOrderSuccessor(X d)
	{
		Node n = find(root, d);
		if(n != null)
		{
			if(n.right != null)
			{
				Node ans = n.right;
				while(ans.left != null)
					ans = ans.left;
				return ans.data;
			}
			else
			{
				Node ans = n;
				while(ans.parent != null && ans.parent.right == ans)
					ans = ans.parent;
				if(ans.parent == null) return null;
				return ans.parent.data;
			}
		}
		return null;
	}
}
