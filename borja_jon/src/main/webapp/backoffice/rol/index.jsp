<%@page import="com.ipartek.formacion.controller.backoffice.RolController"%>
<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>


<h1>Listado Roles</h1>
	
	
	<%@include file="../../includes/mensaje.jsp"%>
			
	
	
		<a href="backoffice/rol?op=<%=RolController.OP_MOSTRAR_FORMULARIO%>" class="mb-3 btn btn-outline-primary">Nuevo Rol</a>
		<div class="btn-group mr-2 mb-3 mt-3 float-right" role="group">
		<form class="form-inline my-2 my-lg-0" action="usuario">
			<input class="form-control mr-sm-2" type="search" name="nombreBuscar" placeholder="Buscar por Nombre" required>
			<input type="hidden" name="op" value="<%=RolController.OP_BUSCAR%>">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"> <i class="fas fa-search"></i></button>	
		</form>
		</div>
		<ul class="list-group">
	  <c:forEach items="${roles}" var="r">	
	  	<li class="list-group-item">
	  		<a href="backoffice/rol?op=<%=RolController.OP_MOSTRAR_FORMULARIO%>&id=${r.id}">	  			
	  			<p class="h3">${r.id} ${r.nombre}</p>
	  		</a>
	
	  	</li>
	  </c:forEach>	  	  
	</ul>




<%@include file="../../includes/footer.jsp"%>