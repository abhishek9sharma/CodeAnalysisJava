package astparsing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import Client.AnalyzeFilesClient;

public class BaselineBugramParser extends ASTGenerator implements IJavaCodeParser{
	
	
	@Override
	public void processFile(String filepath) {
		// TODO Auto-generated method stubi
		System.out.println("Generating BugGram format  AST FOR " + filepath);
		JavaFileInfo jfileinfo = new JavaFileInfo(filepath);		
		this.VisitPattern(jfileinfo);
		//this.VisitMethods();
		//this.visitBugramFormat();
		
		
	}

	public void VisitPattern(JavaFileInfo jfileinfo)
	{
		MethodDeclarationNodeVisitor mdv = new MethodDeclarationNodeVisitor();
		
		try
		{
			this.SetCompilationUnitJavaFile(jfileinfo);		
			
			
			this.astcompilationUnit.accept(mdv);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("Compilation Unit is not set or invalid");
			e.printStackTrace();
		}
		
		for (MethodDeclaration m : mdv.getMethodList()) 
		{
			
			String trace_elmt="<begin method>,";
			//System.out.println(trace_elmt);
			
	    	
			//System.out.println(m.getName());
			BaselineBugramVisitor bv=new BaselineBugramVisitor();
			bv.setTrace(trace_elmt);
			
			
			m.accept(bv);
			
			trace_elmt="<end method>";
			//System.out.println(trace_elmt);
			bv.addTOTrace(trace_elmt);
			
			System.out.println("");
			//System.out.println(bv.getTrace());
			if(bv.getTrace().contains("UNRESOLVED"))
					{
						WriteTraceToFIle(bv.getTrace(), "UNRESOLVED_"+jfileinfo.opfilename+"."+m.getName());
					}
			else
			{
				WriteTraceToFIle(bv.getTrace(), jfileinfo.opfilename+"."+m.getName());
				
			}
			bv.setTrace(null);
		}
	}
	
	public void WriteTraceToFIle(String trace, String filename)
	{
		try {
			File file = new File(AnalyzeFilesClient.ASTTraceFolder+File.separator+filename);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(trace);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
	
	


	


