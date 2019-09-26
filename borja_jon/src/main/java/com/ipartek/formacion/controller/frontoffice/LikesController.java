package com.ipartek.formacion.controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;

import com.ipartek.formacion.model.dao.LikeDAO;
import com.ipartek.formacion.model.dao.VideoDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class LikesController
 */
@WebServlet("/frontoffice/likes")
public class LikesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VIEW_INDEX = "/index";
	public static String view = VIEW_INDEX;
	
	public static final String OP_LISTAR = "0";
	public static final String OP_GUSTA = "1";
	
	private static VideoDAO videoDAO = VideoDAO.getInstance();
	private static LikeDAO likeDAO = LikeDAO.getInstance();

	private Validator validator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op == null) {
			op = OP_LISTAR;
		}

		switch (op) {
		case OP_GUSTA:
			gustar(request,response);
			break;
		default:
			listar(request, response);
			break;
		}
		request.getRequestDispatcher(view).forward(request, response);
	}


	private void listar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void gustar(HttpServletRequest request, HttpServletResponse response) {

		int idVideo = Integer.parseInt(request.getParameter("id"));
		int idUsuario = -1;
		
		HttpSession session = request.getSession();
		Usuario user =  (Usuario) session.getAttribute("usuario");
		idUsuario = user.getId();
		
		likeDAO.insertLike(idUsuario, idVideo);
		
		request.setAttribute("videosVisibles", videoDAO.getAllVisible(true));
		view = VIEW_INDEX;
		
	}
	
}
