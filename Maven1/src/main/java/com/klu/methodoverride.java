//Write a Java program to demonstrate method overriding by creating a superclass Bank with a method to return interest rate. 
		//Override this method in subclasses such as SBI and HDFC.

package com.klu;

class Bank{
	double interest() {
		return 0.0;
	}
}

	class SBI extends Bank{
		double interest() {
			return 3.5;
			
		}
	}
class HDFC extends Bank{
	double interest() {
		return 6.5;
		
	}
}

public class methodoverride {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank b;
		b= new SBI();
		System.out.println("SBI interest: "+b.interest() + "%");
		
		b= new HDFC();
		System.out.println("SBI interest: "+b.interest() + "%");
	}

}
