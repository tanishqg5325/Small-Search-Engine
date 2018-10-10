import java.util.*;
import java.io.*;

public class PageEntry
{
	// store the the information related to a webpage.
	String pageName;
	PageIndex index;
	
	// Read this file, and create the page index.
	public PageEntry(String pageName)
	{
		this.pageName = pageName;
		index = new PageIndex();
		try
		{
			FileInputStream fstream = new FileInputStream (pageName);
			Scanner s = new Scanner (fstream);
			while (s.hasNextLine())
			{
				// todo s.nextLine();
			}
		}
		catch ( FileNotFoundException e) { throw new RuntimeException("Error - File not found"); }
	}
	
	public PageIndex getPageIndex()
	{
		return index;
	}
}
