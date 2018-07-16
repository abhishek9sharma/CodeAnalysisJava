package astparsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Client.AnalyzeFilesClient;
import java.io.File;

public class JavaFileInfo {
	
	String filepath="";
	String codestring="";
	String sourcepath="";
	String binfolder="";
	String opfilename="";
	
	public JavaFileInfo(String filepath) 
	{
		this.filepath=filepath;
		try {
			this.codestring=this.setCodeString(filepath);
			this.setbinfolder();
			this.setopfilename();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String setCodeString(String f) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = null;
		StringBuffer ans = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			// if (line.trim().length() == 0)
			// continue;
			ans.append(line + "\n");
		}
		reader.close();
		return ans.toString();
	}
	
	public String GetCodeString()
	{
		return this.codestring;
	}
	
	
	public void setbinfolder()
	{
		String folderstart=this.filepath.replace(AnalyzeFilesClient.clonedprojectsfolder, "");
		String projecfoldername=folderstart.split(File.separator)[0];
		this.binfolder=AnalyzeFilesClient.clonedprojectsfolder+File.separator+projecfoldername+File.separator+"target";		
		
	}

	public void setopfilename()
	{
		String folderstart=this.filepath.replace(AnalyzeFilesClient.clonedprojectsfolder, "");
		folderstart=folderstart.replaceAll(".java", "");
		this.opfilename=folderstart.replaceAll(File.separator, ".");
		
	}
	
}
