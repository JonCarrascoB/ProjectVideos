package com.ipartek.formacion.controller.frontoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.model.dao.VideoDAO;
import com.ipartek.formacion.model.pojo.Usuario;
import com.ipartek.formacion.model.pojo.Video;

/**
 * Servlet implementation class FrontofficeController
 */
@WebServlet("/frontoffice/inicio")
public class FrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private static final VideoDAO videoDAO = VideoDAO.getInstance();

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontofficeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Usuario usuario =  (Usuario) session.getAttribute("usuario");
		int likestotales = 0;
		int id = usuario.getId();
		ArrayList<Video> videos = videoDAO.getAllByUser(id);
		
		for(int i=0; i<videos.size();i++) {
		likestotales=likestotales+videos.get(i).getCountLikes();
		}
		
		request.setAttribute("numeroLikes",likestotales);
		request.setAttribute("listadoVideos", videos);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
