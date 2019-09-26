package com.ipartek.formacion.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.ipartek.formacion.model.ConnectionManager;
import com.ipartek.formacion.model.pojo.Rol;

public class RolDAO {
	private static final String GET_ALL = "{ call rolGetAll() }";
	private static final String GET_BY_ID = "{ call rolGetById(?) }";
	private static final String INSERT_ROL = "{call rolInsert(?,?) }";
	private static final String UPDATE_ROL = "{call rolUpdate(?,?) }";
	private static final String DELETE_ROL = "{call rolDelete(?)}";

	private static RolDAO INSTANCE = null;

	private RolDAO() {
		super();
	}

	public static synchronized RolDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new RolDAO();
		}

		return INSTANCE;

	}

	public ArrayList<Rol> getAll() {
		ArrayList<Rol> lista = new ArrayList<Rol>();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cst = con.prepareCall(GET_ALL);
				ResultSet rs = cst.executeQuery();) {

			while (rs.next()) {
				lista.add(mapper(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public Rol getById(int id) {
		Rol resul = new Rol();

		try (Connection con = ConnectionManager.getConnection(); CallableStatement cst = con.prepareCall(GET_BY_ID);) {
			cst.setInt(1, id);
			ResultSet rs = cst.executeQuery();
			if (rs.next()) {
				resul = mapper(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resul;
	}

	public Rol crear(Rol pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection(); CallableStatement cst = con.prepareCall(INSERT_ROL);) {

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

	public Rol modificar(Rol pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection(); CallableStatement cst = con.prepareCall(UPDATE_ROL);) {

			cst.setString(1, pojo.getNombre());
			cst.setInt(2, pojo.getId());

			cst.registerOutParameter(2, Types.INTEGER);

			int affectedRows = cst.executeUpdate();
			if (affectedRows == 1) {
				// conseguimos el ID que acabamos de crear
				pojo.setId(cst.getInt(2));

			}

		}

		return pojo;
	}

	public boolean delete(int id) {
		boolean resultado = false;

		try (Connection con = ConnectionManager.getConnection(); CallableStatement cst = con.prepareCall(DELETE_ROL);) {

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

	private Rol mapper(ResultSet rs) throws SQLException {
		Rol r = new Rol();
		r.setId(rs.getInt("id"));
		r.setNombre(rs.getString("nombre"));
		return r;
	}

}
