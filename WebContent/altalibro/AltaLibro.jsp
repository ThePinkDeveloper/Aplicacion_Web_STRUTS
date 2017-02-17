<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Dar de Alta un Libro</title>
	<!-- Hoja de estilos -->
	<link href = "../css/altaLibro.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h1>DAR DE ALTA UN LIBRO EN LA BASE DE DATOS</h1>
	</header>
	<div id = "main">
		<html:form action = "altaLibro">
		<div class = "datoslibros">
		<h3> DATOS DEL LIBRO </h3>

			<label>	Título:	</label>
			<html:text property="titulo"/>
			<label>	ISBN: </label>
			<html:text property="isbn"/>
			<label>	Año de publicación: </label>
			<html:text property="publicacion"/>
			<label id="precio">	Precio: </label>
			<html:text property="precio"/>
			<label>	Descripción del Libro: </label>
			<html:textarea property="descripcion" rows="3"/>
		</div>
		<div class = "datoslibros">
			<h3> DATOS DE LA EDITORIAL </h3>
			<label>	Nombre:	</label>
			<html:text property="nombreEditorial"/>
			<label>	NIF:</label>
			<html:text property="nifEditorial"/>
			<label>	Direccion: (Calle y número)</label>
			<div id="calle">
				<html:text property="calleEditorial"/>
			</div>
			<div id="numero">
				<html:text property="numeroEditorial"/>
			</div>
			<label>	Código Postal: </label>
			<html:text  property="cpEditorial"/>
			<label>	Población: </label>
			<html:text  property="poblacionEditorial"/>
			<label>	Provincia: </label>
			<html:text  property="provinciaEditorial"/>
		</div>
		<div class = "datoslibros">
			<h3> DATOS DE LOS AUTORES </h3>
			<div class="autores">
				<label>	Nombre Autor 1:	</label>
				<html:text property="nombreAutor1"/>
				<label>	Nacionalidad Autor 1</label>
				<html:text property="nacionalidadAutor1"/>
				<label>	Comentario Autor 1: </label>
				<html:text property="comentarioAutor1"/>	
			</div>
			<div class="autores">
				<label>	Nombre Autor 2:	</label>
				<html:text property="nombreAutor2"/>
				<label>	Nacionalidad Autor 2</label>
				<html:text property="nacionalidadAutor2"/>
				<label>	Comentario Autor 2: </label>
				<html:text property="comentarioAutor2"/>	
			</div>
			<div class="autores2">
				<label>	Nombre Autor 3:	</label>
				<html:text property="nombreAutor3"/>
				<label>	Nacionalidad Autor 3</label>
				<html:text property="nacionalidadAutor3"/>
				<label>	Comentario Autor 3: </label>
				<html:text property="comentarioAutor3"/>	
			</div>
			<div class="autores">
				<label>	Nombre Autor 4:	</label>
				<html:text property="nombreAutor4"/>
				<label>	Nacionalidad Autor 4</label>
				<html:text property="nacionalidadAutor4"/>
				<label>	Comentario Autor 4: </label>
				<html:text property="comentarioAutor4"/>
				<div id="submit">
				<html:submit value="Dar de Alta"/>
				</div>	
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