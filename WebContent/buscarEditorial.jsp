<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Buscar por Editorial</title>
	<!-- Hoja de estilos -->
	<link href = "./css/styleed.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la p�gina: buscarEditorial.js -->
	<script src = "./js/buscarEditorial.js" type = "text/javascript" ></script>
</head>

<body onload = "llamadaANombreEditoriales();">
	<header>
		<h1>BUSQUEDA DE EDITORIALES</h1>
	</header>
	<!-- Bloque principal de la p�gina. Todo el c�digo html se escribe dentro
	de este bloque -->
	<div id = "main"></div>
	
	<div id = "separacion"></div>
	<a id = "index" href = "./index.jsp"><<< Inicio</a>
	
	<footer> El tiempo de procesamiento de su petici�n ha sido de <span id="pie"></span> milisegundos.</footer>
</body>
</html>