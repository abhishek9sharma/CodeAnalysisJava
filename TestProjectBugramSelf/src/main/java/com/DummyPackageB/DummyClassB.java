package com.DummyPackageB;

public class DummyClassB {
	
	private int member1;
	private int member2;

	
	public DummyClassB() 
	{
		this.member1=0;
		this.member2=0;
	}
	
	
	public int  GetSum(int number1, int number2) 
	{
		this.member1=number1;
		this.member2=number2;
		return this.member1+this.member2;
		
	}
	
	
	
}
