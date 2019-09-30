package com.ipartek.formacion.controller.backoffice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipartek.formacion.model.ConnectionManager;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class MigracionController
 */
@WebServlet("/backoffice/migracion")
public class MigracionController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Log LOG = LogFactory.getLog(MigracionController.class);

	private static final UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
	private static final String FILENAME = "personas.txt";

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

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		File archivo = new File(FILENAME);
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);

		// si se mete en la carpeta resources y el try auto cierra el BufferedReader
		/*
		 * try (BufferedReader br = new BufferedReader(new inputStreamReader (
		 * this.getClass().getResourceAsStream("/"+ archivo))) ){} //aqui se insertaría
		 * todo lo de abajo
		 */

		int leidas = 0;
		int insertadas = 0;
		int erroneas = 0;

		long totalSum = 0;
		long startTime = System.currentTimeMillis();
		double seconds = 0;

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario;
		String linea;

		try (Connection con = ConnectionManager.getConnection()) {
			con.setAutoCommit(false);
			while ((linea = br.readLine()) != null) {
				try {
					String[] us = linea.split(",");
					if (us.length == 7) {
						usuario = new Usuario();
						usuario.setNombre(us[0] + " " + us[1] + " " + us[2]);
						usuario.setContrasenya(us[5]);

						usuarioDAO.migracion(usuario, con);
						if (usuario.getId() != -1) {
							insertadas++;
						}
					} else {
						erroneas++;
						LOG.warn("**** ERROR *****" + linea);
					} // end if us.length
				} catch (Exception e) {
					erroneas++;
					LOG.warn("**** ERROR *****" + linea);
					e.printStackTrace();
				}
				leidas++;
				LOG.debug("Linea: " + linea);
			} // end while
			if (leidas == 10004) {
				con.commit();
				totalSum += (System.currentTimeMillis() - startTime);
				seconds = totalSum / 1000.0;
			} else {
				con.rollback();
			}
		} catch (Exception e) {
			// TODO exceptions
		} // en try connection

		request.setAttribute("leidas", leidas);
		request.setAttribute("erroneas", erroneas);
		request.setAttribute("insertadas", insertadas);
		request.setAttribute("segundos", seconds);
		LOG.info("---------------- proceso migracion terminado ------------------");
		LOG.info(" Leidas: " + leidas);
		LOG.info(" Insertadas: " + insertadas);
		LOG.info(" Erroneas: " + erroneas);
		LOG.info(" Tiempo: " + seconds + " s");
		LOG.info("----------------------------------------------------------------");

		request.getRequestDispatcher("migration/index.jsp").forward(request, response);

	}

}
