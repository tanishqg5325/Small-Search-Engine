import java.io.*;
import java.util.*;

public class PageEntry
{
	// store the the information related to a webpage.
	private String pageName;
	private PageIndex index;
	private int number_of_words;
	private MySet<String> connectorWords = new MySet<String>();
	
	// Read this file, and create the page index.
	public PageEntry(String pageName)
	{
		this.pageName = pageName;
		index = new PageIndex();
		number_of_words = 0;
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
							number_of_words++;
						}
						wordIndex++;
					}
				}
			}
		}
		catch (FileNotFoundException e) { throw new RuntimeException("Error - File not found"); }
	}
	
	public boolean equals(Object page)
	{
		PageEntry entry = (PageEntry)page;
		if(this == entry) return true;
		if(entry == null) return false;
		return  pageName.equals(entry.getName());
	}
	
	public String getName()
	{
		return pageName;
	}
	
	public PageIndex getPageIndex()
	{
		return index;
	}
	
	public float getTermFrequency(String word)
	{
		MyLinkedList<WordEntry>.Node tmp = index.getWordEntries().head;
		WordEntry wordEntry = null;
		while(tmp != null)
		{
			if(tmp.obj.getWord().equals(word))
			{
				wordEntry = tmp.obj;
				break;
			}
			tmp = tmp.next;
		}
		if(wordEntry == null) return 0;
		int fwp = wordEntry.getAllPositionsForThisWord().size();
		//if(word.equals("function"))
			//System.out.println(pageName + " " + fwp + " " + number_of_words);
		return ((float)(fwp))/number_of_words;
	}
}
