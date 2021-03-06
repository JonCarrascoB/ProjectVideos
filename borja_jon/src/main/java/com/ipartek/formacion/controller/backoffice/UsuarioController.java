package com.ipartek.formacion.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.ipartek.formacion.controller.pojo.Alert;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_INDEX = "backoffice/usuario/index.jsp";
	public static final String VIEW_FRONTOFFICE = "frontoffice/index.jsp";
	public static final String VIEW_FORM = "backoffice/usuario/formulario.jsp";
	public static String view = VIEW_INDEX;

	public static final String OP_LISTARVISIBLES="24";
	public static final String OP_LISTAR = "0";
	public static final String OP_GUARDAR = "23";
	public static final String OP_BUSCAR = "8";
	public static final String OP_NUEVO = "4";
	public static final String OP_ELIMINAR = "hfd3";
	public static final String OP_DETALLE = "13";
	public static final String OP_CAMBIARPASS = "14";
	private static final UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

	private Validator validator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op == null) {
			op = OP_LISTAR;
		}

		switch (op) {
		case OP_DETALLE:
			detalle(request, response);
			break;

		case OP_BUSCAR:
			buscar(request, response);
			break;

		case OP_GUARDAR:
			guardar(request, response);
			break;

		case OP_ELIMINAR:
			eliminar(request, response);
			break;

		case OP_NUEVO:
			nuevo(request, response);
			break;
		case OP_CAMBIARPASS:
			cambiarPass(request, response);
			break;
		case OP_LISTARVISIBLES:
			listarVisible(request, response);
			break;
		default:
			listar(request, response);
			break;
		}

		request.getRequestDispatcher(view).forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("usuarioEditar", new Usuario());
		view = VIEW_FORM;
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) {

		String nombreBuscar = request.getParameter("nombreBuscar");
		request.setAttribute("usuarios", usuarioDAO.getAllByNombre(nombreBuscar));
		view = VIEW_INDEX;
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		if (usuarioDAO.delete(id)) {
			request.setAttribute("mensaje", new Alert("success", "Registro Eliminado"));
		} else {
			request.setAttribute("mensaje", new Alert("danger", "ERROR, no se pudo eliminar"));
		}

		listar(request, response);

	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String contrasenya = request.getParameter("contrasenya");

		Usuario u = new Usuario();
		u.setId(Integer.parseInt(sid));
		u.setNombre(nombre);
		u.setContrasenya(contrasenya);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(u);
		if (violations.isEmpty()) {

			try {

				if (u.getId() == -1) {
					usuarioDAO.crear(u);
				} else {
					usuarioDAO.modificar(u);
				}
				request.setAttribute("mensaje", new Alert("success", "Registro creado con exito"));

			} catch (Exception e) {

				request.setAttribute("mensaje", new Alert("danger", "Tenemos un problema, el codigo ya existe"));
			}

		} else { // hay violaciones de las validaciones

			String mensaje = "";

			for (ConstraintViolation<Usuario> violation : violations) {
				mensaje += violation.getPropertyPath() + ": " + violation.getMessage() + "<br>";
			}
			request.setAttribute("mensaje", new Alert("warning", mensaje));
		}
		request.setAttribute("usuarioEditar", u);
		view = VIEW_FORM;

	}
	
	
	private void cambiarPass(HttpServletRequest request, HttpServletResponse response) {
		
		String contrasenya = request.getParameter("contrasenya");

		HttpSession session = request.getSession();
		Usuario u = (Usuario) session.getAttribute("usuario");
		u.setContrasenya(contrasenya);

		try {

				usuarioDAO.modificar(u);
				request.setAttribute("mensaje", new Alert("success", "Contraseña cambiada con existo"));
			
		} catch (Exception e) {

			request.setAttribute("mensaje", new Alert("danger", "No se ha podido cambiar la contraseña"));
		}
		
		view = VIEW_FRONTOFFICE;

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList <Usuario> usuarios = usuarioDAO.getAll();
		
		request.setAttribute("usuarios", listarTodo(usuarios));
		view = VIEW_INDEX;

	}
	
	/**
	 * Lista los usuarios activos o los borrados
	 * @param request
	 * @param response
	 */
	private void listarVisible(HttpServletRequest request, HttpServletResponse response) {
		
		boolean visible = Boolean.parseBoolean(request.getParameter("visible"));
		
		ArrayList <Usuario> usuariosVisibles = usuarioDAO.getAllVisible(visible);
		request.setAttribute("usuarios", listarTodo(usuariosVisibles) );
		view = VIEW_INDEX;

	}
	

	private void detalle(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		Usuario u = usuarioDAO.getById(id);
		request.setAttribute("usuarioEditar", u);
		view = VIEW_FORM;

	}
	
	private ArrayList<Usuario> listarTodo(ArrayList<Usuario> usuarios){
		
		ArrayList <Usuario> listaUsers= new ArrayList<Usuario>();
		Usuario user = new Usuario();
		Usuario userdepaso = new Usuario();
		for(int i=0; i<usuarios.size(); i++) {
			userdepaso = usuarios.get(i);
			user = (Usuario) usuarioDAO.getCountsById(usuarios.get(i).getId());
			userdepaso.setLikesCount(user.getLikesCount());
			userdepaso.setVideoCount(user.getVideoCount());
		listaUsers.add(userdepaso);
		}
		
		return listaUsers;
	}

}
