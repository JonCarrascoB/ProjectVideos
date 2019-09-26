<%@page import="com.ipartek.formacion.controller.IndexController"%>
<%@page import="com.ipartek.formacion.controller.backoffice.UsuarioController"%>
<%@page import="com.ipartek.formacion.controller.VideoController"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <a class="navbar-brand" href="#">JEE</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item">
	        <a class="nav-link" href="index"><fmt:message key="menu.inicio" /></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="ejemplos/index.jsp">Ejemplos</a>
	      </li> 
	       	<c:if test="${usuario == null}">
	       		<li class="nav-item">
	        		<a class="nav-link" href="login.jsp">Login</a>
	        	</li>	
	        </c:if>
        
	        <c:if test="${usuario != null}">
	       	  <c:if test="${usuario.rol.id == 1 }">
	          <li class="nav-item">
		        <a class="nav-link" href="backoffice/inicio">Dashboard</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="videos">Videos</a>
		      </li>
	          <li class="nav-item mt-2">	        		
	        			${usuario.nombre}
	        			<i class="fas fa-user"></i>	        		
	        	</li>
	        	<li class="nav-item">
	        		<a class="nav-link" href="logout"><i class="fas fa-sign-out-alt"></i> Salir</a>
	        	</li>
	        	</c:if>
	        	<c:if test="${usuario.rol.id != 1 }">
	          <li class="nav-item">
		        <a class="nav-link" href="frontoffice/inicio">Dashboard</a>
		      </li>
		     		      
	          <li class="nav-item">	        		
	        			${usuario.nombre}
	        			<i class="fas fa-user"></i>	        		
	        	</li>
	        	<li class="nav-item">
	        		<a class="nav-link" href="logout"><i class="fas fa-sign-out-alt"></i> Salir</a>
	        	</li>
	        	</c:if>
	        </c:if>		       
	    </ul>
	    
	     <c:if test="${usuario != null}">
	       	  <c:if test="${usuario.rol.id == 1 }">
	    
	   	<div class="dropdown mr-2">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					   Usuarios
			  </button>
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> 
			   	<a class="dropdown-item" href="usuario?op=<%=UsuarioController.OP_LISTARVISIBLES%>&visible=true">Usuario Registrados</a>
		    	<a class="dropdown-item" href="usuario?op=<%=UsuarioController.OP_LISTARVISIBLES%>&visible=false">Usuarios Eliminados</a>			    
		  </div>
		</div>   
		</c:if>
		</c:if>
		<div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					  Videos Visualizados
			</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<c:forEach items="${videosVistos}" var="vv"> 
					<c:if test="${sessionScope.usuario != null}">
					<a class="dropdown-item" href="videos?op=<%=VideoController.OP_DETALLE%>&id=${vv.value.id}">${vv.value.nombre}</a>
					</c:if>
					<c:if test="${sessionScope.usuario == null}">
					<a class="dropdown-item" href="index?op=<%=IndexController.OP_DETALLE%>&id=${vv.value.id}">${vv.value.nombre}</a>
					</c:if>
				</c:forEach>				    
			</div>
		</div>
	   
	  </div>
	</nav>
    <!-- end navar -->
    
    <nav class="bg-dark">
    	<a href="i18n?idiomaSeleccionado=en_EN"><img src="resources/img/british.png" alt="" class="${sessionScope.idiomaSeleccionado != 'en_EN' ? 'inactive': ''  }"></a>
    	<a href="i18n?idiomaSeleccionado=eu_ES"><img src="resources/img/euskadi.png" alt="" class="${sessionScope.idiomaSeleccionado != 'eu_ES' ? 'inactive': ''  }"></a>
    	<a href="i18n?idiomaSeleccionado=es_ES"><img src="resources/img/Spain.png" alt="" class="${sessionScope.idiomaSeleccionado != 'es_ES' ? 'inactive': ''  }"></a> 
    </nav>
    
       
    <main class="container">