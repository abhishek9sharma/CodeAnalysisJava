package astparsing;

public class BasicASTParser implements IJavaCodeParser { 
	
	JavaFileInfo javaFileInfo;

	@Override
	public void processFile(String filepath) {
		// TODO Auto-generated method stub
		this.javaFileInfo=new JavaFileInfo(filepath);
	}



}
