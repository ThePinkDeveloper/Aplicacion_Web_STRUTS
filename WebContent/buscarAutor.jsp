<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Buscar por Autor</title>
	<!-- Hoja de estilos -->
	<link href = "./css/styleau.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la p�gina: buscarAutor.js -->
	<script src = "./js/buscarAutor.js" type = "text/javascript" ></script>
</head>

<body onload = "llamadaANombreAutores();">

	<header>
		<h1>BUSQUEDA DE AUTORES</h1>
	</header>
	<!-- Bloque principal de la p�gina. Todo el c�digo html se escribe dentro
	de este bloque -->
	<div id = "main"></div>
	<div id = "separacion"></div>
	<a id = "index" href = "./index.jsp"><<< Inicio</a>
	
	<footer> El tiempo de procesamiento de su petici�n ha sido de <span id="pie"></span> milisegundos.</footer>

</body>
</html>