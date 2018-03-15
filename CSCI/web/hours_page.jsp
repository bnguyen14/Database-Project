<%-- 
    Document   : hours_page
    Created on : Nov 17, 2017, 11:22:45 PM
    Author     : sim
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hour's Page</title>
        <a href="index.jsp">Home Page</a><br />
        
    </head>
    <body>
        <h1>Enter hours:</h1>
        <form action="LogServerlet" method="POST">
            <div id="label">StudentId: <div/><input type="text" name="studentId"><br />   <!--"studentid" doesnt match .getParameter("studentId") in LogServerlet-->
            <div id="label">ProjectId: <div/><input type="text" name="projectId"><br />
            <div id="label">Hours Worked: <div/><input type="text" name="hoursWorked"><br />  <!--"hours worked doesnt match .getParameter("hoursWorked")-->
            <input type="submit" value="Submit" id="rsubmit"/>
        </form>
        
        <h2>List hours worked by:</h2>
        <form action="ListServerlet" method="POST">
            <div id="label">StudentId: <div/><input type="text" name="studentid"><br />  
                <input type="submit" value="Submit" id="submit"/><br/>
        </form>
        <table border="1">
            <thread>                
                <th>Hours Worked</th>
                <tbody>
                <c:forEach items="${hoursworked}" var="student" >  <!--students isnt the name used for .setAttribute in ListServerlet2-->
                    <tr>
                        <td>
                            <c:out value="${student.hoursWorked}" />  <!-- students doesnt even match the var "student"-->
                        </td>                                               <!--now to go ListServerlet-->
                    </tr>
                </c:forEach>
                        
                </tbody>
            </thread>
        </table>
            
    </body>
</html>
