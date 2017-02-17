<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Modificar el Precio de un Libro</title>
	<!-- Hoja de estilos -->
	<link href = "../css/modificarPrecio.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>MODIFICAR EL PRECIO DE UN LIBRO</h1>
	</header>
	<div id = "main">
		<html:form action = "modificarPrecio">
		<div class = "datoslibros">
		<h3> INTRODUZCA EL ISBN DEL LIBRO AL QUE DESEA CAMBIAR EL PRECIO </h3>
			<label>	ISBN:	</label>
			<html:text property = "isbn" />
			<label>	NUEVO PRECIO:	</label>
			<html:text property = "precio" />
			<div id="submit">
				<html:submit value="Cambiar" />
			</div>
		</div>		
		</html:form>
			

	</div>
	<article>

	</article>
	<div id = "separacion"></div>
	<a id="index" href = "../index.jsp"><<<  Inicio</a>
</body>
</html>