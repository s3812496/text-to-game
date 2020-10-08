package proto;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JTextArea;

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
		tes.viewTable(c, 1);
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Next?");
	    String nex = myObj.nextLine();  // Read user input
	    if (nex.equalsIgnoreCase("next")) {
	    	tes.viewTable(c, 2);
	    }
	    System.out.println("Left or Right");
	    String chap = myObj.nextLine();
	    if (chap.equalsIgnoreCase("left"))
	    	tes.viewTable(c, 3);
	    else if (chap.equalsIgnoreCase("right"))
	    	tes.viewTable(c, 4);
	    tes.viewTable(c, 5);
	    
		

	}
	
	
	
	
	 @SuppressWarnings("unused")
	private class CompletionTask implements Runnable {
			 JTextArea textArea = new JTextArea(
			    "This is an editable JTextArea. " +
			    "A text area is a \"plain\" text component, " +
			    "which means that although it can display text " +
			    "in any font, all of the text is in the same font.");
		
	        String completion;
	        int position;
	        
	        public void run() {
	            textArea.insert(completion, position);
	            textArea.setCaretPosition(position + completion.length());
	            textArea.moveCaretPosition(position);
	        }
	    }

}
