package com.ankit.jdbc;

public class Student 
{
	public String Id;
	public String Name;
	
	public Student(String Id, String Name)
	{
		super();
		this.Id = Id; 	 	
		this.Name = Name;
	}
	@Override
	public String toString() {
		return "Student [Id= "+ Id + ", Name=" + Name + "]";
	}
}
