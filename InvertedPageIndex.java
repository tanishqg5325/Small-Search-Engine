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
		WordEntry entry = getEntryFromWord(str);
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

	MySet<PageEntry> getPagesWhichContainAllWords(String[] words)
	{
		MySet<PageEntry> and_pages = new MySet<PageEntry>();
		if(words.length == 0) return and_pages;
		and_pages = getPagesWhichContainWord(words[0]);
		for(int i=1;i<words.length;i++)
		{
			and_pages = and_pages.intersection(getPagesWhichContainWord(words[i]));
			if(and_pages.size() == 0) return and_pages;
		}
		return and_pages;
	}

	MySet<PageEntry> getPagesWhichContainAnyOfTheseWords(String[] words)
	{
		MySet<PageEntry> or_pages = new MySet<PageEntry>();
		if(words.length == 0) return or_pages;
		or_pages = getPagesWhichContainWord(words[0]);
		for(int i=1;i<words.length;i++)
			or_pages = or_pages.union(getPagesWhichContainWord(words[i]));
		return or_pages;
	}
	
	MySet<PageEntry> getPagesWhichContainPhrase(String[] str)
	{
		MySet<PageEntry> webPages = getPagesWhichContainAllWords(str);
		if(webPages.size() == 0) return webPages;
		MyLinkedList<PageEntry>.Node tmp = webPages.getElements().head;
		while(tmp != null)
		{

			tmp = tmp.next;
		}
		return webPages;
	}
}
