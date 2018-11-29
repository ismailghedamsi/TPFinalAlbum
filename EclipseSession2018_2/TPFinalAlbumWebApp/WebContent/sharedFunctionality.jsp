<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="Controller?action=addAlbumToUserAlbumCollection" method="post">
	<fieldset>
		<label for="albumName">Add album to user album collection :</label><br/>
		Album Name : <input type="text" name="albumName"><br>
		Artist Name : <input type="text" name="artistName"><br>
		<input type="submit" name="Add">
		</fieldset>
	</form>
	<br/>


<a href="Controller?action=displayApplicationAlbums">Display all the album the application have :</a>
<br/>
<a href="Controller?action=displayUserAlbums">Display your album collection :</a>