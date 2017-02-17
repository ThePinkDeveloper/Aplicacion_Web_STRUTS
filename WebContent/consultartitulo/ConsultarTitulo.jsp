<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Consultar Libros por su Título</title>
	<!-- Hoja de estilos -->
	<link href = "../css/mostrarTitulo.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>CONSULTAR LIBROS POR SU TÍTULO</h1>
	</header>
	<div id = "main">
		<html:form action = "consultarTitulo" >
		<div class = "datoslibros">
		<h3> INTRODUZCA EL TÍTULO DEL LIBRO QUE DESEA ENCONTRAR </h3>

			<label>TÍTULO: </label>
			<html:text property="titulo" />
			<div id="submit">
				<html:submit value="Mostrar" />
			</div>
		</div>		
		</html:form>
			

		<!--  <input type="submit" value="Enviar">  -->

	</div>
	<article>
	</article>
	<div id = "separacion"></div>
	<a id="index" href = "../index.jsp"><<<  Inicio</a>
</body>
</html>