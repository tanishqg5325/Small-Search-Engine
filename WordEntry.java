import java.util.*;

public class WordEntry
{
	// For a string str, this class stores the list
	// of word indice's where str is present in the document(s).
	String word;
	MyLinkedList<Position> entry; 
	
	public WordEntry(String word)
	{
		this.word = word;
		entry = new MyLinkedList<Position>();
	}
	
	public boolean equals(WordEntry w)
	{
		if(this == w) return true;
		if(w == null) return false;
		return (word.equals(w.getWord()));
	}
	
	void addPosition(Position position)
	{
		if(entry.IsMember(position))
			throw new RuntimeException("Error - Given position is already present");
		entry.Insert(position);
	}
	
	void addPositions(MyLinkedList<Position> positions)
	{
		MyLinkedList<Position>.Node temp = positions.head;
		int count = 0;
		while(temp != null)
		{
			try { addPosition(temp.obj); }
			catch(RuntimeException ex) { count++; }
			temp = temp.next;
		}
		if(count > 0)
			throw new RuntimeException("Error - " + count  + " positions are already present in list");
	}
	
	String getWord()
	{
		return word;
	}
	
	MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return entry;
	}
	
	/*float getTermFrequency(String word)
	{
		
	}*/
}
