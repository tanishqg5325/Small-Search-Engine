public class MyHashTable
{
	// implements the hashtable used by the InvertedPageIndex.
	// It maps a word to its word-entry.
	int size;
	MyLinkedList<WordEntry>[] hashTable;
	
	public MyHashTable()
	{
		size = 100;
		hashTable = new MyLinkedList[size];
	}
	
	// Create a hash function which maps a string to the index of its word-entry in the
	// hashtable. The implementation of hashtable supports chaining.
	private int getHashIndex(String str)
	{
		int code = str.hashCode();
        code = Math.abs(code);
        return code % size;
	}
	
	// This adds an entry to the hashtable: stringName(w) - > positionList(w). If no word-entry
	// exists, then create a new word entry. However, if a word-entry exists,
	// then merge w with the existing word-entry.
	void addPositionsForWord(WordEntry w)
	{
		int index = getHashIndex(w.getWord());
		if(hashTable[index] == null)
		{
			hashTable[index] = new MyLinkedList<WordEntry>();
			hashTable[index].Insert(w);
		}
		else
		{
			MyLinkedList<WordEntry>.Node tmp = hashTable[index].head;
			while(tmp != null)
			{
				if(tmp.obj.equals(w))
				{
					tmp.obj.addPositions(w.getAllPositionsForThisWord());
					return;
				}
				tmp = tmp.next;
			}
			hashTable[index].Insert(w);
		}
	}
	
	WordEntry searchWord(String str)
	{
		int index = getHashIndex(str);
		if(hashTable[index] == null) return null;
		MyLinkedList<WordEntry>.Node tmp = hashTable[index].head;
		while(tmp != null)
		{
			if(tmp.obj.getWord().equals(str))
				return tmp.obj;
		}
		return null;
	}
}
