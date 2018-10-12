import java.util.Vector;

public class SearchEngine
{
	private InvertedPageIndex ipi;
	private MySet<PageEntry> pages;
	
	public SearchEngine()
	{
		ipi = new InvertedPageIndex();
		pages = new MySet<PageEntry>();
	}
	
	String processString(String str)
	{
		str = str.toLowerCase();
		if(str.equals("stacks")) str = "stack";
		else if(str.equals("structures")) str = "structure";
		else if(str.equals("applications")) str = "application";
		return str;
	}
	
	float getInverseDocumentFrequency(String word)
	{
		int N = pages.size();
		WordEntry wordEntry = ipi.getEntryFromWord(word);
		if(wordEntry == null) return Float.MAX_VALUE;
		MyLinkedList<Position>.Node pos = wordEntry.getAllPositionsForThisWord().head;
		MySet<PageEntry> webPages = new MySet<PageEntry>();
		while(pos != null)
		{
			try { webPages.addElement(pos.obj.getPageEntry()); }
			catch(RuntimeException e) {}
			pos = pos.next;
		}
		int nwp = webPages.size();
		float ans = ((float)N)/nwp;
		return (float)Math.log(ans);
	}
	
	MyLinkedList<PageEntry> getSortedResults(MySet<PageEntry> webPages, String word)
	{
		MyLinkedList<PageEntry>.Node tmp = webPages.getElements().head;
		MyLinkedList<PageRelevance> page_relevances = new MyLinkedList<PageRelevance>();
		float inv_doc_freq = getInverseDocumentFrequency(word);
		float rel = tmp.obj.getTermFrequency(word) * inv_doc_freq;
		page_relevances.Insert(new PageRelevance(tmp.obj, rel));
		MyLinkedList<PageRelevance>.Node start;
		tmp = tmp.next;
		while(tmp != null)
		{
			start = page_relevances.head;
			rel = tmp.obj.getTermFrequency(word) * inv_doc_freq;
			MyLinkedList<PageRelevance>.Node pr = page_relevances.new Node(new PageRelevance(tmp.obj, rel));
			page_relevances.size++;
			if(rel >= start.obj.getRelevance())
			{
				pr.next = start;
				start.prev = pr;
				page_relevances.head = pr;
				tmp = tmp.next;
				continue;
			}
			while(start.next != null && start.next.obj.getRelevance() > rel)
				start = start.next;
			pr.next = start.next; pr.prev = start;
			if(start.next != null) start.next.prev = pr;
			start.next = pr;
			tmp = tmp.next;
		}
		MyLinkedList<PageEntry> ans = new MyLinkedList<PageEntry>();
		start = page_relevances.head;
		while(start != null)
		{
			ans.Insert(start.obj.getPageEntry());
			start = start.next;
		}
		return ans;
	}
	
	boolean contains(MySet<PageEntry> pgs, String doc)
	{
		MyLinkedList<PageEntry>.Node tmp = pgs.getElements().head;
		while(tmp != null)
		{
			if(tmp.obj.getName().equals(doc))
				return true;
			tmp = tmp.next;
		}
		return false;
	}
	
	void performAction(String actionMessage)
	{
		String[] query = actionMessage.split("\\s");
		if(query.length == 2 && query[0].equals("addPage"))
		{
			if(contains(pages, query[1]))
			{
				System.out.println("Webpage " + query[1] + " already added");
				return;
			}
			try
			{
				PageEntry webPage = new PageEntry(query[1]);
				ipi.addPage(webPage);
				pages.addElement(webPage);
			}
			catch(RuntimeException e)
			{
				System.out.println("Webpage " + query[1] + " not found");
				return;
			}	
		}

		else if(query.length == 2 && query[0].equals("queryFindPagesWhichContainWord"))
		{
			String x = query[1];
			x = processString(x);
			MySet<PageEntry> pageSet = ipi.getPagesWhichContainWord(x);
			if(pageSet.size() == 0)
				System.out.println("No webpage contains word " + x);
			else
			{
				MyLinkedList<PageEntry>.Node tmp = getSortedResults(pageSet, x).head;
				while(tmp.next != null)
				{
					System.out.print(tmp.obj.getName() + ", ");
					tmp = tmp.next;
				}
				System.out.println(tmp.obj.getName());
			}
		}
		
		else if(query.length == 3 && query[0].equals("queryFindPositionsOfWordInAPage"))
		{
			String word = processString(query[1]), document = query[2];
			if(!contains(pages, document))
			{
				System.out.println("No webpage " + document + " found");
				return;
			}
			WordEntry wordEntry = ipi.getEntryFromWord(word);
			if(wordEntry == null)
			{
				System.out.println("Webpage " + document + " does not contain word " + word);
				return;
			}
			MyLinkedList<Position>.Node tmp = wordEntry.getAllPositionsForThisWord().head;
			Vector<Integer> v = new Vector<Integer>();
			while(tmp != null)
			{
				if(tmp.obj.getPageEntry().getName().equals(document))
					v.add(tmp.obj.getWordIndex());
				tmp = tmp.next;
			}
			int n = v.size();
			if(n == 0)
			{
				System.out.println("Webpage " + document + " does not contain word " + word);
				return;
			}
			for(int i=0;i<n-1;i++)
				System.out.print(v.get(i) + ", ");
			System.out.println(v.get(n-1));
		}
		
		else
			System.out.println("Error - Invalid Input");
	}
}
