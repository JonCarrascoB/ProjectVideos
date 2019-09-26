package com.ipartek.formacion.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.controller.ControladorCrud;
import com.ipartek.formacion.controller.pojo.Alert;
import com.ipartek.formacion.model.dao.RolDAO;
import com.ipartek.formacion.model.pojo.Rol;

/**
 * Servlet implementation class RolController
 */
@WebServlet("/backoffice/rol")
public class RolController extends ControladorCrud  {
private static final long serialVersionUID = 1L;
	
	public static final String VIEW_INDEX = "rol/index.jsp";
	public static final String VIEW_FORM  = "rol/formulario.jsp";
	
	private static final RolDAO rolDAO = RolDAO.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RolController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	
	@Override
	protected void listar(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Rol> lista = rolDAO.getAll();;
		request.setAttribute("roles", lista);
		view = VIEW_INDEX;
		
	}

	@Override
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("id");
		
		
		Rol r = new Rol();
		r.setId(Integer.parseInt(sid));
		
		
		rolDAO.delete(r.getId());
		view = VIEW_INDEX;
		
	}

	@Override
	protected void guardar(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		
		Rol r = new Rol();
		r.setId(Integer.parseInt(sid));
		r.setNombre(nombre);
	
			try {

				if (r.getId() == -1) {
					rolDAO.crear(r);
				} else {
					rolDAO.modificar(r);
				}
				request.setAttribute("mensaje", new Alert("success", "Registro creado con exito"));

			} catch (Exception e) {

				request.setAttribute("mensaje", new Alert("danger", "Tenemos un problema, el codigo ya existe"));
			}

		
		request.setAttribute("rolEditar", r);
		view = VIEW_FORM;
		
	}

	@Override
	protected void buscar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) {
	
		String sid = request.getParameter("id");
		Rol rol = new Rol();
		if(sid != null) {
			rol.setId(Integer.parseInt(sid));
		}
		
			if(rol.getId()==-1) {
				request.setAttribute("rolEditar", rol);
				view=VIEW_FORM;
			}else {
				rol = rolDAO.getById(rol.getId());
				request.setAttribute("rolEditar", rol);
				view=VIEW_FORM;
			}
		
	}
	
	

}
