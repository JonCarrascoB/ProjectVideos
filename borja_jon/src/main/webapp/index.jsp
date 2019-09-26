<%@page import="com.ipartek.formacion.controller.IndexController"%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>


<%@include file="includes/mensaje.jsp"%>

<div class="btn-group mr-2 mt-3 mb-3" role="group">
	<c:if test="${sessionScope.usuario != null}">
		<a href="videos?op=<%=VideoController.OP_NUEVO%>" class="mb-2 btn btn-outline-primary">Nuevo Video</a>
	</c:if>
	<c:if test="${sessionScope.usuario == null}">
	
	<!-- Button trigger modal -->
		<button type="button" class="btn btn-outline-success btn-block" data-toggle="modal" data-target="#exampleModal">
			Crear Nuevo
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
				    <div class="modal-header">
				       <h2 class="modal-title text-danger" id="exampleModalLabel">Aviso:</h2>
				       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				       </button>
				    </div>
				    <div class="modal-body">
				      <h4>Para crear un nuevo video debe usted estar logeado.</h4>
				      <p> Si quiere seguir hacia la sección de crear video, pulse a Continuar, sino pulse atras para volver al menu inicial. Gracias</p>
				    </div>
				    <div class="modal-footer">
				      <button type="button" class="btn btn-danger" data-dismiss="modal">Atras</button>
			          <a href="login.jsp" class="btn btn-outline-success">Continuar </a>
				    </div>
				</div>
			</div>
		</div>	
	</c:if>
</div>
<div class="btn-group mr-2 mb-3 mt-3 float-right" role="group">
	<form class="form-inline my-2 my-lg-0" action="index">
      <input class="form-control mr-sm-2" type="search" name="nombreBuscar" placeholder="Buscar por Nombre o Usuario" required>
      <input type="hidden" name="op" value="<%=IndexController.OP_BUSCAR%>">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit"> <i class="fas fa-search"></i></button>
    </form>
</div>
	
<h3>Videos Publicados</h3>


<ul class="list-group">
	 <c:forEach items="${videosVisibles}" var="v" >	  
  			<li class="list-group-item">
				<a href="index?op=<%=IndexController.OP_DETALLE%>&id=${v.id}">
				  	<img class="float-left mr-3	" src="https://img.youtube.com/vi/${v.codigo}/mqdefault.jpg" alt="Imagen destacda del video ${v.nombre}"/>
				  	<p class="h3">${v.nombre}</p>
				</a>	
				<p><i class="fas fa-user mr-1"></i> ${v.usuario.nombre}</p>				  			
				<p><i class="fas fa-tag mr-1"></i> ${v.categoria.nombre}</p>
				<p><i class="fas fa-heart"></i> ${v.countLikes}</p>						  		
			</li>					
	</c:forEach>
</ul>		
		


<%@include file="includes/footer.jsp"%>