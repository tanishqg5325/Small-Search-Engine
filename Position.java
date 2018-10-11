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
	
	public boolean equals(Object pos)
	{
		Position tmp = (Position)pos;
		if(this == tmp) return true;
		if(tmp == null) return false;
		return (p.equals(tmp.p) && wordIndex == tmp.wordIndex);
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
