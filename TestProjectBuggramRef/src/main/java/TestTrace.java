import java.util.Properties;
import java.io.*;
import org.example.*;

public class TestTrace {
	
	 public Properties loadProperties(final String name) 
	 {
		 final Properties properties = new Properties();
		 try (FileInputStream in = new FileInputStream(name))
		 {
		  properties.load(in);
		 }
		 
		 catch (final FileNotFoundException e) 
		 {	
			 try
				 {
					 final File file = new File(name.trim());
					 file.getParentFile().mkdirs();
					 file.createNewFile();
				 } 
			catch (final IOException innerE) 
			 	{
				 	Log.error("Error creating setting file " + name, innerE);
			 	}
		 } 
		 
		 catch (final Exception e) 
		 	{
			 Log.error("Error loading setting file " + name, e);
		 	}
		  
		 return properties;
		  }


}
