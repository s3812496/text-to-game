package proto;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Scanner;


    


//import java.util.*;


public class Author{
	

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
		
		
	        //Scanner in = new Scanner(System.in);
	       //String s = in.nextLine();
	        //a.viewnextchap(c,1,1);
	        //System.out.println( "" +s);
	        //int r = in.nextInt();
	        //System.out.println("You entered integer "+r);
	        //float b = in.nextFloat();
	        //System.out.println("You entered float "+b);
	    a.insert(c, "While the cows lie", "Cows are big and cannot run", 2, 2, 0);
		
		
		//a.createTable(c);
		//String chapName= "Toodloo";
		//String chap = "Bippidee boppidee";
		//a.insert(c, chapName, chap);
		//a.viewnextchap(c,2);
		//a.getNextChap(c, 2);
	}
	
	public void createTable(Connection c) throws SQLException {
	    String createString =
	        "CREATE TABLE " +
	        "Text-to-game " +
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
	
	
	public void insert(Connection c, String name, String chap, int bookid, int id, int parid) {
		
		
		//insert into TEXTTOGAME values('Chapter 1', id, NULL, 'venus is big')
		String query = "insert into Text-to-game values('" + name + "', " + id + ", '" + chap + "', NULL)";
		
		 Statement stmt = null;
		    try {
		        stmt = c.createStatement();
		        stmt.executeUpdate(query);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }    
		
	}
	
	public void viewnextchap(Connection c, int chapid, int bookid) throws SQLException {
		
	    String query = "select * " +
	                   "from Chapter_registry " +
	                   "where CHAP_ID = " + chapid +
	                   " AND BOOK_ID = " + bookid;

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
	
	
	public void getchap(Connection c, int chapid) throws SQLException {
	
		    String query = "select * " +
		                   "from Text-To-Game " +
		                   "where PAR_ID = " + chapid;
	
		    try (Statement stmt = c.createStatement()) {
	
		        ResultSet rs = stmt.executeQuery(query);
	
		        while (rs.next()) {
		            String studentnum = rs.getString("CHAP_NAME");
		            System.out.println(studentnum);
		        }
		    } catch (SQLException e) {
		        System.out.println(e);
		    }
		}
	
	public void getImage(Connection c,int chapid) throws Exception{
		String query =
				"select IMAGE " +
		        "from Text-To-Game " +
		        "where CHAP_ID = " + chapid;

		    
		    try (Statement stmt = c.createStatement()) {
		    	
		        ResultSet rs = stmt.executeQuery(query);
	
		        while (rs.next()) {
		            String studentnum = rs.getString("IMAGE");
		            System.out.println(studentnum);
		        }
		    } catch (SQLException e) {
		        System.out.println(e);
		    }
	} 
	
	public void getNextChap(Connection c, int chapid) {
		
		/*
		select *, COUNT(*)
		from Chapter_registry
		inner join ParID
		on Chapter_registry.CHAP_ID = ParID.CHAP_ID
		where Chapter_registry.BOOK_ID = 1
		AND ParID.PAR_ID = 2
		*/
		
		String query1 = "select *" +
                "from Chapter_registry " +
                "inner join ParID "
                + "on Chapter_registry.CHAP_ID = ParID.CHAP_ID "
                + "where Chapter_registry.BOOK_ID = 1 "
                + "AND ParID.PAR_ID = " + chapid;
		
		String query2 = "select COUNT(*) "
				+ "from Chapter_registry "
				+ "inner join ParID "
				+ "on Chapter_registry.CHAP_ID = ParID.CHAP_ID "
				+ "where Chapter_registry.BOOK_ID = 1 "
				+ "AND ParID.PAR_ID = " + chapid;
		
		

		 try (Statement stmt = c.createStatement()) {
		
		     ResultSet rs1 = stmt.executeQuery(query1);
		     
		
		     while (rs1.next()) {
		         String bod = rs1.getString("CHAP_NAME");
		         System.out.println(bod);
		     }
		     ResultSet rs2 = stmt.executeQuery(query2);
		     while (rs2.next()) {
		         String bod = rs2.getString("COUNT(*)");
		         System.out.println(bod);
		     }
		     
		 } catch (SQLException e) {
		     System.out.println(e);
		 }
		 
		
	}
	  
	
}
