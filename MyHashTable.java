public class MyHashTable
{
	// implements the hashtable used by the InvertedPageIndex.
	// It maps a word to its word-entry.
	private int size;
	private MyLinkedList<WordEntry>[] hashTable;
	
	@SuppressWarnings("unchecked")
	public MyHashTable()
	{
		size = 100000;
		hashTable = new MyLinkedList[size];
	}
	
	// Create a hash function which maps a string to the index of its word-entry in the
	// hashtable. The implementation of hashtable supports chaining.
	private int getHashIndex(String str)
	{
		int n = str.length(), a = 1, code = 0;
		for(int i=0;i<n;i++)
		{
			code = (code + str.charAt(i)*a) % size;
			a = (a * 41) % size;
		}
        return code;
	}
	
	// This adds an entry to the hashtable: stringName(w) - > positionList(w). If no word-entry
	// exists, then create a new word entry. However, if a word-entry exists,
	// then merge w with the existing word-entry.
	public void addPositionsForWord(WordEntry w)
	{
		int index = getHashIndex(w.getWord());
		if(hashTable[index] == null)
		{
			hashTable[index] = new MyLinkedList<WordEntry>();
			hashTable[index].Insert(w.Clone());
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
			hashTable[index].Insert(w.Clone());
		}
	}
	
	public WordEntry searchWord(String str)
	{
		int index = getHashIndex(str);
		if(hashTable[index] == null) return null;
		MyLinkedList<WordEntry>.Node tmp = hashTable[index].head;
		while(tmp != null)
		{
			if(tmp.obj.getWord().equals(str))
				return tmp.obj;
			tmp = tmp.next;
		}
		return null;
	}
}
