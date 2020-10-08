package proto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import java.util.*;


public class Author {

	public static void main(String[] args) throws SQLException {
		
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c= DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Sqldb Connected");
		} catch(Exception e) {
			System.out.println(e);
		}
		
		Author tes = new Author();
		tes.createbook(tes, c);

	}
	
	
	public void createbook(Author a, Connection c) throws SQLException{
		/*
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter Chapter name:");
	    String chapName = myObj.nextLine();  // Read user input
	    System.out.println("Enter Chapter");
	    String chap = myObj.nextLine();
	    
		System.out.println("Name: " + chapName + ", Chapter: " + chap);
		*/
		//a.createTable(c);
		//String chapName= "Toodloo";
		//String chap = "Bippidee boppidee";
		//a.insert(c, chapName, chap);
		//a.viewTable(c);
	}
	
	public void createTable(Connection c) throws SQLException {
	    String createString =
	        "CREATE TABLE " +
	        "TEXTTOGAME " +
	        "(CHAP_NAME VARCHAR(32) not NULL, " +
	        "CHAP_ID integer not NULL, " +
	        "PAR_ID integer NULL, " +
	        "BODY VARCHAR(250) not NULL, " + 
	        "PRIMARY KEY (CHAP_ID))";

	    Statement stmt = null;
	    try {
	        stmt = c.createStatement();
	        stmt.executeUpdate(createString);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	
	public void insert(Connection c, String name, String chap) {
		int id = 1;
		
		//insert into TEXTTOGAME values('Chapter 1', id, NULL, 'venus is big')
		String query = "insert into TEXTTOGAME values('" + name + "', " + id + ", NULL, '" + chap + "')";
		
		 Statement stmt = null;
		    try {
		        stmt = c.createStatement();
		        stmt.executeUpdate(query);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }    
		
	}
	
	
	public void viewTable(Connection c, int chapid) throws SQLException {
	
		    String query = "select * " +
		                   "from TEXTTOGAME " +
		                   "where CHAP_ID = " + chapid;
	
		    try (Statement stmt = c.createStatement()) {
	
		        ResultSet rs = stmt.executeQuery(query);
	
		        while (rs.next()) {
		            String studentnum = rs.getString("BODY");
		            System.out.println(studentnum);
		        }
		    } catch (SQLException e) {
		        System.out.println(e);
		    }
		}
}
