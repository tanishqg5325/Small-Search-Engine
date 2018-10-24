public class MyLinkedList<X>
{
	Node head, tail;
	int size;
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
	
	public MyLinkedList()
	{
		head = tail = null;
		size = 0;
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
		size++;
		if(head==null)
		{
			head = tail = tmp;
			return;
		}
		tail.next = tmp;
		tmp.prev = tail;
		tail = tmp;
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
				else tail = temp.prev;
				size--;
				return;
			}
			temp = temp.next;
		}
		throw new RuntimeException("Error - Given object is not present in list");
	}
	
	public int size()
	{
		return size;
	}
}
