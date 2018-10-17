public class SearchResult implements Comparable<SearchResult>
{
	private PageEntry page;
	private float relevance;
	
	public SearchResult(PageEntry p, float r)
	{
		page = p;
		relevance = r;
	}

	public int compareTo(SearchResult otherObject) 
    {
    	if(relevance > otherObject.getRelevance()) return 1;
    	if(relevance < otherObject.getRelevance()) return -1;
        return 0;
    }
	
	public PageEntry getPageEntry()
	{
		return page;
	}
	
	public float getRelevance()
	{
		return relevance;
	}
}
