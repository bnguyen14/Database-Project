/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import java.sql.*;
import java.util.*;
import util.OracleConnection;
/**
 *
 * @author Bao Nguyen
 */
public class Student implements Serializable
{
    private String studentId;
    private String firstName;
    private String lastName;
    private String discipline;
    private List<String> skills;
    private List<String> teams;
    
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }
    
    private Connection conn;
    
    public String toString()
    {
        return "Student: "+studentId+" "+firstName+" "+lastName;
    }
    
    public void setStudent(String id)
    {
        try
        {
            List<String> skills = new ArrayList<String>();
            List<String> teams = new ArrayList<String>();
            conn = OracleConnection.getConnection();
            String sql = "SELECT * FROM STUDENTS WHERE STUDENTID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //String sql = "SELECT * FROM STUDENTS where STUDENTID = "+id+" and FIRSTNAME = "+fname+" and LASTNAME = "+lname+" ";
            ps.setString(1,id);
            setStudentId(id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                setFirstName(rs.getString(2));
                setLastName(rs.getString(3));
            }
            
            sql = "SELECT SKILLNAME FROM SKILLS WHERE STUDENTID = "+id;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                skills.add(rs.getString(1));
            }
            setSkills(skills);
            
            sql = "SELECT DISCIPLINENAME FROM DISCIPLINES WHERE STUDENTID = "+id;
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                discipline = rs.getString(1);
            }
            setDiscipline(discipline);
            
            sql = "SELECT TEAMNAME FROM STUDENTTEAMASSIGNMENT WHERE STUDENTID = "+id;
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                teams.add(rs.getString(1));
            }
            setTeams(teams);
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
    
    /*
    public List<Student> getStudent()
    {
        List<Student> students = new ArrayList<Student>();
        try
        {
            conn = OracleConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM STUDENTS";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                Student stu = new Student();
                stu.setStudentId(rs.getString(1));
                stu.setFirstName(rs.getString(2));
                stu.setLastName(rs.getString(3));
                students.add(stu);
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
        return students;
    }
    */
    public boolean validateStudent(String id, String fname, String lname) throws Exception
    {
        boolean validated = false;
        try
        {
            conn = OracleConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement
                ("SELECT * FROM STUDENTS WHERE STUDENTID = ? and FIRSTNAME = ? and LASTNAME = ?");
            ps.setString(1,id);
            ps.setString(2,fname);
            ps.setString(3,lname);
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
    
    private String findID()
    {
        List<Integer> tmp = new ArrayList<Integer>();
        int max = 0;
        try
        {
            conn = OracleConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT STUDENTID FROM STUDENTS";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                int tmpint = Integer.parseInt(rs.getString(1));
                tmp.add(tmpint);
            }
            for(int i = 0; i<tmp.size();i++)
            {
                if(tmp.get(i)>max)
                {
                    max=tmp.get(i);
                }
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
        return Integer.toString(max+1);
    }
    public void createStudent(String firstName, String lastName) throws Exception
    {
        try
        {
            String id = findID();
            setStudentId(id);
            setFirstName(firstName);
            setLastName(lastName);
            //get connection
            conn = OracleConnection.getConnection();
            //preparedstatement
            PreparedStatement ps;
            String sql="INSERT INTO STUDENTS (STUDENTID, FIRSTNAME, LASTNAME) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            //bind data
            ps.setString(1,id);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            //execute query
            ps.executeUpdate();
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
    public void addSkill(String id, String skill)
    {
        try
        {
            conn = OracleConnection.getConnection();
            PreparedStatement ps;
            String sql="INSERT INTO SKILLS (SKILLNAME, STUDENTID) VALUES (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,skill);
            ps.setString(2,id);
            ps.executeUpdate();
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
    public void addDiscipline(String id, String disc)
    {
        try
        {
            conn = OracleConnection.getConnection();
            PreparedStatement ps;
            String sql="INSERT INTO DISCIPLINES (DISCIPLINENAME, STUDENTID) VALUES (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,disc);
            ps.setString(2,id);
            ps.executeUpdate();
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
    /*
    public static void main(String[] args) throws Exception
    {
        Student stu = new Student();
        List<Student> students = stu.getStudent();
        for(Student s: students)
        {
            System.out.println(s.toString());
        }
        //Test validate student method
        
        String email = "jd@clayton.edu";
        String phone = "6783456789";
        if(stu.validateStudent(email, phone))
        {
            System.out.println("Hi "+email+" Welcome to CSCI3310");
        }
        else
        {
            System.out.println("Hi "+email+" please register first");
        }
        
    */
}
