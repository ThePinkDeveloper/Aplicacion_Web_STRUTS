<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Eliminar un Libro</title>
	<!-- Hoja de estilos -->
	<link href = "../css/eliminarLibro.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>ELIMINAR UN LIBRO EN LA BASE DE DATOS</h1>
	</header>
	<div id = "main">
		<html:form action = "eliminarLibro">
			<div>
				<h3> INTRODUZCA EL ID DEL LIBRO QUE DESEA ELIMINAR </h3>
				<label>	ID:	</label>
				<html:text property="id"/>
				<div id="submit">
					<html:submit value="Eliminar"/>
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