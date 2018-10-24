public class InvertedPageIndex
{
	private MyHashTable hashTable;
	
	public InvertedPageIndex()
	{
		hashTable = new MyHashTable();
	}
	
	// Add a new page entry p to the inverted page index.
	public void addPage(PageEntry p)
	{
		MyLinkedList<WordEntry>.Node tmp = p.getPageIndex().getWordEntries().head;
		while(tmp != null)
		{
			hashTable.addPositionsForWord(tmp.obj);
			tmp = tmp.next;
		}
	}

	public WordEntry getEntryFromWord(String str)
	{
		return hashTable.searchWord(str);
	}
	
	// Return a set of page-entries of webpages which contain the word str.
	public MySet<PageEntry> getPagesWhichContainWord(String str)
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

	public MySet<PageEntry> getPagesWhichContainAllWords(String[] words)
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

	public MySet<PageEntry> getPagesWhichContainAnyOfTheseWords(String[] words)
	{
		MySet<PageEntry> or_pages = new MySet<PageEntry>();
		if(words.length == 0) return or_pages;
		or_pages = getPagesWhichContainWord(words[0]);
		for(int i=1;i<words.length;i++)
			or_pages = or_pages.union(getPagesWhichContainWord(words[i]));
		return or_pages;
	}
	
	public MySet<PageEntry> getPagesWhichContainPhrase(String[] str)
	{
		MySet<PageEntry> webPages = getPagesWhichContainAllWords(str);
		if(webPages.size() == 0) return webPages;
		MyLinkedList<PageEntry>.Node tmp = webPages.getElements().head;
		MySet<PageEntry> phrasePages = new MySet<PageEntry>();
		while(tmp != null)
		{
			if(tmp.obj.containsPhrase(str) > 0)
				phrasePages.addElement(tmp.obj);
			tmp = tmp.next;
		}
		return phrasePages;
	}
}
