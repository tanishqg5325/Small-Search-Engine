import java.util.*;

public class Position
{
	PageEntry p;
	int wordIndex;
	
	public Position(PageEntry p, int wordIndex)
	{
		this.p = p;
		this.wordIndex = wordIndex;
	}
	
	public PageEntry getPageEntry()
	{
		return p;
	}
	
	int getWordIndex()
	{
		return wordIndex;
	}
}
