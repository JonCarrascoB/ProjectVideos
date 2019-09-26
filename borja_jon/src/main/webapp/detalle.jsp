<%@page import="com.ipartek.formacion.controller.frontoffice.LikesController"%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

<h1>Detalle Video</h1>
<%@include file="includes/mensaje.jsp"%>
	
	<div class="row">
		<div class="col-8">
			<div class="embed-responsive embed-responsive-16by9">
				<iframe class="embed-responsive-item"
				        src="https://www.youtube.com/embed/${video.codigo}" 
				        frameborder="0" 
				        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
				        allowfullscreen>
				</iframe>
			</div>
		</div>
		<div class="col-4">
			<div>
				<h3>${video.nombre}</h3>
				<p><i class="fas fa-user mr-1"></i>${video.usuario.nombre}</p>				  			
				<p><i class="fas fa-tag mr-1"></i>${video.categoria.nombre}</p>	
			</div>
			<c:if test="${sessionScope.usuario != null}">
				<a href="frontoffice/likes?op=<%=LikesController.OP_GUSTA%>&id=${video.id}"> <i class="fas fa-heart"></i> ${video.countLikes} </a>
			</c:if>
			<c:if test="${sessionScope.usuario == null}">
			<!-- Button trigger modal -->
				<button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal">
				<i class="fas fa-heart"></i> ${video.countLikes}
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
				      			<h4>Para dar un like en un video, debe usted estar logeado.</h4>
				      			<p> Si quiere dar el LIKE, pulse a Continuar, sino pulse atras para volver al menu inicial. Gracias</p>
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
	</div>
	
<%@include file="includes/footer.jsp"%>