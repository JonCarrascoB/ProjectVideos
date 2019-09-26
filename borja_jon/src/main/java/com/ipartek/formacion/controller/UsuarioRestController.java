package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class UsuarioRestController
 */
@WebServlet("/api/usuarios")
public class UsuarioRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
	private Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Usuario> datos = usuarioDAO.getAll();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		for (int i = 0; i < datos.size(); i++) {
			datos.get(i).setContrasenya("*******");
			usuarios.add(datos.get(i));
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String usuariosJson = gson.toJson(usuarios);

		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/json;charset=UTF-8");
		out.print(usuariosJson);

	}

}
