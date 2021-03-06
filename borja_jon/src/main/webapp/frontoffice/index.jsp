<%@page import="com.ipartek.formacion.controller.VideoController"%>
<%@page import="com.ipartek.formacion.controller.backoffice.UsuarioController"%>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/navbar.jsp"%> 
<%@include file="../includes/mensaje.jsp"%>




<a href="videos?op=<%=VideoController.OP_NUEVO%>" class="mb-2 mt-3 btn btn-outline-primary">Nuevo Video</a>
	
	
	<div class="row">
		<div class="col-8">
			<h1>Videos Publicados</h1>
			<ul class="list-group">
	  			<c:forEach items="${listadoVideos}" var="v" >	  
  				  	<li class="list-group-item">
				  		<a href="videos?op=<%=VideoController.OP_DETALLE%>&id=${v.id}">
				  			<img class="float-left mr-3 mt-3" src="https://img.youtube.com/vi/${v.codigo}/mqdefault.jpg" alt="Imagen destacda del video ${v.nombre}"/>
				  			<p class="h3">${v.nombre}</p> 
				  		</a>
				  		<p><i class="fas fa-user mr-1"></i>${v.usuario.nombre}</p>
				  		<p><i class="fas fa-tag mr-1"></i>${v.categoria.nombre}</p>
				  		<p><i class="fas fa-heart"></i> ${v.countLikes}</p>							  		
				  	</li>					
				</c:forEach>
			</ul>		
		</div> 
		
		
		
		<div class="col-4 mt-2"> 
			<div class="card text-white bg-danger o-hidden mt-5 mb-5	">
       			 <div class="card-body">
       			   <div class="card-body-icon">
        		    <i class="fas fa-heart"></i>
       			   </div>
      		    <div class="mr-5">Has recibido <strong><span class="h5">${numeroLikes}</span></strong> likes</div>
    		    </div>
   		   
     	 	</div>
		
		
			<h2 class=" text-center "> Cambiar Contraseņa</h2> 
			<div class="p-2 border rounded"> 
				 <form action="usuario" method="post" class="mb-2"> 
					 <input type="hidden" name="op" value="<%=UsuarioController.OP_CAMBIARPASS%>">
					<div class="form-group">	
					<input type="text" name="nombre" value="${sessionScope.usuario.nombre}" hidden class="form-control">
					</div>
				 	<div class="form-group">
							<label for="codigo">Nueva Contraseņa:</label>
							<input type="password" name="contrasenya"   class="form-control">
					</div>	
					<input type="submit" value="Guardar" class="btn btn-outline-primary  btn-block">	
		 
				 </form>
		</div>
		</div>
	</div>

<%@include file="../includes/footer.jsp"%>