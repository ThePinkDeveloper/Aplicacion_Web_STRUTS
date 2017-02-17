<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Consultar un Libro por su ISBN</title>
	<!-- Hoja de estilos -->
	<link href = "../css/mostrarIsbn.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>CONSULTAR UN LIBRO POR SU ISBN</h1>
	</header>
	<div id = "main">
		<html:form action = "consultarIsbn">
			<div class = "datoslibros">
				<h3> INTRODUZCA EL ISBN DEL LIBRO QUE DESEA ENCONTRAR </h3>
				<label>	ISBN: </label>
				<html:text property="isbn"/>
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