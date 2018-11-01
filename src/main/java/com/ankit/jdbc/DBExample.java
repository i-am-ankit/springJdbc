package com.ankit.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/*public class DBExample 
{
	public static void main(String[] args)
	{
		try 
		{
			Class.forName("org.hsqldb.jdbc.JDBCDrive");
		}
		catch(Exception e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
			//return;
		}
	}
	//Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb","SA","");
	//Connection c = DriverManager.getConnection("", "SA", "");
	Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/db", "SA", "");
}*/







public class DBExample {

    static Connection con;
    
    // What is JDBC ?? Java Database Connectivity API (Application Programming Interface)
    // ---> https://docs.oracle.com/javase/tutorial/jdbc/overview/
    // JDBC is an API that enables us to talk to many different types of data stores.
    // anything under java.sql is part of JDBC
    // jdbc protocol - hsqldb type - file type - path to resource
    static String connectionString = "jdbc:hsqldb:file:db-data/mydatabase";
    
	public static void main(String[] args) throws Exception {
	    
		String createStudent = readToString("sql/Student.sql");
		String populateStudent = readToString("sql/populate-Student.sql");
		
		System.out.println("Attempting to create Student DB ... ");
		
		
		// this loads the DB driver
		// explained here: http://stackoverflow.com/questions/5992126/loading-jdbc-driver
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver.class");
		} catch (ClassNotFoundException e) {
			throw e;
		}
		
		try {
			// will create DB if does not exist
			// "SA" is default user with hypersql
			con = DriverManager.getConnection(connectionString, "SA", "");
			
			// create table
			con.createStatement()
					.executeUpdate(createStudent);
			
			// add contacts
			con.createStatement()
					.executeUpdate(populateStudent);
			
			// select everything
			PreparedStatement pst = con.prepareStatement("select * from Student");
	        pst.clearParameters();
	        ResultSet rs = pst.executeQuery();
	        
	        /*List<Student> student = new ArrayList<>();
	        while(rs.next())
	        {
	        	student.add(new Student
	        			(
	        			rs.getString(1), 
	        			rs.getString(2)
	        			));        				
	        }
	        for(Student c : student) {
	        	System.out.println(c);
	        }*/
	        
		} catch (SQLException e) {
			throw e;
		} finally {
			con.close();
		}
	}
	
	public static String readToString(String fname) throws Exception {
		File file = new File(fname);
		String string = FileUtils.readFileToString(file, "utf-8");
		return string;
	}
	
	
}