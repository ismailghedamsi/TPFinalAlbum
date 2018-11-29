<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin page</title>
</head>
<body>
	<form action="Controller?action=addAlbumFromApi" method="post">
	<fieldset>
		<label for="albumName">Ajouter un album a la base de donnee via api :</label><br/>
		Album Name : <input type="text" name="albumName"><br>
		Artist Name : <input type="text" name="artistName"><br>
		<input type="submit" name="Add">
		</fieldset>
	</form>
	<br/>
	   <%@include file="/sharedFunctionality.jsp"%>
</body>
</html>