package database;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        try {
           // Class.forName("com.mysql.jdbc.Driver"); //deprecated one
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
            
            Statement stmt = con.createStatement();  
          ResultSet rs = stmt.executeQuery("select * from eproduct"); 
            
            while(rs.next()) {
                
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)); 
            }
            
            //   stmt.executeUpdate("insert into eproduct (name, price, date_added) values ('Ipad', 7830.00, now());"); 
          //  stmt.executeUpdate("update eproduct set price=20000 where name = 'Laptop'");
           // stmt.executeUpdate("delete from eproduct where name = 'desktop'");
         // stmt.executeUpdate("create database db2"); //create database using JDBC
   //         stmt.executeUpdate("drop database db2"); 
     
            //to call stored procedure use callable statement
            
            CallableStatement stmt1 = con.prepareCall("{call add_product(?, ?)}");
            stmt1.setString(1, "Harddisk");
            
            BigDecimal obj = new BigDecimal(1900.50);
            
            stmt1.setBigDecimal(2, obj);
            stmt1.executeUpdate();

        } catch (ClassNotFoundException e) {
            
           System.out.println("Class not found");
        } catch (SQLException e) {
            
           System.out.println("SQL Exception");
        } 
	}
}
