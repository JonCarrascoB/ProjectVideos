<%@page import="com.ipartek.formacion.controller.backoffice.CategoriaController"%>
<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>

	<h1>Listado de Categorias</h1>
	
	<%@include file="../../includes/mensaje.jsp"%>
	
	
	<a href="backoffice/categoria?op=<%=CategoriaController.OP_MOSTRAR_FORMULARIO%>" class="mb-3 btn btn-outline-primary">Nueva Categoria</a>
		<div class="btn-group mr-2 mb-3 mt-3 float-right" role="group">
			<form class="form-inline my-2 my-lg-0" action="usuario">
				<input class="form-control mr-sm-2 ml-2 mb" type="search" name="nombreBuscar" placeholder="Buscar por Categoria" required>
				<input type="hidden" name="op" value="<%=CategoriaController.OP_BUSCAR%>">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit"> <i class="fas fa-search"></i></button>	
			</form>
		</div>
	
	<ul class="list-group">
	  <c:forEach items="${categorias}" var="c">	
	  	<li class="list-group-item">
	  		<a href="backoffice/categoria?op=<%=CategoriaController.OP_MOSTRAR_FORMULARIO%>&id=${c.id}">	  			
	  			<p class="h3">${c.id}. ${c.nombre}</p>
	  		</a>
	  	</li>
	  </c:forEach>	  	  
	</ul>

	
<%@include file="../../includes/footer.jsp"%>
