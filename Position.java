import java.util.*;

public class Position
{
	// represents a tuple <page p, word position i>.
	PageEntry p;
	int wordIndex;
	
	public Position(PageEntry p, int wordIndex)
	{
		this.p = p;
		this.wordIndex = wordIndex;
	}
	
	public boolean equals(Position pos)
	{
		if(this == pos) return true;
		if(pos == null) return false;
		return (p.equals(pos.p) && wordIndex == pos.wordIndex);
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
