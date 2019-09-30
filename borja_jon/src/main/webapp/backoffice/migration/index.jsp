<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>

	<h1>Resumen Migracion</h1>
	
	<%@include file="../../includes/mensaje.jsp"%>
	
	<h3>Lineas Leidas: ${leidas}</h3>
	<h3>Lineas Insertadas: ${insertadas}</h3>
	<h3>Lineas erroneas: ${erroneas}</h3>
	
	<h3>Tiempo de ejecucion: ${segundos} seg.</h3>
	
	
	

<%@include file="../../includes/footer.jsp"%>