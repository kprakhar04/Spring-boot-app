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
<div class="container">
<div id="header">
<h2>All Request</h2>
<div id="cta">
<a href="/">Home</a>&nbsp;&nbsp;
<a href="logout">Logout</a>
</div>
</div>
<br/>
<table class="table table-dark">
<tr>
<th scope="col">Request id</th>
<th scope="col">Request For</th>
<th scope="col">Request date</th>
<th scope="col">Request by</th>
<th scope="col">Status</th>
<th scope="col">Action</th>
</tr>
<c:forEach var="temp" items="${statusInfo}">
<tr>
<th scope="row">${temp.id}</th>
<td>${temp.requestName }</td>
<td>${temp.date }</td>
<td>${temp.username}</td>
<td>${temp.status }</td>
<c:if test="${temp.status.equalsIgnoreCase('Submitted/Waiting')}">
<td>
<pre>
<a href="approve?id=${temp.id }">Approve</a> | <a href="cancel?id=${temp.id }">Cancel</a>
</pre>
</td>
</c:if>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>