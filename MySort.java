import java.util.ArrayList;

public class MySort<Sortable extends Comparable<Sortable>>
{
	// sorts the set in descending order
	public ArrayList<Sortable> sortThisList(MySet<Sortable> listOfSortableEntries)
	{
		MyLinkedList<Sortable>.Node tmp = listOfSortableEntries.getElements().head;
		ArrayList<Sortable> sortedList = new ArrayList<Sortable>();
		while(tmp != null)
		{
			sortedList.add(tmp.obj);
			tmp = tmp.next;
		}
		sortedList.trimToSize();
		int n = sortedList.size(),ind;
		// selection sort
		for(int i=0;i<n-1;i++)
		{
			ind = i;
			for(int j=i+1;j<n;j++)
				if(sortedList.get(j).compareTo(sortedList.get(ind)) > 0)
					ind = j;
			Sortable temp = sortedList.get(i);
			sortedList.set(i, sortedList.get(ind));
			sortedList.set(ind, temp);
		}
		return sortedList;
	}

	public static void main(String[] args)
	{
		MySort<Integer> ms = new MySort<Integer>();
		MySet<Integer> ints = new MySet<Integer>();
		ints.addElement(4); ints.addElement(9); ints.addElement(6); ints.addElement(0); ints.addElement(50); ints.addElement(-4);
		ArrayList<Integer> sorted = ms.sortThisList(ints);
		int n = sorted.size();
		for(int i=0;i<n;i++) System.out.println(sorted.get(i));
	}
}
