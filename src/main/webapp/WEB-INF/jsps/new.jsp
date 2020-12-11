<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<html>
<head>
<style>
.form-group{
  padding: 20px;
}
</style>
<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${submission-success.length()>0 }">
<div class="alert alert-success">
${submission-success}
</div>
</c:if>
<div class="form-group">
<form action="make-request" method="POST">

<h3>Request for :</h3>

<label for="hard" class="radio-inline">Hardware</label>
<input type="radio" id="hard" name="requestType" value="hardware" onChange="prepopulate()">
<label for="soft" class="radio-inline">Software</label>
<input type="radio" id="soft" name="requestType" value="software" onChange="prepopulate()">
<!-- </div>
<div class="form-group"> -->
<br> <br>
<select name="requestName" id="requestValues" class="form-control">
</select>
<br> 
<button type="submit" class="btn btn-success">Submit</button>
</form><br> <br>
<a href="home">Check all Request Status</a>
</div>
<jsp:include page="footer.jsp"></jsp:include>