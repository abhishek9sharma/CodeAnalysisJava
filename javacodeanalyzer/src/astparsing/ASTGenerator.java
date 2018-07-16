package astparsing;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.felix.resolver.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import astparsing.JavaFileInfo;
import astparsing.*;
import Client.AnalyzeFilesClient;


public class ASTGenerator {
	CompilationUnit astcompilationUnit;
	
	public void SetCompilationUnitJavaFile(JavaFileInfo jfileinfo) {
		String codeString;
		//JavaFileInfo jfileinfo = new JavaFileInfo(javafilepath);
		codeString = jfileinfo.GetCodeString();
		
		List<String> localclasspath=AnalyzeFilesClient.classpathjars;
		localclasspath.add(jfileinfo.binfolder);
		String[] classpathArr = localclasspath.toArray(new String[localclasspath.size()]);
		
		
		ASTParser astparser = ASTParser.newParser(AST.JLS10);
		astparser.setBindingsRecovery(true);
		astparser.setStatementsRecovery(true);
		astparser.setResolveBindings(true);
		
		
		
		try {
			// astparser.setEnvironment(Environments.getClassPath(),Environments.getSourcePathNew(javafilepath),null,
			// true);
			//astparser.setEnvironment(classpathArr,Environments.getSourcePathNew(javafilepath),null, true);
			astparser.setEnvironment(classpathArr,Environments.getSourcePathNew(jfileinfo.filepath),null, true);
			
			//astparser.setEnvironment(null, null, null, true);

		} 
		catch (Exception ex) 
		{
			//System.out.println(" Exception Occured while setting Envirofnment For  file " + javafilepath);
			System.out.println(" Exception Occured while setting Envirofnment For  file " + jfileinfo.filepath);
					
		}

		// System.out.println(Environments.getSourcePath());

		Map options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8); // or newer // version
		astparser.setCompilerOptions(options);

		astparser.setUnitName(jfileinfo.filepath);
		astparser.setSource(codeString.toCharArray());
		astparser.setKind(ASTParser.K_COMPILATION_UNIT);

		//this.astcompilationUnit = (CompilationUnit) astparser.createAST(null);
		this.astcompilationUnit = (CompilationUnit) astparser.createAST(new NullProgressMonitor());
		// Setting up parameters
		// visit package node
		//return this.astUnit;

	}

	public void VisitPattern()
	{
		this.astcompilationUnit.accept(new ASTVisitor() 
		{
		})
		;
	}
	
	
	/*
	public void VisitMethods()
	{
		this.methodNodeVisitor = new MethodNodeVisitor();
		try
		{
			
		this.astcompilationUnit.accept(methodNodeVisitor);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("Compilation Unit is not set or invalid");
			e.printStackTrace();
		}
	}*/
	
	
}
