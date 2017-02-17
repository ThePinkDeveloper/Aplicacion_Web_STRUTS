<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8" %>
		 
<%@ taglib prefix="met" uri="/tlds/mis_etiquetas.tld"%>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Éxito al mostrar los Libros por su Título</title>
	<!-- Hoja de estilos -->
	<link href = "./css/mostrarTitulo.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>ESTOS LIBROS COINCIDEN CON EL TÍTULO INTRODUCIDO</h1>
	</header>
	<div id = "main">
		<div class = "datoslibros">
			<table>
				<met:desplegar_list_titulo tituloLibro="${requestScope.titulo}"></met:desplegar_list_titulo>
			</table>
		</div>		
	</div>
	<article>

	</article>
	<div id = "separacion"></div>
	<a id="index" href = "./index.jsp"><<<  Inicio</a>
	
	<footer> El tiempo de procesamiento de su petición ha sido de <met:tiempo_peticion_tag tiempoInicio="${requestScope.inicioPeticion}"> </met:tiempo_peticion_tag> milisegundos.</footer>
</body>
</html>