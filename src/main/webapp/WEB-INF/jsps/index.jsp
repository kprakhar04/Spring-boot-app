<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<link href="/css/common.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Manrope&display=swap" rel="stylesheet">
<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>
${ title }
</title>
</head>
<body>
 <div id="welcome">
    <h1>Welcome to our application!</h1>
    <p>You found the best place to request for your required software/hardware and get its status even via email!</p>
    <c:if test="${yes }">
    <div class="cta">
      <a href="signup">Sign Up</a>
      <a href="signin">Sign In</a>
    </div>
    </c:if>
    <c:if test="${!yes }">
    <div class="cta">
      <a href="/home">Show</a>
      <a href="logout">Logout</a>
    </div>
    </c:if>
  </div>
<jsp:include page="footer.jsp"></jsp:include>