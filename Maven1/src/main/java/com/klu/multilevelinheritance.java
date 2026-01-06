//Write a Java program to demonstrate multilevel inheritance by creating classes Vehicle, Car, and ElectricCar. 
//Each class should have its own method. Show how methods of all parent classes are accessible by the child class.
package com.klu;

class Vehicle{
	void horn() {
		System.out.println("Vehicle started horn");
		
	}
}
	class Car extends Vehicle{
		void drive() {
			System.out.println("Learning driving");
		}
	}
	
class ElectricCar extends Car{
	void charge() {
		System.out.println("Electric car is charging");
		
	}
}
public class multilevelinheritance {
	public static void main(String args[]) {
		ElectricCar ec= new ElectricCar();
		ec.horn();
		ec.drive();
		ec.charge();
	
	}
}
