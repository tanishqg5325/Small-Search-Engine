import java.util.*;

public class WordEntry
{
	String word;
	MyLinkedList<Position> entry; 
	
	public WordEntry(String word)
	{
		this.word = word;
		entry = new MyLinkedList<Position>();
	}
	
	void addPosition(Position position)
	{
		entry.Insert(position);
	}
	
	void addPositions(MyLinkedList<Position> positions)
	{
		MyLinkedList<Position>.Node temp = positions.head;
		while(temp != null)
		{
			addPosition(temp.obj);
			temp = temp.next;
		}
	}
	
	MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return entry;
	}
	
	float getTermFrequency(String word)
	{
		
	}
}
