<%-- 
    Document   : login
    Created on : Nov 7, 2017, 11:47:24 AM
    Author     : Bao Nguyen
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Login</title>
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
                    <li class="nav-item active">
                        <a class="nav-link" id="student-tab" data-toggle="tab" href="login.jsp" role="tab" aria-controls="student" aria-selected="true">Student login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="staff-tab" data-toggle="tab" href="stafflogin.jsp" role="tab" aria-controls="staff" aria-selected="false">Staff Login</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="tab-content scr" id="tabCon">
            <div class="tab-pane fade show active" id="student" role="tabpanel" aria-labelledby="student-tab">
                <div align="center">
                    <h3>Please enter your First and Last name and ID</h3>
                    <form action="ValidateStudentServlet" method="POST">
                        <div id="label">First Name: </div><input type="text" name ="FIRSTNAME" /><br/>
                        <div id="label">Last Name: </div><input type="text" name ="LASTNAME" /><br/>
                        <div id="label">ID: </div><input type="text" name ="ID"/><br/>
                        <span>
                            <input type="submit" value="Log-in" id="rsubmit"/>
                            <a style="text-decoration: none;" href="studentReg.jsp">
                                <input type="button" value="Register" />
                            </a>
                        </span>
                    </form>
                    <h5><c:out value="${register}" /></h5>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </body>
</html>
