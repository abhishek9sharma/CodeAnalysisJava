package Client;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import utils.*;
import astparsing.*;
import java.util.logging.*;
import utils.*;

public class AnalyzeFilesClient {
	
	public static String classpathfolder="";
	public static List<String> classpathjars;
	public static String clonedprojectsfolder="";
	public static String sourcepathfinder="";
	public static String ASTTraceFolder="";


	public static void main(String args[])
	{
	    try	
		{
			//SET LOGGING
	    	String cwd = new File("").getAbsolutePath();
	    	String rootd = new File(cwd+"/..").getCanonicalPath();
	    	System.out.println(rootd);
	    	
	    	
	    	
			String logfilepath=cwd+"/Logs/ExecutionLog.txt";
			String excplogfilepath=cwd+"/Logs/ExecutionLog.txt";
			
			CustomLogger.setLogger(logfilepath);
			ExceptionLogger.setLogger(excplogfilepath);
			
	       
	        //LOAD FILES TO PARSE
			//SET BELOW PATH TO TEXT FILE WHICH CONTIANS PATH OF ALL JAVE FILES
			String astfilelist="../TestProjectBugramSelf/alljavafiles.lst";
			ReadFromFile readF=new ReadFromFile(astfilelist);
			Iterator<String> iterator = readF.linesinfile.iterator();
			
			
			//SET ENVVARIABLE
			AnalyzeFilesClient.classpathfolder="/home/oldmonk/.m2/repository";		
			AnalyzeFilesClient.classpathjars=Environments.setClassPath(AnalyzeFilesClient.classpathfolder);
			AnalyzeFilesClient.ASTTraceFolder=cwd+"/ASTOutput";
			
			//SET BELOW PATH TO CLONED PROJECTS FOLDER 
			AnalyzeFilesClient.clonedprojectsfolder=rootd+"/";
			AnalyzeFilesClient.sourcepathfinder="/src/main/java/";
			
			//PARSE FILES
			while (iterator.hasNext()) 
			{
				String javafiletoprocess=iterator.next();
				if(javafiletoprocess.contains(AnalyzeFilesClient.sourcepathfinder))
				{
					try 
					{
						ParseAST parser=new ParseAST();
						parser.SetParser(new BaselineBugramParser());
						parser.ProcessFile(javafiletoprocess);
						
					}
					catch (Exception e)
					{
						//logger.info("Below Exception Occured for FIle " + javafiletoprocess);
						//logger.info(e.toString());
						CustomLogger.Log("Below Exception Occured for FIle " + javafiletoprocess);					
						CustomLogger.Log(e.toString());
						
							
					}
				}
					else
					{
						System.out.println("File at path " + javafiletoprocess + " Ignored");
					}
				
			}
		}
		catch (Exception e)
		{
			///System.out.println("Below Exception Occured whiel Loading File List ");
			System.out.println(e.toString());
			
			
			
			CustomLogger.Log("Below Exception Occured whiel Loading File List ");
			CustomLogger.Log(e.toString());
			
		}
		finally 
		{
			System.out.println("Finished Processing");
			CustomLogger.Log("Finished Processing");
		}

	}


	
}
