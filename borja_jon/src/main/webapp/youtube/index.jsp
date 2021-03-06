<%@page import="com.ipartek.formacion.controller.backoffice.UsuarioController"%>
<%@page import="com.ipartek.formacion.controller.VideoController"%>

<%@include file="../includes/header.jsp"%>
<%@include file="../includes/navbar.jsp"%>

		
	<%@include file="../includes/mensaje.jsp"%>
			
	
	<a href="videos?op=<%=VideoController.OP_NUEVO%>" class="mb-3 mt-2 btn btn-outline-primary">Nuevo Video</a>
	
	
	<div class="row">
		<div class="col">
			<h1>Videos Publicados</h1>
			<ul class="list-group">
	  			<c:forEach items="${videosVisibles}" var="v" >	  
  				  	<li class="list-group-item">
				  		<a href="videos?op=<%=VideoController.OP_DETALLE%>&id=${v.id}">
				  			<img class="float-left mr-3" src="https://img.youtube.com/vi/${v.codigo}/mqdefault.jpg" alt="Imagen destacda del video ${v.nombre}"/>
				  			<p class="h3">${v.nombre}</p>
				  		</a>
				  		<a href="usuario?op=<%=UsuarioController.OP_DETALLE%>&id=${v.usuario.id}">	
				  			<p><i class="fas fa-user mr-1"></i>${v.usuario.nombre}</p>
				  		</a>				  			
				  		<p><i class="fas fa-tag mr-1"></i>${v.categoria.nombre}</p>	
				  								  		
				  	</li>					
				</c:forEach>
			</ul>		
		</div>
		
		<div class="col">
			<h1>Videos Ocultos</h1>
			<ul class="list-group">
	  			<c:forEach items="${videosNoVisibles}" var="v" >	  
  				  	<li class="list-group-item">
				  		<a href="videos?op=<%=VideoController.OP_DETALLE%>&id=${v.id}">
				  			<img class="float-left mr-3" src="https://img.youtube.com/vi/${v.codigo}/mqdefault.jpg" alt="Imagen destacda del video ${v.nombre}"/>
				  			<p class="h3">${v.nombre}</p>
				  		</a>
				  		<a href="usuario?op=<%=UsuarioController.OP_DETALLE%>&id=${v.usuario.id}">	
				  			<p><i class="fas fa-user mr-1"></i>${v.usuario.nombre}</p>
				  		</a>				  			
				  		<p><i class="fas fa-tag mr-1"></i>${v.categoria.nombre}</p>						  		
				  	</li>					
				</c:forEach>
			</ul>		
		</div>
		
		
	</div>
	
	
<%@include file="../includes/footer.jsp"%>