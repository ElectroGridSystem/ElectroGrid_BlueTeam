package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pafelegb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String payment_method, String name_on_card, String card_number, String cvc, String exp) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into paymentgb(`Payment_id`,`payment_method`,`name_on_card`,`card_number`,`cvc`,`exp`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, payment_method);
			preparedStmt.setString(3, name_on_card);
			preparedStmt.setString(4, card_number);
			preparedStmt.setString(5, cvc);
			preparedStmt.setString(6, exp);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Payment Method</th><th>Card Number</th><th>Payment Date</th><th>Total Amount</th><th>Description</th></tr>";
			String query = "select * from paymentgb";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Payment_id = Integer.toString(rs.getInt("Payment_id"));
				String payment_method = rs.getString("payment_method");
				String name_on_card = rs.getString("name_on_card");
				String card_number = rs.getString("card_number");
				String cvc = rs.getString("cvc");
				String exp = rs.getString("exp");

				output += "<tr><td>" + Payment_id + "</td>";
				output += "<td>" + payment_method + "</td>";
				output += "<td>" + name_on_card + "</td>";
				output += "<td>" + card_number + "</td>";
				output += "<td>" + cvc + "</td>";
				output += "<td>" + exp + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String Payment_id, String payment_method, String name_on_card, String card_number, String cvc, String exp) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE paymentgb SET payment_method=?,name_on_card=?,card_number=?,cvc=?,exp=? WHERE Payment_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, payment_method);
			preparedStmt.setString(2, name_on_card);
			preparedStmt.setString(3, card_number);
			preparedStmt.setString(4, cvc);
			preparedStmt.setString(5, exp);
			preparedStmt.setInt(6, Integer.parseInt(Payment_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String Payment_id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from paymentgb where Payment_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Payment_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
