public class Position implements Comparable<Position>
{
	// represents a tuple <page p, word position i>.
	private PageEntry p;
	private int wordIndex;
	
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
		return (p.equals(tmp.getPageEntry()) && wordIndex == tmp.getWordIndex());
	}

	public int compareTo(Position pos) 
    { 
        return wordIndex - pos.getWordIndex(); 
    }
	
	public PageEntry getPageEntry()
	{
		return p;
	}
	
	public int getWordIndex()
	{
		return wordIndex;
	}
}
