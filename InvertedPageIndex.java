public class InvertedPageIndex
{
	MyHashTable hashTable;
	
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
		MyLinkedList<Position>.Node tmp = hashTable.searchWord(str).getAllPositionsForThisWord().head;
		while(tmp != null)
		{
			page_entries.addElement(tmp.obj.getPageEntry());
			tmp = tmp.next;
		}
		return page_entries;
	}
}
