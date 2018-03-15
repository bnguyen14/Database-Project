/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.OracleConnection;

/**
 *
 * @author sim, bao
 */
public class Hours implements Serializable{
    
    private String studentId;
    private String projectId; //changed table
    private String hoursWorked;
    
    Connection conn = null;


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    public List<Hours> getHours(String sId){ //fixed get hours
        List<Hours> hours = new ArrayList<Hours>();
        try{
            conn = OracleConnection.getConnection();
            String sql = "SELECT * FROM HOURS WHERE STUDENTID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,sId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Hours h = new Hours();
                h.setStudentId(rs.getString(1));
                h.setProjectId(rs.getString(2));
                h.setHoursWorked(rs.getString(3));
                hours.add(h);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return hours;
    }
    
    public void acceptLogHours(String sId, String pId, String hW){ //fixed logging hours
    try{//get connection
            //preparedStatement
            if(!listHours(sId, pId))  //if there is entry update that entry(adds time)
            {
                conn = OracleConnection.getConnection();
                int curHour=0;
                String sql = "SELECT HOURS FROM HOURS WHERE STUDENTID = ? and PROJECTID = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, sId);
                ps.setString(2, pId);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    curHour = rs.getInt(1);
                }
                sql = "UPDATE HOURS SET HOURS = "+(hW+curHour)+" WHERE STUDENTID = "+sId+" AND PROJECTID = "+pId;
                Statement stmt = conn.createStatement();
                /*
                ps.setString(1, (hW+curHour));
                ps.setString(2, sId);
                ps.setString(3, pId);
                */
                stmt.executeUpdate(sql);
            }
            else  //if there is no entry
            {
                conn = OracleConnection.getConnection();
                String sql = "INSERT INTO HOURS (STUDENTID, PROJECTID, HOURS) VALUES (?,?,?)"; //"sId =?, hW=?" is not correct sql query
                PreparedStatement ps =
                    conn.prepareStatement(sql);
                //Bind data
                ps.setString(1, sId);
                ps.setString(2, pId);
                ps.setString(3, hW);
                //Execute Query
                ps.executeUpdate(); //executeUpdate() was never called
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
    }finally{
        OracleConnection.closeConnection();
        }
    }
    
    public boolean listHours(String sId){ //fixed check for hours
        boolean hours = false;
        try{//get connection
            conn = OracleConnection.getConnection();
            //preparedStatement
            PreparedStatement ps =
                    conn.prepareStatement("SELECT * FROM HOURS WHERE STUDENTID = ?");
            ps.setString(1, sId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){//have correct studentId
                hours = true;
            }
            }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        OracleConnection.closeConnection();
        }
        return hours;
    }
    
    public boolean listHours(String sId, String pId){ //fixed check for hours
        boolean hours = false;
        try{//get connection
            conn = OracleConnection.getConnection();
            //preparedStatement
            String sql = "SELECT * FROM HOURS WHERE STUDENTID = ? AND PROJECTID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sId);
            ps.setString(2, pId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){//have correct studentId
                hours = true;
            }
            }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        OracleConnection.closeConnection();
        }
        return hours;
    }
    /*
    public List<Hours> getHours2(){ // unnecessary 
        List<Hours> hours = new ArrayList<Hours>();
        try{
            conn = OracleConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select hoursWorked from hours";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Hours h = new Hours();
                h.setHoursWorked(rs.getString(3)); //hoursWorked
                hours.add(h);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return hours;
    }
    */
    //@Override
    public String toString(){
       return "Hours info: " +studentId+" "+projectId+" "+hoursWorked;
    } 
    
    public static void main(String[] args){
     /*
        Hours h = new Hours();
        List<Hours> hour = h.getHours2();
        for(Hours h2 : hour){
            System.out.println(h.toString());}
    */
    }
    
}
