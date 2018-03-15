/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;

/**
 *
 * @author Blake-LT
 */
public class OracleConnection 
{
    private static Connection connection = null;
    
    public static Connection getConnection()
    {
        System.out.println("Oracle JDBC connection testing....");
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@168.28.245.66:1521:csuit", 
                    "bnguyen14", "bnguyen14&");
        }
        catch(Exception e)
        {
            System.out.println("Connection failed. Check out errors");
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    
    public static void closeConnection()
    {
        try
        {
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args)
    {
        try
        {
            Connection conn = OracleConnection.getConnection();
            //SQL command
            String sql = "select sysdate as current_day from dual";
            //create PreparedStatement object to execute SQL
            PreparedStatement preStatement = conn.prepareStatement(sql);
            //Get Resulset
            ResultSet result = preStatement.executeQuery();
            //Process data in resultant
            while(result.next())
            {
                System.out.println("Current Data is "+result.getString(1));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            OracleConnection.closeConnection();
        }
    }
    */
}
