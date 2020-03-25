import java.sql.*;
import java.io.*;
 
public class myjdbcproj2313{
	
	public static void main(String args[]) throws IOException{
	Connection con=null;
	Statement stmt=null;
	int ch,n;// n is for no of rows effected
	  
	try{
		// Load the driver class
 		Class.forName("oracle.jdbc.driver.OracleDriver"); 
	
		// Create the connection object
		String conurl="jdbc:oracle:thin:@172.17.144.110:1521:ora11g";
 		con=DriverManager.getConnection(conurl,"csea1741012313","csea1741012313");
		stmt=con.createStatement();// creating statement object 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// creating buffer object
		
		do{
		System.out.println("\n***** Banking Management System*****");
		// Display the menu...
		System.out.println("\t" + "Displaying MENU.......");
		System.out.println("\t\t" + "1: Display Customer Record");
		System.out.println("\t\t" + "2: Insert Customer Information");
		System.out.println("\t\t" + "3: Delete Customer Information");
		System.out.println("\t\t" + "4: Update Customer Information");
		System.out.println("\t\t" + "5: Display Account Details of user");
		System.out.println("\t\t" + "6: Display Loan details of user");
		System.out.println("\t\t" + "7: Deposit Money in Account");
		System.out.println("\t\t" + "8: Withdraw money from Account");
		System.out.println("\t\t" + "9: Exit the Program");
		System.out.print("\t" + "\nEnter your Choice as per above menu => ");
		
		ch = Integer.parseInt(br.readLine());
		System.out.println();
		
		switch(ch){
			case 1:	
			// Display Customer Records
			
			String sqlstr = "select * from CUSTOMER order by cust_no";
			ResultSet rs = stmt.executeQuery(sqlstr);
			while(rs.next()){
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.println(rs.getString(4));	
			}
			
			break;
		
			case 2:
			// Add customer record
			// Accept input for each column from user
			
			System.out.print("Enter Customer no:");
			String cno = br.readLine();
			//System.out.println();
			
			System.out.print("Enter name:");
			String name = br.readLine();
			//System.out.println();
			
			System.out.print("Enter Phone no:");
			long pno = Long.parseLong(br.readLine());
			//System.out.println();
			
			System.out.print("Enter City:");
			String ct = br.readLine();
			//System.out.println();
			
			String instr = "insert into customer values('"+cno+"','"+name+"',"+pno+",'"+ct+"')";
			n = stmt.executeUpdate(instr);
			//System.out.println(n + " rows Inserted.");
			
			break;
			
			case 3:
			// Delete customer record
			// Accept customer number from user
			
			System.out.print("Enter Customer no to be deleted:");
			cno = br.readLine();
			instr = "delete from customer where cust_no ='"+cno+"'";
			n = stmt.executeUpdate(instr);
			System.out.println(n + " row Deleted.");
			
			break;
			
			case 4:
			// Update customer record
			// Accept customer number from user
			System.out.print("Enter Customer no to be updated:");
			cno = br.readLine();
			// Accept user's choice
			System.out.println("Enter 1: For Name 2: For Phone no 3: For City to update:");
			int choice_variable_1 = Integer.parseInt(br.readLine());

			switch(choice_variable_1){
			
			case 1:
			// Update customer's name
			System.out.print("Enter name:");
			name = br.readLine();
			instr = "update customer set name ='"+name+"'where cust_no ='"+cno+"'";
			n = stmt.executeUpdate(instr);
			System.out.println(n + " row Updated.");
			break;
			
			case 2:
			// Update customer's phone number
			System.out.print("Enter Phone no:");
			pno = Long.parseLong(br.readLine());
			instr = "update customer set phone_no ='"+pno+"'where cust_no ='"+cno+"'";
			n = stmt.executeUpdate(instr);
			System.out.println(n + " row Updated.");
			break;
			
			case 3:
			// Update customer's city
			System.out.print("Enter City:");
			ct = br.readLine();
			instr = "update customer set city ='"+ct+"'where cust_no ='"+cno+"'";
			n = stmt.executeUpdate(instr);
			System.out.println(n + " row Updated.");
			break;
			}
			break;
		
			case 5:
			// Display account details
			// Accept customer number from user
			System.out.print("Enter Cust_no of Customer to be Displayed\nwith account details:");
			cno = br.readLine();
			sqlstr = "SELECT * FROM CUSTOMER NATURAL JOIN DEPOSITOR NATURAL JOIN ACCOUNT NATURAL JOIN BRANCH 					WHERE CUST_NO='"+cno+"'";
			rs = stmt.executeQuery(sqlstr);
			while(rs.next()){
				System.out.print(rs.getString("cust_no") + "\t");
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getString("phone_no") + "\t");
				System.out.print(rs.getString("city") + "\t");
				System.out.print(rs.getString("account_no") + "\t");
				System.out.print(rs.getString("type") + "\t");
				System.out.print(rs.getString("balance") + "\t");
				System.out.print(rs.getString("branch_code") + "\t");
				System.out.print(rs.getString("branch_name") + "\t");
				System.out.println(rs.getString("branch_city"));
			}
			break;
		
			case 6:
			// Display loan details
			// Accept customer number from user
			System.out.print("Enter Cust_no of Customer to be Displayed\nwith Loan details:");
			cno = br.readLine();
			sqlstr = "SELECT * FROM CUSTOMER NATURAL JOIN LOAN NATURAL JOIN BRANCH WHERE CUST_NO='"+cno+"'";
			rs = stmt.executeQuery(sqlstr);
			while(rs.next()){
				System.out.print(rs.getString("cust_no") + "\t");
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getString("phone_no") + "\t");
				System.out.print(rs.getString("city") + "\t");
				System.out.print(rs.getString("loan_no") + "\t");
				System.out.print(rs.getString("amount") + "\t");
				System.out.print(rs.getString("branch_code") + "\t");
				System.out.print(rs.getString("branch_name") + "\t");
				System.out.println(rs.getString("branch_city"));
			}
			break;
			case 7:
			//Deposit money
			// Accept the account number to be deposited in
			System.out.print("Enter Account no:");
			String ano = br.readLine();
			System.out.print("Enter amount to be deposited:");
			long amount = Long.parseLong(br.readLine());
			instr = "update account set balance=balance+'"+amount+"'where account_no ='"+ano+"'";
			n = stmt.executeUpdate(instr);
			// Message for transaction completion
			System.out.println("Money Deposited.");
			break;

			case 8:
			//Withdraw money
			// Accept the account number to be withdrawn from
			System.out.print("Enter Account no:");
			ano = br.readLine();
			System.out.print("Enter amount to be withdrawn:");
			amount = Long.parseLong(br.readLine());
			// Handle appropriate withdral ckeck conditions
			
			instr = "update account set balance=balance-'"+amount+"'where account_no ='"+ano+"'";
			n = stmt.executeUpdate(instr);
			
			// Message for transaction completion
			System.out.println("Money Withdrawn.");
			break;
		
			case 9:
			// Exit the menu
			break;

			default:
			System.out.println("Wrong Choice! Retry...");
		}// Closing switch
		}while(ch != 9);
		
	}// closing try
	
	catch(Exception e){
		System.out.println(e);
	}// closing catch block
	
	}// end of main function

}// end of main class	 
