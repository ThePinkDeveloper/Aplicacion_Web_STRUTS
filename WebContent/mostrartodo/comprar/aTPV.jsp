<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8" %>
		 
<%@ taglib prefix="met" uri="/tlds/mis_etiquetas.tld"%>

<!DOCTYPE html>

<html lang = "es">

<head>
	<meta charset = "utf-8">
	<title>Compra Finalizada</title>
	<!-- Hoja de estilos -->
	<link href = "../../css/tpv.css" rel = "stylesheet" type = "text/css">
	<!-- Archivo JavaScript que se carga al abrir la página: buscarAutor.js -->
	<script src = "" type = "text/javascript" ></script>
</head>

<body>
	<header>
		<h2>POR AQUÍ TE METEN EL PUFO A TU CUENTA BANCARIA POR VALOR DE ${sessionScope.preciototal} euros</h2>
	</header>

	<a id="index" href = "../../index.jsp"><<<  Inicio</a>
	
	<footer> El tiempo de procesamiento de su petición ha sido de <met:tiempo_peticion_tag tiempoInicio="${requestScope.inicioPeticion}"> </met:tiempo_peticion_tag> milisegundos.</footer>
</body>
</html>