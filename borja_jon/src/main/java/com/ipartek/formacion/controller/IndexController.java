package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;

import com.ipartek.formacion.model.dao.CategoriaDAO;
import com.ipartek.formacion.model.dao.LikeDAO;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.dao.VideoDAO;
import com.ipartek.formacion.model.pojo.Video;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/index")
public class IndexController extends ControladorCrud {
	private static final long serialVersionUID = 1L;

	public static final String VIEW_INDEX = "index.jsp";
	public static final String VIEW_FORM = "detalle.jsp";
	public static String view = VIEW_INDEX;

	public static final String OP_LISTAR = "0";
	public static final String OP_BUSCAR = "1";
	public static final String OP_DETALLE = "2";

	private static VideoDAO videoDAO = VideoDAO.getInstance();
	private static UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
	private static CategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
	private static LikeDAO likeDAO = LikeDAO.getInstance();

	private Validator validator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("op");
		if (op == null) {
			op = OP_LISTAR;
		}

		switch (op) {
		case OP_BUSCAR:
			buscar(request, response);
			break;
		case OP_DETALLE:
			mostrarFormulario(request, response);
			break;

		default:
			listar(request, response);
			break;
		}

		request.getRequestDispatcher(view).forward(request, response);
	}

	@Override
	protected void listar(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("videosVisibles", videoDAO.getAllVisible(true));

		view = VIEW_INDEX;

	}

	@Override
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void guardar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void buscar(HttpServletRequest request, HttpServletResponse response) {

		String nombreBuscar = request.getParameter("nombreBuscar");
		request.setAttribute("videosVisibles", videoDAO.getAllByNombre(nombreBuscar));
		view = VIEW_INDEX;

	}

	@Override
	protected void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Video v = videoDAO.getById(id);
		request.setAttribute("video", v);

		request.setAttribute("usuarios", usuarioDAO.getAll());
		request.setAttribute("categorias", categoriaDAO.getAll());
		request.setAttribute("likes", likeDAO.getCountLikes(id));

		view = VIEW_FORM;

		HttpSession session = request.getSession();
		HashMap<Integer, Video> videosVistos = (HashMap<Integer, Video>) session.getAttribute("videosVistos");
		if (videosVistos == null) {
			videosVistos = new HashMap<Integer, Video>();
		}
		videosVistos.put(v.getId(), v);
		session.setAttribute("videosVistos", videosVistos);

	}

}
