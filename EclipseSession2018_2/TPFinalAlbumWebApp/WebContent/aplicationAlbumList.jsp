<%@page import="java.io.IOException"%>
<%@page import="bean.*"%>
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

	%>
	<table border="1">
		<tr>
			<th>Album Name</th>
			<th>Album Type</th>
			<th>Tracklist</th>
			<th>Supprimer</th>
		<tr>
		<%
			for(Album currentAlbum :albumList ){
				List<bean.Song> tracks = currentAlbum.getTracklist();
				String tracklist = "";
				%>
				<tr>
					<td><%=currentAlbum.getAlbumName() %></td>
					<td><%=currentAlbum.getType() %></td>
					<td><%
						for(int i=0;i<tracks.size();i++){
							tracklist += (i+1)+"-"+tracks.get(i)+"<br/>";
						}
						
						%><%=tracklist %></td>
					}
					<td><a href="Controller?action=supprimerAlbum?idAlbum=<%=currentAlbum.getId() %>">Supprimer <%=currentAlbum.getAlbumName() %></a></td>
				</tr>
		<% 	}%>
	</table>
</body>
</html>