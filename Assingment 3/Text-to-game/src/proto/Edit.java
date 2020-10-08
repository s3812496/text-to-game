package proto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Edit {
	
	public Connection c;
	
	public Edit(Connection c) {
		this.c = c;
	}
	
	public void updateTable() throws SQLException {
		String newBody = "CHEESE";
		int id = 000001;
		String update = "UPDATE TEXTTOGAME" +
						"SET CHAP_BODY =" + newBody + 
						"WHERE CHAP_ID = " + id;
		
		Statement stmt = null;
		try {
	        stmt = c.createStatement();
	        stmt.executeUpdate(update);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	
	

	public void createTable(Connection c) throws SQLException {
	    String createString =
	        "create table " +
	        "TEXTTOGAME " +
	        "(CHAP_NAME varchar(32) NOT NULL, " +
	        "CHAP_ID integer NOT NULL, " +
	        "PAR_ID integer, " +
	        "CHAP_BODY varchar(250) NOT NULL" + 
	        "PRIMARY KEY (CHAP_ID)";

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
		int id = 000001;
		String query = "insert into TEXTTOGAME values('" + name + "', " + id + ", NULL, '" + chap + "')";
		
		 Statement stmt = null;
		    try {
		        stmt = c.createStatement();
		        stmt.executeUpdate(query);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }    
		
	}
	
	
	public void viewTable(Connection c) throws SQLException {
	
		    String query = "select *" +
		                   "from TEXTTOGAME" +
		                   "where CHAP_ID = 000001";
	
		    try (Statement stmt = c.createStatement()) {
	
		        ResultSet rs = stmt.executeQuery(query);
	
		        while (rs.next()) {
		            String name = rs.getString("CHAP_NAME");
		            int id = rs.getInt("CHAP_ID");
		            String studentnum = rs.getString("CHAP_BODY");
		            System.out.println(name + ", " + id + ", " + studentnum);
		        }
		    } catch (SQLException e) {
		        System.out.println(e);
		    }
		}
}
