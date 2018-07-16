package Flow;

import com.DummyPackageA.DummyClassA;
import com.DummyPackageB.DummyClassB;

public class TestFlow {
	
	
	public static void TestSwitchCase(int assertest) 
	{
	
	    
		switch (assertest) 
	    	{
	      
			    case 1: com.AssertTestA.AssertTestSnippet astestA=new com.AssertTestA.AssertTestSnippet();
			    		break;
			    
			    case 2: com.AssertTestB.AssertTestSnippet astestB=new com.AssertTestB.AssertTestSnippet();
			    		break;
			    		
			    default: String astestC = "Invalid Case Selected";
			    		 break;
	    	}
	}
	
	public static Object TestClassConstuctors(String className)  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
			System.out.println("Inside CONSTRUTOR CALL");	
		    Class classTemp = Class.forName(className);
		    //Object obj=DummyClassB.newInstance();
		    Object obj=null;
			//obj = classTemp.getConstructor();
			obj = classTemp.newInstance();
		    return obj;
	}
	
	public static int TestForLoop(DummyClassB obj,int a , int b)
	{
		int n=a-b;
		int sum=0;
		 for (int i=0;i<n;i++)
		 {
			 System.out.println("Inside for Loop");							
			 sum+=obj.GetSum(a,b);					
		 }
		 return sum;
	}

	public static void TestWhileLoop(int sum)
	{
		
		 while(true)
		 {
			 System.out.println("Inside WHile Loop");
			 sum=sum-1;
			 if(sum<10)
			 {	
				 break;
			 }
			 else
			 {
				 continue;
			 }
		 }
	}

	
	public static void TestDoWhileLoop(int sum)

	
	{
		 do
		 {
			 System.out.println("Inside WHile Loop");
			 sum=sum-1;
			 if(sum<10)
			 {	
				 break;
			 }
			 else
			 {
				 continue;
			 }
		 }
		 while(true);
	}

	public static void TestSynhcnronizeA()
	{
		System.out.println("Inside synchrnonize a");	
		
			   com.SynchronizedTestA.Sender snd = new com.SynchronizedTestA.Sender();
			   com.SynchronizedTestA.ThreadedSend S1 = new com.SynchronizedTestA.ThreadedSend( " Hi " , snd );
			   com.SynchronizedTestA.ThreadedSend S2 =
	               new com.SynchronizedTestA.ThreadedSend( " Bye " , snd );
	    
	           // Start two threads of ThreadedSend type
	           S1.start();
	           S2.start();
	    
	           // wait for threads to end
	           try
	           {
	               S1.join();
	               S2.join();
	           }
	           catch(Exception e)
	           {
	               System.out.println("Interrupted");
	           }
	       }
	
	
	public static void TestSynhcnronizeB()
	{
			  System.out.println("Inside synchrnonize b");	
		
		
			  com.SynchronizedTestB.Sender snd = new com.SynchronizedTestB.Sender();
			  com.SynchronizedTestB.ThreadedSend S1 = new com.SynchronizedTestB.ThreadedSend( " Hi " , snd );
			  com.SynchronizedTestB.ThreadedSend S2 = new com.SynchronizedTestB.ThreadedSend( " Bye " , snd );
	    
	           // Start two threads of ThreadedSend type
	           S1.start();
	           S2.start();
	    
	           // wait for threads to end
	           try
	           {
	               S1.join();
	               S2.join();
	           }
	           catch(Exception e)
	           {
	               System.out.println("Interrupted");
	           }
	       }
	
	public static void main(String args[])
	{
		int a=13;
		int b=12;
	
		try {
				if(a>b)
				{
					 System.out.println("Inside IF");							
						
					 int n=a-b;
					 DummyClassA classA=new DummyClassA(a, b);
					 int sum=0;
					 
					 for (int i=0;i<n;i++)
					 {
						 System.out.println("Inside for Loop");							
						 sum+=classA.GetSum();		
						
					 }			
	
					 TestWhileLoop(sum);
					 TestSwitchCase(1);	 
					 TestSynhcnronizeA();
						
					
				}
				else
				{
					System.out.println("Inside ELSE");							
					
					 int n=a-b;
					 DummyClassB B=(DummyClassB) TestClassConstuctors("com.DummyPackageB.DummyClassB");
					 int sum=TestForLoop(B, a, b);
					 TestDoWhileLoop(sum);
					 TestSwitchCase(2);	 
					 TestSynhcnronizeB();
					
				}
				
		}
	catch(Exception ex)
	{
		System.out.println("Exception caught");
		System.out.println(ex);
    }
	
	
	finally
	{
		System.out.println("Finally Block Executed ");
	    	
	}
	
		
      
	
			
	}		
	}



