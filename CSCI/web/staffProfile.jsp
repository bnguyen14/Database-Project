<%-- 
    Document   : staffProfile
    Created on : Dec 3, 2017, 4:15:18 PM
    Author     : Bao Nguyen
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">OWL</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.html">Home<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="student-tab" data-toggle="tab" href="login.jsp" role="tab" aria-controls="student" aria-selected="true">Student login</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" id="staff-tab" data-toggle="tab" href="stafflogin.jsp" role="tab" aria-controls="staff" aria-selected="false">Staff Login</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="tab-content scr" id="tabCon">
            <div class="tab-pane fade show active" id="staff" role="tabpanel" aria-labelledby="staff-tab">
                <div align="center">
                    <h3>Student Profile</h3>
                    <table border="1">
                        <thead>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <c:out value="<%= request.getAttribute("id") %>" />
                                </td>
                                <td>
                                    <c:out value="<%= request.getAttribute("fname") %>" />
                                </td>
                                <td>
                                    <c:out value="<%= request.getAttribute("lname") %>" />
                                </td>
                                <td>
                                    <c:out value="<%= request.getAttribute("email") %>" />
                                </td> 
                            </tr>
                        </tbody>
                    </table>
                    <table border="1" class="inlineTable">
                        <thead>
                            <th>Mentoring</th>
                        </thead>
                        <tbody>
                            <c:if test="${not empty mentors}">
                                <c:forEach items="${mentors}" var="mentor">
                                    <tr>
                                        <td><c:out value="${mentor}" /></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty mentors}">
                                <tr>
                                    <td>Not Mentoring</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </body>
</html>
