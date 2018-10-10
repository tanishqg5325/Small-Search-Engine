// done
public class PageIndex
{
	// stores one word-entry for each unique word in the document.
	MyLinkedList<WordEntry> words;
	
	public PageIndex()
	{
		words = new MyLinkedList<WordEntry>();
	}
	
	void addPositionForWord(String str, Position p)
	{
		MyLinkedList<WordEntry>.Node temp = words.head;
		while(temp != null)
		{
			if(temp.obj.word.equals(str))
			{
				try { temp.obj.addPosition(p); return; }
				catch(RuntimeException ex) { throw ex; }
			}
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