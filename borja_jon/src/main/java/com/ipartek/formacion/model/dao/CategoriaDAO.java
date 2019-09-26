package com.ipartek.formacion.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartek.formacion.model.ConnectionManager;
import com.ipartek.formacion.model.pojo.Categoria;

public class CategoriaDAO {

	private static CategoriaDAO INSTANCE = null;

	private static final String GET_ALL = "{ CALL categoriaGetAll() }";
	private static final String GET_BY_ID = "{ CALL categoriaGetById(?) }";
	private static final String INSERT = "{ CALL categoriaInsert(?,?) }";
	private static final String UPDATE = "{ CALL categoriaUpdate(?,?) }";
	private static final String DELETE = "{ CALL categoriaDelete(?) }";
	private static final String GET_ALL_BY_NOMBRE = "{CALL categoriaSearch(?)}";
	

	private CategoriaDAO() {
		super();
	}

	public static synchronized CategoriaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CategoriaDAO();
		}

		return INSTANCE;

	}


	public ArrayList<Categoria> getAll() {

		ArrayList<Categoria> lista = new ArrayList<Categoria>();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(GET_ALL);
				ResultSet rs = cst.executeQuery()){
				
			while (rs.next()) {				
				lista.add(mapper(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public Categoria getById(int id) {
		Categoria categoria = new Categoria();
		

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(GET_BY_ID)) {

			// sustituyo la 1º ? por la variable id
			cst.setInt(1, id);

			try (ResultSet rs = cst.executeQuery()) {
				if (rs.next()) {
					categoria = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoria;
	}
	
	/**
	 * Metodo para buscar categorias por nombre.
	 * @param nombre
	 * @return lista
	 */
	public ArrayList<Categoria> getAllByNombre(String nombre) {
		ArrayList<Categoria> lista = new ArrayList<Categoria>();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(GET_ALL_BY_NOMBRE);) {

			cst.setString(1, "%" + nombre + "%");

			try (ResultSet rs = cst.executeQuery()) {

				while (rs.next()) {
					lista.add(mapper(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	
	
	public Categoria crear(Categoria pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(INSERT);) {

			cst.setString(1, pojo.getNombre());
			cst.registerOutParameter(2, Types.INTEGER);
			
			int affectedRows = cst.executeUpdate();
			if (affectedRows == 1) {
				// conseguimos el ID que acabamos de crear
					pojo.setId(cst.getInt(2));
			}

		}

		return pojo;
	}
	
	public Categoria modificar(Categoria pojo) throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(UPDATE);) {

			cst.setString(1, pojo.getNombre());
			cst.setInt(2, pojo.getId());
			
			cst.registerOutParameter(2, Types.INTEGER);

			int affectedRows = cst.executeUpdate();
			if (affectedRows == 1) {
				pojo.setId(cst.getInt(2));	
			}
		}
		return pojo;
	}
	
	public boolean delete(int id) {
		boolean resultado = false;

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(DELETE);) {

			cst.setInt(1, id);

			int affetedRows = cst.executeUpdate();
			if (affetedRows == 1) {
				resultado = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}


	
	private Categoria mapper(ResultSet rs) throws SQLException {
		Categoria c = new Categoria();
		c.setId(rs.getInt("id"));
		c.setNombre(rs.getString("nombre"));		
		return c;
	}

}
