<%@page import="com.ipartek.formacion.controller.backoffice.UsuarioController"%>

<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>

	<h1>Listado Usuarios</h1>
	
	
	<%@include file="../../includes/mensaje.jsp"%>
			
	
	
		<a href="backoffice/usuario?op=<%=UsuarioController.OP_NUEVO%>" class="mb-3 btn btn-outline-primary">Nuevo Usuario</a>
		<div class="btn-group mr-2 mb-3 mt-3 float-right" role="group">
			<form class="form-inline my-2 my-lg-0" action="usuario">
				<input class="form-control mr-sm-2" type="search" name="nombreBuscar" placeholder="Buscar por Nombre" required>
				<input type="hidden" name="op" value="<%=UsuarioController.OP_BUSCAR%>">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit"> <i class="fas fa-search"></i></button>	
			</form>
		</div>
	
	
	<ul class="list-group">
	  <c:forEach items="${usuarios}" var="u">	
	  	<li class="list-group-item">
	  		<a href="usuario?op=<%=UsuarioController.OP_DETALLE%>&id=${u.id}">	  			
	  			<p class="h3">${u.id} ${u.nombre}</p>
	  		</a>
	  		<p class="h5"><i class="fas fa-file-video"></i> ${u.videoCount}<p>
	  		<p class="h5"><i class="fab fa-gratipay"></i> ${u.likesCount}</p>
	  	</li>
	  </c:forEach>	  	  
	</ul>

	
	
<%@include file="../../includes/footer.jsp"%>