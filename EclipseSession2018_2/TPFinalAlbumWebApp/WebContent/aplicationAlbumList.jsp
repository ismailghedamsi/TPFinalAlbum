<%@page import="bean.Admin"%>
<%@page import="bean.Album"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of album available in the application</title>
</head>
<body>
	<%
		List<bean.Album> albumList =( List<bean.Album>) request.getSession().getAttribute("albumList");
		Album album = albumList.get(0);
	%>
	<table border="1">
		<tr>
			<th>Album Name</th>
			<th>Album Type</th>
		<tr>
		<%
			for(Album currentAlbum :albumList ){
				%>
				<tr>
					<td><%=currentAlbum.getAlbumName() %></td>
					<td><%=currentAlbum.getType() %></td>
				</tr>
		<% 	}%>
	</table>
</body>
</html>