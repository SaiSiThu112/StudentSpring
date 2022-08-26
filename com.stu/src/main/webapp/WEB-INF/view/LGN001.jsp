<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/com.stu/reach/test/test.css">
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
   	
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${param.error}</p>
          </div>
        </div>
        <form:form class="login-form" action="/com.stu/Login" method="post" name="confirm" modelAttribute="user">
       	<form:input path="id" type="text" placeholder="User ID" required="true"/>
         <form:input path="password" type="password" placeholder="password" required="true"/>
          <input type="submit" value="login">
          <p class="message">Not registered? <a href="#">Create an account</a></p>
        
        </form:form>
      </div>
    </div>
</body>

</html>