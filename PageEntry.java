import java.util.*;
import java.io.*;

public class PageEntry
{
	// store the the information related to a webpage.
	String pageName;
	PageIndex index;
	MySet<String> connectorWords = new MySet<String>();
	
	// Read this file, and create the page index.
	public PageEntry(String pageName)
	{
		this.pageName = pageName;
		index = new PageIndex();
		connectorWords.addElement("a"); connectorWords.addElement("an"); connectorWords.addElement("the"); connectorWords.addElement("they");
		connectorWords.addElement("these"); connectorWords.addElement("this"); connectorWords.addElement("for"); connectorWords.addElement("is");
		connectorWords.addElement("are"); connectorWords.addElement("was"); connectorWords.addElement("of"); connectorWords.addElement("or");
		connectorWords.addElement("and"); connectorWords.addElement("does"); connectorWords.addElement("will"); connectorWords.addElement("whose");
		try
		{
			FileInputStream fstream = new FileInputStream ("./webpages/" + pageName);
			Scanner s = new Scanner(fstream);
			int wordIndex = 1;
			while (s.hasNextLine())
			{
				String str = s.nextLine();
				str = str.toLowerCase();
				String[] words = str.split("\\s++|\\{|}|[|]|<|>|\\(|\\)|\\.|,|;|'|\"|\\?|#|!|-|:");
				int size = words.length;
				for(int i=0;i<size;i++)
				{
					if(words[i].length() > 0)
					{
						if(!connectorWords.IsMember(words[i]))
						{
							if(words[i].equals("stacks")) words[i] = "stack";
							else if(words[i].equals("structures")) words[i] = "structure";
							else if(words[i].equals("applications")) words[i] = "application";
							Position p = new Position(this, wordIndex);
							index.addPositionForWord(words[i],p);
						}
						wordIndex++;
					}
				}
			}
		}
		catch ( FileNotFoundException e) { throw new RuntimeException("Error - File not found"); }
	}
	
	public boolean equals(PageEntry page)
	{
		if(this == page) return true;
		if(page == null) return false;
		return  page.getName().equals(pageName);
	}
	
	public String getName()
	{
		return pageName;
	}
	
	public PageIndex getPageIndex()
	{
		return index;
	}
	
	public float getRelevanceOfPage(String str)
	{
		
	}
}
