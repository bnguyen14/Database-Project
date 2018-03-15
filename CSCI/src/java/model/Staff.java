/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
import java.sql.*;
import util.OracleConnection;

/**
 *
 * @author Bao Nguyen
 */
public class Staff 
{
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> mentors;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getMentors() {
        return mentors;
    }

    public void setMentors(List<String> mentors) {
        this.mentors = mentors;
    }
    
    private Connection conn;
    
    public String toString()
    {
        return "CSU Staff: "+firstName+" "+lastName+" "+email;
    }
    
    public void setStaff(String id, String email)
    {
        try
        {
            List<String> mentor = new ArrayList<String>();
            conn = OracleConnection.getConnection();
            String sql = "SELECT * FROM CSU_STAFF WHERE EMPLOYEEID = ? and EMAIL = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                setEmployeeId(rs.getString(1));
                setFirstName(rs.getString(2));
                setLastName(rs.getString(3));
                setEmail(rs.getString(4)); 
            }
            
            
            sql = "SELECT PROJECTNAME FROM PROJECTS WHERE PROJECTID = "
                    + "(SELECT PROJECTID FROM MENTOR WHERE EMPLOYEEID = "+id+")";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                mentor.add(rs.getString(1));
            }
            setMentors(mentor);
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
    
    public boolean validateStaff(String id, String email) throws Exception
    {
        boolean validated = false;
        try
        {
            conn = OracleConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement
                ("SELECT * FROM CSU_STAFF WHERE EMAIL = ? and EMPLOYEEID = ?");
            ps.setString(1,email);
            ps.setString(2,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                validated = true;
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
        return validated;
    }
}
