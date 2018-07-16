package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

	private File filepath;
	public List<String> linesinfile;
	
	public ReadFromFile(String filepath) 
	{
		this.filepath=new File(filepath);
		this.ReadLinesIntoArray();
				}

	public void ReadLinesIntoArray() 
	{
		this.linesinfile = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filepath)))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                linesinfile.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
		
	}
	

}
