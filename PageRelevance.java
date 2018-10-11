public class PageRelevance
{
	PageEntry page;
	float relevance;
	
	public PageRelevance(PageEntry p, float f)
	{
		page = p;
		relevance = f;
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
