public class MySet<X>
{
	MyLinkedList<X> set;
	
	public MySet()
	{
		set = new MyLinkedList<X>();
	}	
	
	public boolean IsMember(X o)
	{
		return set.IsMember(o);
	}
	
	void addElement(X element)
	{
		if(set.IsMember(element))
			throw new RuntimeException("Error - Object is already in the set");
		set.Insert(element);
	}
	
	MySet<X> union(MySet<X> otherSet)
	{
		MySet<X> union = new MySet<X>();
		MyLinkedList<X>.Node temp = set.head;
		while(temp != null)
		{
			union.addElement(temp.obj);
			temp = temp.next;
		}
		temp = otherSet.set.head;
		while(temp != null)
		{
			try { union.addElement(temp.obj); }
			catch(RuntimeException ex) {}
			temp = temp.next;
		}
		return union;
	}
	
	MySet<X> intersection(MySet<X> otherSet)
	{
		MySet<X> intersection = new MySet<X>();
		MyLinkedList<X>.Node temp = set.head;
		while(temp != null)
		{
			if(otherSet.IsMember(temp.obj)) intersection.addElement(temp.obj);
			temp = temp.next;
		}
		return intersection;
	}
	
	int size()
	{
		return set.size();
	}
}
