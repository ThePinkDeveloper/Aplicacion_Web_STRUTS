<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8" %>
		 
<%@ taglib prefix="met" uri="/tlds/mis_etiquetas.tld"%>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset="utf-8">
	<title>Página principal</title>
	<!-- Hoja de estilos -->
	<link href="./css/style.css" rel="stylesheet" type="text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: index.js -->
	<script src="" type="text/javascript" ></script>
</head>

<body>
	<h1>Bienvenido a nuestra aplicación web de Librería</h1>
	<div class = "separacion"></div>
	<h2>Hoy le recomendamos nuestro libro: ${applicationScope.ad}</h2>
	<div class = "separacion"></div>
	<h2>Desde aquí puede:</h2>
	<div>
		<a href="./altalibro/AltaLibro.jsp">Dar de alta un libro en la base de datos</a>
		<a href="./eliminarlibro/EliminarLibro.jsp">Eliminar un libro de la base de datos</a>
		<a href="./gestionCompra.do">Mostrar todos los libros disponibles en la base de datos</a>
		<a href="./consultarisbn/ConsultarIsbn.jsp">Consultar un libro por su ISBN</a>
		<a href="./consultartitulo/ConsultarTitulo.jsp">Consultar los libros por título</a>
		<a href="./modificarprecio/ModificarPrecio.jsp">Modificar el precio de un libro</a>
		<a href="./buscarAutor.jsp">Consultar los libros escritos por un autor</a>		
		<a href="./buscarEditorial.jsp">Consultar los libros publicados por una editorial</a>		
	</div>
</body>
</html>