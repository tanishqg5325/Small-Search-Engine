public class WordEntry
{
	// For a string str, this class stores the list
	// of word indice's where str is present in the document(s).
	private String word;
	private MyLinkedList<Position> entry; 
	
	public WordEntry(String word)
	{
		this.word = word;
		entry = new MyLinkedList<Position>();
	}
	
	public boolean equals(Object w)
	{
		WordEntry entry = (WordEntry)w;
		if(this == entry) return true;
		if(entry == null) return false;
		return word.equals(entry.getWord());
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
	
	String getWord()
	{
		return word;
	}
	
	MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return entry;
	}
	
	WordEntry Clone()
	{
		WordEntry new_word = new WordEntry(word);
		new_word.entry = entry.Clone();
		return new_word;
	}
}
