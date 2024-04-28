import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class A extends JFrame {
	
	
	 
	static String Id,cod,fnam,lnam,ag,sx,ed,quest,cho1,cho2,rght;
	static Connection c = null;
	static ResultSet rs = null;
	static ResultSet rs2 = null;
	static Statement stmnt = null;
	static Statement stmnt2 = null;
	static String query = null;
	static String col_nam;
	static String ch;
	static int chi;
	static int count = 0;
	static int count2 = 0;
	static int i = 0;
	static String question;
	static String[] answer = new String[11];
	static String res;
	//===========================================================================================

	public A() {

		super();

		

		try {

			// Get a connection to database (MySQL)

			 Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
	            c = DriverManager.getConnection( "jdbc:odbc:Exams", "", "" );
	            stmnt = c.createStatement();
			// create a statement, execute your query		

			stmnt = c.createStatement();

			ResultSet rs = stmnt.executeQuery("SELECT * from [info$]");

			// Convert the result set into an array of Objects
			Object[][] rows = getObjects(rs);			

			// These are the column headings displayed in the JTable

			String[] headings = { "ID", "LastName"," FirstName", "Age", "Sex", "Education"};

			// Initialize a JTable with the rows of objects and column headings

			JTable table = new JTable(rows, headings);

			// Add the table to a JScrollPane to make it display nicely

			JScrollPane scrollPane = new JScrollPane(table);

			// Add the scroll pane to the JFrame's container

			getContentPane().add(scrollPane);

				

		} catch (SQLException sqle) {

			sqle.printStackTrace();

		} catch (Exception e) {

			// Other exceptions should be properly caught, but this'll do for an example

			e.printStackTrace();

		}

		

		// Display the JFrame, and then make it look nice

		show();

		pack();

	}

	

	

	public Object[][] getObjects(ResultSet rs) throws SQLException {

		// Find out how many columns are in the result set

		ResultSetMetaData metaData = rs.getMetaData();

		final int colCount = metaData.getColumnCount();

	

		ArrayList rows = new ArrayList();

		Object[] row = null;

		

		

		while (rs.next()) {

			// for each row in the result set, put every column into a temporary Object array

			row = new Object[colCount];

			for (int a = 0; a < colCount; a++) 

					row[a] = rs.getObject(a+1);

					

			// add the temporary Object array to the array list

			rows.add(row);

		}

	

		// convert the array list to objects
		//Second();
		return (Object[][])rows.toArray(new Object[0][0]);
		
		
	}

//----------------------------------------------------------------------------------------------------
	
	public class B extends  A
	{

	public void A() {
		try {

			ResultSet rs = stmnt.executeQuery("SELECT * from [Quest$]");

			// Convert the result set into an array of Objects
			Object[][] rows = getObjects(rs);			

			// These are the column headings displayed in the JTable

			String[] headings = { "Questions","CH1", "CH2", "RGHT"};

			// Initialize a JTable with the rows of objects and column headings

			JTable table = new JTable(rows, headings);

			// Add the table to a JScrollPane to make it display nicely

			JScrollPane scrollPane = new JScrollPane(table);

			// Add the scroll pane to the JFrame's container

			getContentPane().add(scrollPane);

				

		} catch (SQLException sqle) {

			sqle.printStackTrace();

		} catch (Exception e) {

			// Other exceptions should be properly caught, but this'll do for an example

			e.printStackTrace();

		}

		

		// Display the JFrame, and then make it look nice

		show();

		pack();

	}

	

	

	public Object[][] getObjects(ResultSet rs) throws SQLException {

		// Find out how many columns are in the result set

		ResultSetMetaData metaData = rs.getMetaData();

		final int colCount = metaData.getColumnCount();

	

		ArrayList rows = new ArrayList();

		Object[] row = null;

		

		

		while (rs.next()) {

			// for each row in the result set, put every column into a temporary Object array

			row = new Object[colCount];

			for (int a = 0; a < colCount; a++) 

					row[a] = rs.getObject(a+1);

					

			// add the temporary Object array to the array list

			rows.add(row);

		}

	

		// convert the array list to objects

		return (Object[][])rows.toArray(new Object[0][0]);

		
		
	}
		
}
	
	
	
	
	
	
	
	
	
	
	public static void main( String [] args ) throws ClassNotFoundException, SQLException 
	{
		A obja = new A();
	
		Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
        c = DriverManager.getConnection( "jdbc:odbc:Exams", "","");
        stmnt = c.createStatement();
        count();
        Login();
        System.out.println(Id+cod);
        
        if(cod.isEmpty())
        {
        	new_user();
        	Test();
		}
        else
        {
        	admin_menu();
        }
        
                
		System.out.println("done");
		System.out.println(fnam);
		new A();
		JOptionPane.showMessageDialog(null, "Displayed!");
		c.close();
		stmnt.close();
		System.exit(0);
        
        
	}
	private static void Test() throws SQLException {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Logging in as" +fnam+lnam);
		//while(i<10)
		get_que();
		
		
		
		
	}
	private static void get_que() throws SQLException {
		// TODO Auto-generated method stub
		//System.out.println("hear");
		stmnt.executeQuery("select questions,ch1,ch2 from [Quest$]" );
		rs = stmnt.getResultSet();
		System.out.println("hear");
		while(rs.next())
		{
			//question = rs.getString( "questions" );
			answer[i] = JOptionPane.showInputDialog(null,(rs.getObject(1)+"\n\n1."+(rs.getObject(2))+"\n\n2."+(rs.getObject(3))));
			if(answer[i]==null)
			{
				
				JOptionPane.showMessageDialog(null, "exit");
				exit();
			}
			//stmnt.executeUpdate("update [quest$] set INPUT = '"+answer+"' where ID = '"+i+"'");
			i++;
		}
		store();
		//stmnt.executeUpdate("update [quest$] set INPUT = '"+answer+"' where ID = '"+i+"'");
	}
	
	private static void store() throws SQLException {
		// TODO Auto-generated method stub
		rs = stmnt.getResultSet();
		for(i=0;i<=10;i++)
		stmnt.executeUpdate("update [quest$] set INPUT = '"+answer[i]+"' where ID = '"+(i+1)+"'");
		
		JOptionPane.showMessageDialog(null, "Your IQ is less then 1 because you took time to take this test! ");
		exit();
				
		
	}





	private static void count() throws SQLException {
		// TODO Auto-generated method stub
		stmnt.executeQuery("select LastName from [Info$]" );
		rs = stmnt.getResultSet();
		while( rs.next() )
		{
			count++;
		}
		stmnt.executeQuery("select Questions from [quest$]");
		rs = stmnt.getResultSet();
		while( rs.next() )
		{
			count2++;
		}
		System.out.println(count+1);
		System.out.println(count2+1);
	}
	private static void admin_menu() throws SQLException { /////////////////////////////////////////
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Logging In as Admin "+fnam);
		String ch = JOptionPane.showInputDialog(null,"Select Choice\n\n1.User Records\n2.Question Bank\n");
		if(ch==null)
		{
			JOptionPane.showMessageDialog(null, "Exiting");
			exit();
		}
		chi = Integer.parseInt(ch);
		if(chi==1)
			INFO();
		else if(chi==2)
			admin_Test_record();
		else
		{
			JOptionPane.showMessageDialog(null, "Incorrect choice!!");
			admin_menu();
		}
		new A();
		JOptionPane.showMessageDialog(null, "Displayed!");
		admin_menu();	
		
	}
	private static void admin_Test_record() throws SQLException {
		// TODO Auto-generated method stub
		String ch = JOptionPane.showInputDialog(null,"Select choice as follows\n\n\n1.View records\n\n2.insert new record\n\n3.delete record\n\n4.modify record\n\n");
		if(ch==null)
		{
			JOptionPane.showMessageDialog(null, "Exiting");
			exit();
		}
		int chi = Integer.parseInt(ch);
		switch(chi)
		{
		case 1 :
			{
				select_q();
				break;
			}
		case 2 :
			{
				insert_q();
				break;
				
			}
		case 3 :
			{
				delete_q();
				break;
			}
		case 4 :
			{
				alter_q();
				break;
				
			}
		default :
			{
				exit();
			}
		
		//--------------------------------------> create RESULT SET
		}
	
		
		 
		
		
		
	}
	private static void alter_q() throws SQLException {
		// TODO Auto-generated method stub
		String stq = JOptionPane.showInputDialog(null,"enter ID number of record");
		new_ques();
		String stqi = "update [Quest$] set QUESTIONS = '"+quest+"', CH1 = '"+cho1+"', CH2 = '"+cho2+"', RGHT = '"+rght+"'";
		System.out.println(stqi+" where "+stq);
		stmnt.executeUpdate(stqi+" where ID = '"+stq+"'");
		
		
	}
	private static void delete_q() throws SQLException {
		// TODO Auto-generated method stub
		String stf = JOptionPane.showInputDialog(null,"enter ID number of record");
		String sti = "update [Quest$] set QUESTIONS = "+null+", CH1 = "+null+", CH2 = "+null+", RGHT = "+null;
		System.out.println(sti+"  where ID = "+stf);
		stmnt.executeUpdate(sti+"  where ID = '"+stf+"'");
	}
	private static void insert_q() throws SQLException {
		// TODO Auto-generated method stub
		count2 =count2+1;
		if((count2)>=10)
		{
			JOptionPane.showMessageDialog(null, "Test Records Full (max=10)\nAdmin May alter any previous record");
			admin_menu();
		}
		new_ques();
		String insertque = "insert into [Quest$] (ID,Questions,CH1,CH2,RGHT) values('"+((count2))+"','"+quest+"','"+cho1+"','"+cho2+"','"+rght+"')";        
		System.out.println(insertque);
		stmnt.executeUpdate(insertque);
	}
	private static void new_ques() {
		// TODO Auto-generated method stub
		System.out.println("new");
		JTextField Ques = new JTextField();
		JTextField ch1 = new JTextField();
		JTextField ch2 = new JTextField(2);
		JTextField right = new JTextField(1);
		
		
		
				
		Object[] msg = {"Question", Ques, "First Choice",ch1,"Second Choice",ch2,"Correct Answer",right};
		
		JOptionPane op = new JOptionPane(
			msg,
			JOptionPane.QUESTION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION,
			null,
			null);
		
				
		JDialog dialog = op.createDialog("Enter Info...");
		dialog.setVisible(true);
		quest = Ques.getText();
		cho1 = ch1.getText();
		cho2 = ch2.getText();
		rght = right.getText();
		
		if(quest.isEmpty() || cho1.isEmpty() || cho2.isEmpty() || rght.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "No field CAn have NULL value!!");
			new_ques();
		}
		
		//---------------------------------------------**********************************************
		
		
	}
	private static void select_q() {
		// TODO Auto-generated method stub
		//B();
		
		
		
		
	}
	private static void INFO() throws SQLException {
		// TODO Auto-generated method stub
		String ch = JOptionPane.showInputDialog(null,"Select choice as follows\n\n\n1.View records\n\n2.insert new record\n\n3.delete record\n\n4.modify record\n\n");
		if(ch==null)
		{
			JOptionPane.showMessageDialog(null, "Exiting");
			exit();
		}
		int chi = Integer.parseInt(ch);
		
		switch(chi)
		{
		case 1 :
			{
				select();
				break;
			}
		case 2 :
			{
				insert();
				break;
				
			}
		case 3 :
			{
				delete();
				break;
			}
		case 4 :
			{
				alter();
				break;
				
			}
		default :
			{
				exit();
			}
		
		//--------------------------------------> create RESULT SET
		}
		
		
	}
	public static void insert() throws SQLException  {
		// TODO Auto-generated method stub
		new_user();
		System.out.println("requuired area");
		String insertque = "insert into [Info$] (ID,LastName,FirstName,Age,Sex,Education) values('"+(count+1)+"','"+lnam+"','"+fnam+"','"+ag+"','"+sx+"','"+ed+"')";        
		System.out.println(insertque);
		stmnt.executeUpdate(insertque);
		rs = stmnt.getResultSet();
		
	}
	private static void alter() throws SQLException {
		// TODO Auto-generated method stub
		String stf = JOptionPane.showInputDialog(null,"ID number of record");
		new_user();
		String sti = "update [info$] set LastName = '"+lnam+"', FirstName ='"+fnam+"', age ='"+ag+"', sex ='"+sx+"', education ='"+ed+"' ";
		System.out.println(sti+" where "+stf);
		stmnt.executeUpdate(sti+" where ID = '"+stf+"'");
	}
	private static void delete() throws SQLException {
		// TODO Auto-generated method stub
		String stf = JOptionPane.showInputDialog(null,"enter ID number of record");
		String sti = "update [info$] set LastName = "+null+",FirstName ="+null+",age ="+null+",sex ="+null+",education ="+null;
		System.out.println(sti+"  where ID = "+stf);
		stmnt.executeUpdate(sti+"  where ID = '"+stf+"'");
	}
	private static void select() throws SQLException {
		// TODO Auto-generated method stub
		
		new A();
		JOptionPane.showMessageDialog(null, "Displayed!");
		
				
	}
	private static void Login() throws SQLException {
		JTextField firstName = new JTextField();
		JTextField code = new JPasswordField();
		Object[] msg = {"Name:", firstName, "Code(for ADMIN only):",code};
		//do{
		JOptionPane op = new JOptionPane(
			msg,
			JOptionPane.QUESTION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION,
			
			null,
			null);
		//------------------------->MAKE exit on close operation op.CLOSED_OPTION;<--------------------//
		
		JDialog dialog = op.createDialog("Enter Name...");
		dialog.setVisible(true);
		fnam = firstName.getText();
		int i = ((Integer)op.getValue()).intValue();
		if(i==JOptionPane.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Insufficient Info to take test");
			exit();
		}
		
		if(fnam.isEmpty())
		{
			do{
			JOptionPane.showMessageDialog(null, "Enter Name To proceed to Test");
			Login();
			
			
			}while((fnam.isEmpty()));
			//return;
		}
		
		int result = JOptionPane.OK_OPTION;
		try
		{
		    result = ((Integer)op.getValue()).intValue();
		}
		catch(Exception uninitializedValue)
		{}
		Id = firstName.getText();
		cod = code.getText();
		if(result == JOptionPane.OK_OPTION)
		
		{
			System.out.println(firstName.getText() + " : " + code.getText());
		}
		else
		{
			System.out.println("Canceled");
		}
		
		
	}
	private static void exit() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Exiting system");
		System.exit(0);
		
	}
	private static void new_user() {
		// TODO Auto-generated method stub
		System.out.println("new");
		JTextField f_nam = new JTextField();
		JTextField l_nam = new JTextField();
		JTextField age = new JTextField(2);
		JTextField sex = new JTextField(1);
		JTextField edu = new JTextField();
		
		
				
		Object[] msg = {"First Name:", f_nam, "Last Name",l_nam,"Age:",age,"Sex(m/f):",sex,"Education:",edu};
		
		JOptionPane op = new JOptionPane(
			msg,
			JOptionPane.QUESTION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION,
			null,
			null);
		
				
		JDialog dialog = op.createDialog("Enter Info...");
		dialog.setVisible(true);
		fnam = f_nam.getText();
		lnam = l_nam.getText();
		ag = age.getText();
		sx = sex.getText();
		ed = edu.getText();
		int i = ((Integer)op.getValue()).intValue();
		if(i== JOptionPane.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Insufficient Info to take test");
			exit();
		}
		
		if(fnam.isEmpty())
		{
			do{
			JOptionPane.showMessageDialog(null, "Enter Name To proceed to Test");
			fnam = JOptionPane.showInputDialog(null, "Name?");
			
			
			}while((fnam.isEmpty()));
			//return;
		}
		
		int result = JOptionPane.OK_OPTION;
		try
		{
		    result = ((Integer)op.getValue()).intValue();
		}
		catch(Exception uninitializedValue)
		{}
		
		
		if(result == JOptionPane.OK_OPTION)
			return;
		
		else
		{
			System.out.println("Canceled");
		}

		 
		
	}
	 
	
	
}

