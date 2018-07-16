package utils;

//https://stackoverflow.com/questions/20737880/java-logging-through-multiple-classes
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ExceptionLogger {
	 static Logger logger = null;  
	 static Logger excplogger = null; 
	  FileHandler logfile;  
	
	
	
	private ExceptionLogger(String filepath) {
		//System.out.println("COSNT CALLED");
		logger=Logger.getLogger("ExecutionLog");
		try 
		{
			logfile= new FileHandler(filepath);
		} 
		catch (SecurityException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		logger.addHandler(logfile);
		
        SimpleFormatter formatter = new SimpleFormatter();  
        logfile.setFormatter(formatter); 
        logger.setUseParentHandlers(false);

       
	}
	
	private static Logger getLogger()
	{

	    return logger;
	}
	
	public static Logger setLogger(String filepath)
	{
	    if(logger == null){
	        new ExceptionLogger(filepath);
	    }
	    return logger;
	}
	
	public static void Log(String msg)
	{
		//getLogger().info(msg);
		getLogger().info(msg);
	    //System.out.println(msg);
	}
}
