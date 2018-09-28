import java.util.*;

public class MyLinkedList<X>
{
	Node head;
	class Node
	{
		X obj;
		Node next, prev;
		Node(X o)
		{
			obj = o;
			next = prev = null;
		}
	}
	
	public boolean IsEmpty()
	{
		return (head==null);
	}
	
	public boolean IsMember(X o)
	{
		Node temp = head;
		while(temp != null)
		{
			if(temp.obj.equals(o))
				return true;
			temp = temp.next;
		}
		return false;
	}
	
	public void Insert(X o)
	{
		Node tmp = new Node(o);
		if(head==null)
		{
			head = tmp;
			return;
		}
		Node n = head;
		while(n.next != null)
			n = n.next;
		tmp.prev = n;
		n.next = tmp;
	}
	
	public void Delete(X o)
	{
		Node temp = head;
		while(temp != null)
		{
			if(temp.obj.equals(o))
			{
				Node t = temp.next;
				if(temp.prev != null) temp.prev.next = t;
				else head = t;
				if(t != null) t.prev = temp.prev;
				return;
			}
			temp = temp.next;
		}
		throw new RuntimeException("Error - Given object is not present in list");
	}
	
	public int size()
	{
		Node tmp = head;
		int count = 0;
		while(tmp != null)
		{
			count++;
			tmp = tmp.next;
		}
		return count;
	}
}