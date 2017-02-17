<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"
		 import="com.grupoatrium.modelo.*"%>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Éxito al mostrar un Libro por ISBN</title>
	<!-- Hoja de estilos -->
	<link href = "./css/mostrarIsbn.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>ESTE ES EL LIBRO CON EL ISBN ESPECIFICADO:</h1>
	</header>
	<div id="main">
		<table>
			<tr>
				<th>Id</th>
				<th>Título</th>
				<th>ISBN</th>
				<th>Editorial</th>
				<th>Año Publicacion</th>
				<th>Precio</th>
			</tr>
			<tr>
				<td>${requestScope.libro.IDLibro}</td>
				<td>${requestScope.libro.titulo}</td>
				<td>${requestScope.libro.isbn}</td>
				<td>${requestScope.libro.editorial.nombre}</td>
				<td>${requestScope.libro.publicacion}</td>
				<td>${requestScope.libro.precio}</td>
			</tr>
		</table>
	</div>
	<article>

	</article>
	<div id = "separacion"></div>
	<a id="index" href = "./index.jsp"><<<  Inicio</a>
	
	<footer> El tiempo de procesamiento de su petición ha sido de ${requestScope.tiempoPeticion} milisegundos.</footer>
</body>
</html>