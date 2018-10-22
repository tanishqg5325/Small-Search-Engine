import java.util.*;

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

	String[] processString(String[] str)
	{
		int n = str.length;
		for(int i = 0; i<n; i++)
			str[i] = processString(str[i]);
		return str;
	}
	
	float getInverseDocumentFrequency(String word)
	{
		int N = pages.size();
		int nwp = ipi.getPagesWhichContainWord(word).size();
		if(nwp == 0) return Float.MAX_VALUE;
		float ans = ((float)N)/nwp;
		return (float)Math.log(ans);
	}

	public float getRelevanceOfPage(PageEntry page, String[] str, boolean doTheseWordsRepresentAPhrase)
	{
		float ans = 0;
		int n = str.length;
		if(doTheseWordsRepresentAPhrase == false)
		{
			for(int i=0;i<n;i++)
				ans += (page.getTermFrequency(str[i]) * getInverseDocumentFrequency(str[i]));
		}
		else
		{

		}
		return ans;
	}
	
	ArrayList<SearchResult> getSortedSearchResults(MySet<PageEntry> webPages, String[] words, boolean doTheseWordsRepresentAPhrase)
	{
		MyLinkedList<PageEntry>.Node tmp = webPages.getElements().head;
		MySet<SearchResult> search_results = new MySet<SearchResult>();
		float rel;
		while(tmp != null)
		{
			rel = getRelevanceOfPage(tmp.obj, words, doTheseWordsRepresentAPhrase);
			search_results.addElement(new SearchResult(tmp.obj, rel));
			tmp = tmp.next;
		}
		MySort<SearchResult> sorted = new MySort<SearchResult>();
		return sorted.sortThisList(search_results);
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
			String[] str = new String[1]; str[0] = x;
			MySet<PageEntry> pageSet = ipi.getPagesWhichContainWord(x);
			if(pageSet.size() == 0)
				System.out.println("No webpage contains word " + x);
			else
			{
				ArrayList<SearchResult> search_results = getSortedSearchResults(pageSet, str, false);
				int n = search_results.size();
				for(int i=0;i<n-1;i++)
					System.out.print(search_results.get(i).getPageEntry().getName() + ", ");
				System.out.println(search_results.get(n-1).getPageEntry().getName());
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

		else if(query.length > 1 && query[0].equals("queryFindPagesWhichContainAllWords"))
		{
			String[] str = Arrays.copyOfRange(query, 1, query.length);
			str = processString(str);
			MySet<PageEntry> pageSet = ipi.getPagesWhichContainAllWords(str);
			if(pageSet.size() == 0)
				System.out.println("No webpage contains all the given words");
			else
			{
				ArrayList<SearchResult> search_results = getSortedSearchResults(pageSet, str, false);
				int n = search_results.size();
				for(int i=0;i<n-1;i++)
					System.out.print(search_results.get(i).getPageEntry().getName() + ", ");
				System.out.println(search_results.get(n-1).getPageEntry().getName());
			}
		}

		else if(query.length > 1 && query[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			String[] str = Arrays.copyOfRange(query, 1, query.length);
			str = processString(str);
			MySet<PageEntry> pageSet = ipi.getPagesWhichContainAnyOfTheseWords(str);
			if(pageSet.size() == 0)
				System.out.println("No webpage contains any of the given words");
			else
			{
				ArrayList<SearchResult> search_results = getSortedSearchResults(pageSet, str, false);
				int n = search_results.size();
				for(int i=0;i<n-1;i++)
					System.out.print(search_results.get(i).getPageEntry().getName() + ", ");
				System.out.println(search_results.get(n-1).getPageEntry().getName());
			}
		}

		else if(query.length > 1 && query[0].equals("queryFindPagesWhichContainPhrase"))
		{
			String[] str = Arrays.copyOfRange(query, 1, query.length);
			str = processString(str);
			MySet<PageEntry> pageSet = ipi.getPagesWhichContainPhrase(str);
			if(pageSet.size() == 0)
				System.out.println("No webpage contains the given phrase");
			else
			{
				ArrayList<SearchResult> search_results = getSortedSearchResults(pageSet, str, true);
				int n = search_results.size();
				for(int i=0;i<n-1;i++)
					System.out.print(search_results.get(i).getPageEntry().getName() + ", ");
				System.out.println(search_results.get(n-1).getPageEntry().getName());
			}
		}
		
		else
			System.out.println("Error - Invalid Input");
	}
}
