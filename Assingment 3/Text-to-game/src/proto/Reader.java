package proto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;



public class Reader {

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
		tes.viewnextchap(c, 1, 1);
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Next?");
	    String nex = myObj.nextLine();  // Read user input
	    if (nex.equalsIgnoreCase("next")) {
	    	tes.viewnextchap(c, 2,1);
	    }
	    System.out.println("Left or Right");
	    String chap = myObj.nextLine();
	    if (chap.equalsIgnoreCase("left"))
	    	tes.viewnextchap(c, 3,1);
	    else if (chap.equalsIgnoreCase("right"))
	    	tes.viewnextchap(c, 4, 1);
	    tes.viewnextchap(c, 5,1 );
	    
	}
}
