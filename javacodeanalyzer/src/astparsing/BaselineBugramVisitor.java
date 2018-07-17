package astparsing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.internal.compiler.ast.CaseStatement;
import org.eclipse.jdt.internal.compiler.ast.ConstructorDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;



public class 	BaselineBugramVisitor extends ASTVisitor
{
		
	   private String complationunittrace;
	   private boolean unresolvedelmt=false;

	   
	   public String getTrace()
	   {
		   return this.complationunittrace;
	   }

	   public boolean TraceHasUnresolvedElements()
	   {
		return unresolvedelmt;
	   }
	   
	   public void setTrace(String trace_elemt)
	   {
		
			   this.complationunittrace=trace_elemt+"\n";
		   
	   }
	   
	   public void addTOTrace(String trace_elemt)
	   {
			System.out.println(trace_elemt);
			   System.out.println(trace_elemt);
			   if(trace_elemt.contains("::UNRESOLVEBLK::"))
			   {
				this.unresolvedelmt=true;
				String[] traceArray = trace_elemt.split("::UNRESOLVEBLK::");
				this.complationunittrace+=traceArray[traceArray.length-1]+",\n";
			   }
			   else
			   {
				   this.complationunittrace+=trace_elemt+"\n";
			   }
			   
		   
	   }
	   
	   public boolean visit( ClassInstanceCreation node) 
		{
		   try 
		   {
		    Type instancetype=node.getType();	    
	    	ITypeBinding typeBinding =node.resolveTypeBinding();
	    	ITypeBinding superClass = typeBinding.getSuperclass();
	    	String superclassName = typeBinding.getBinaryName();	    	
	    	String trace_elemt=superclassName+"."+instancetype+",";
	    	
	    	this.addTOTrace(trace_elemt);
		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  class instance::UNRESOLVEBLK::  "+ node.toString();
			   
	    	   this.addTOTrace(trace_elemt);
		   }
		    return super.visit(node);
	    }

	   public boolean visit(ConstructorInvocation node)
	   {
		   try 
		   {
			String constname=node.toString();
		 	//IMethodBinding  methodbinding=node.resolveConstructorBinding()();
		 	//ITypeBinding typebinding = node.getExpression().resolveTypeBinding();
		 	//String qualfiiedname = typebinding.getQualifiedName();
		 	//String trace_elemt=qualfiiedname+"."+methodname+",";
			String trace_elemt="CONST"+"."+constname+",";
	    	
	    	this.addTOTrace(trace_elemt);

		   }
		    catch (Exception e) 
			   {
				// TODO: handle exception
		    	String trace_elemt="::UNRESOLVEBLK::  constructor invocation::UNRESOLVEBLK:: "+ node.toString();				
	    		this.addTOTrace(trace_elemt);
			   }
		 
		     return super.visit(node);
	   }
	   
   	   public boolean visit(MethodInvocation node) 
		{
		   try 
		   {
			node.getExpression().accept(this);
			String methodname=node.getName().toString();  
		 	IMethodBinding  methodbinding=node.resolveMethodBinding();
		 	ITypeBinding typebinding = node.getExpression().resolveTypeBinding();
		 	String qualfiiedname = typebinding.getQualifiedName();
		 	String trace_elemt=qualfiiedname+"."+methodname+",";
	    	
	    	this.addTOTrace(trace_elemt);

		   }
		    catch (Exception e) 
			   {
				// TODO: handle exception
		    	String trace_elemt="::UNRESOLVEBLK::  method invocation::UNRESOLVEBLK:: "+ node.getName().toString();
				
	    		this.addTOTrace(trace_elemt);
			   }
		 
		     //return super.visit(node);
		     return false;
		}
	   
	   	 
	   public boolean visit(CatchClause node) 
	   {
		   try 
		   {
		 	SingleVariableDeclaration excp=node.getException();
		 	IVariableBinding vbinding=excp.resolveBinding();
		 	String resolvedtypeexcp = vbinding.getType().getQualifiedName();   	
		 	String trace_elemt="<CATCH "+resolvedtypeexcp+">,";	 	 
	    	
	    	this.addTOTrace(trace_elemt);

		   }
		    catch (Exception e) 
			   {
				// TODO: handle exception
		    	String trace_elemt="::UNRESOLVEBLK::  catch clause::UNRESOLVEBLK::  "+ node.toString();
				
	    		this.addTOTrace(trace_elemt);
			   }
		   return super.visit(node);
	   }

 
	   public boolean visit(ReturnStatement node) 
		{
		   try {
			   String trace_elemt="<RETURN>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
			 	   catch (Exception e) 
				   {
			 		  String trace_elemt="::UNRESOLVEBLK::  return clause::UNRESOLVEBLK::  "+ node.toString();
					   
	    			this.addTOTrace(trace_elemt);
				   }
			 	  return super.visit(node);
				 	 
		 }
 	   
	   public boolean visit(TryStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<TRY>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  try statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   public boolean visit(IfStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<IF>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  if statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	 
	   	   
	   public boolean visit(WhileStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<WHILE>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  while statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   
	   public boolean visit(DoStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<DO>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  DO statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   
	   public boolean visit(ForStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<FOR>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  FOR  statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}

	   
	   public boolean visit(SwitchStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<SWITCH>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  SWITCH statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   public boolean visit(CaseStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<CASE>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  CASE statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   //return super.visit(node);
		   return true;
	   	}
	   
	   public boolean visit(ForeachStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<FOREACH>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  FOREACH statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return true;//super.visit(node);
	   	}
	   
	   public boolean visit(AssertStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<ASSERT>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  ASSERT statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   public boolean visit(SynchronizedStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<SYN>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  SYN statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   
	   public boolean visit(BreakStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<BREAK>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  BREAK statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   
	   public boolean visit(ContinueStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<CONTINUE>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  CONTINUE statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   return super.visit(node);
	   	}
	   
	   	   
	   public void endVisit(SynchronizedStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<END SYN>,";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  ENDSYN statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}
	   
	   	   
	   public void endVisit(TryStatement node)
	   {
		   try 
		   {
			   String trace_elemt="<END TRY>,";	    	
			   
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end try::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
	   }
	   
	   public void endVisit(IfStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<END IF>,";
		    	
		    	this.addTOTrace(trace_elemt);

			  
		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end if statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}
	 
	   
	   
	   public void endVisit(WhileStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<END WHILE>,";
		    	
		    	this.addTOTrace(trace_elemt);

			  
		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end while statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}
	   
	   
	   public void endVisit(DoStatement node) 
	   {
		   try 
		   {
			   String trace_elemt="<END DO>,";
		    	
		    	this.addTOTrace(trace_elemt);

			  
		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end do statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}
	   
	   
	   public void endVisit(ForStatement node) 

	   {
		   try 
		   {
			   String trace_elemt="<END FOR>";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end for statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}
	   public void endVisit(SwitchStatement node) 

	   {
		   try 
		   {
			   String trace_elemt="<END SWITCH>";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end switch statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}
	   
	   public void endVisit(ForeachStatement node) 

	   {
		   try 
		   {
			   String trace_elemt="<END FOREACH>";
		    	
		    	this.addTOTrace(trace_elemt);

		   }
		   catch (Exception e) 
		   {
			// TODO: handle exception
			   String trace_elemt="::UNRESOLVEBLK::  end foreach statement::UNRESOLVEBLK::  "+ node.toString();
			   
	    	this.addTOTrace(trace_elemt);
		   }
		   
	   	}

}
