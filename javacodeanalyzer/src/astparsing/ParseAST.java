package astparsing;

public class ParseAST {
	IJavaCodeParser astparser;
	
	public void SetParser(IJavaCodeParser parserin) 
	{
		this.astparser=parserin;
		
	}
	
	public void ProcessFile(String filepath) 
	{
		this.astparser.processFile(filepath);
	}
	
}
