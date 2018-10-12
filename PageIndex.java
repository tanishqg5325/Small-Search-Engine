public class PageIndex
{
	// stores one word-entry for each unique word in the document.
	private MyLinkedList<WordEntry> words;
	
	public PageIndex()
	{
		words = new MyLinkedList<WordEntry>();
	}
	
	void addPositionForWord(String str, Position p)
	{
		MyLinkedList<WordEntry>.Node temp = words.head;
		while(temp != null)
		{
			if(temp.obj.getWord().equals(str))
			{
				try { temp.obj.addPosition(p); return; }
				catch(RuntimeException ex) { throw ex; }
			}
			temp = temp.next;
		}
		WordEntry new_word = new WordEntry(str);
		new_word.addPosition(p);
		words.Insert(new_word);
	}
	
	MyLinkedList<WordEntry> getWordEntries()
	{
		return words;
	}
}
