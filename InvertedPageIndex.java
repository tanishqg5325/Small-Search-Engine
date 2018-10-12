public class InvertedPageIndex
{
	private MyHashTable hashTable;
	
	public InvertedPageIndex()
	{
		hashTable = new MyHashTable();
	}
	
	// Add a new page entry p to the inverted page index.
	void addPage(PageEntry p)
	{
		MyLinkedList<WordEntry>.Node tmp = p.getPageIndex().getWordEntries().head;
		while(tmp != null)
		{
			hashTable.addPositionsForWord(tmp.obj);
			tmp = tmp.next;
		}
	}
	
	// Return a set of page-entries of webpages which contain the word str.
	MySet<PageEntry> getPagesWhichContainWord(String str)
	{
		MySet<PageEntry> page_entries = new MySet<PageEntry>();
		WordEntry entry = hashTable.searchWord(str);
		if(entry == null) return page_entries;
		MyLinkedList<Position>.Node tmp = entry.getAllPositionsForThisWord().head;
		while(tmp != null)
		{
			try { page_entries.addElement(tmp.obj.getPageEntry()); }
			catch(RuntimeException e) {}
			tmp = tmp.next;
		}
		return page_entries;
	}
	
	WordEntry getEntryFromWord(String str)
	{
		return hashTable.searchWord(str);
	}
}
