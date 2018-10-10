public class SearchEngine
{
	InvertedPageIndex ipi;
	
	public SearchEngine()
	{
		ipi = new InvertedPageIndex();
	}
	
	void performAction(String actionMessage)
	{
		String[] query = actionMessage.split("\\s");
		if(query.length == 2 && query[0].equals("addPage"))
		{
			
		}
		
		else if(query.length == 2 && query[0].equals("queryFindPagesWhichContainWord"))
		{
			
		}
		
		else if(query.length == 3 && query[0].equals("queryFindPositionsOfWordInAPage"))
		{
			
		}
		
		else
			System.out.println(actionMessage + ": Error - Invalid Input");
	}
}
