<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8" %>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset="utf-8">
	<title>Error al mostrar el Libro por ISBN</title>
	<!-- Hoja de estilos -->
	<link href="./css/mostrarIsbn.css" rel="stylesheet" type="text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: index.js -->
	<script src="" type="text/javascript" ></script>
</head>

<body>
	<header>
		<h1>Error al mostrar un Libro por ISBN</h1>
	</header>
	<div id="main">
		<h2>Motivo: ${requestScope.errorValidacion}</h2>
	</div>
	<div id = "separacion"></div>
	<a id="index" href = "./index.jsp"><<<  Inicio</a>
	
	<footer> El tiempo de procesamiento de su petición ha sido de ${requestScope.tiempoPeticion} milisegundos.</footer>
</body>
</html>