package astparsing;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Client.AnalyzeFilesClient;

//adapted from 	https://github.com/liyuning0811/ASTSample

public class Environments {

	public static final String UTF_8 = "UTF-8";
	public static final String SJIS = "SJIS";
	public static final String EUC_JP = "EUC-JP";
	
	public static String[] getClassPath() throws IOException {
		// TODO Auto-generated method stub
		
		List<String> classpath = new ArrayList<String>();
		//classpath.add("/arquillian-core/bin/");
		
		Path start = Paths.get("/home/oldmonk/.m2/repository");
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            List<String> collect = stream
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.toList());
            for(String f: collect){
            	if (f.endsWith(".jar"))
            		classpath.add(f);
            }
        }
        
		String property = System.getProperty("java.class.path", ".");
		String[] classpathArr = classpath.toArray(new String[classpath.size()]);
		//return property.split(File.pathSeparator);
		//return new String[] { "/Cloned/arquillian-core/bin/" };
		return classpathArr;

	}
	
	public  static List<String> setClassPath(String classpathfolder) throws IOException {
		// TODO Auto-generated method stub
		
		List<String> classpath = new ArrayList<String>();
		//classpath.add("/arquillian-core/bin/");
		
		Path start = Paths.get(classpathfolder);
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            List<String> collect = stream
                .map(String::valueOf)
                .sorted()
                .collect(Collectors.toList());
            for(String f: collect){
            	if (f.endsWith(".jar"))
            		classpath.add(f);
            }
        }
        
		String property = System.getProperty("java.class.path", ".");
		//String[] classpathArr = classpath.toArray(new String[classpath.size()]);
		//return classpathArr;
		return classpath;

	}
	

	public static String[] getSourcePath() {
		// TODO Auto-generated method stub
		return new String[] { "." };
	}

	public static String[] getSourcePathNew(String filepath) {
		// TODO Auto-generated method stub
		int srcindex=filepath.indexOf(AnalyzeFilesClient.sourcepathfinder); 
		String[] sourcepath=new String[] {filepath.substring(0, srcindex)+AnalyzeFilesClient.sourcepathfinder};
		return sourcepath;  
		
		
		
	}
	
	public static String getEncoding() {
		// TODO Auto-generated method stub
		return UTF_8;
	}

	public static String getLineSeparator() {
		return System.getProperty("line.separator", "\n");
	}
}
