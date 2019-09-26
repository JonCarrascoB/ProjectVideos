package com.ipartek.formacion.model.pojo;

import java.util.Date;

public class Usuario {

	
	private int id;
	private String nombre;
	private String contrasenya;
	private Date fechaCreacion;
	private Date fechaEliminacion;
	private int videoCount;
	private int likesCount;
	private Rol rol;
	
	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.contrasenya = "";
		this.fechaCreacion = null;
		this.fechaEliminacion = null;
		this.videoCount = 0;
		this.likesCount = 0;
		this.rol = new Rol();
		rol.setId(Rol.ROL_USUARIO);
		rol.setNombre(Rol.NOMBRE_USUARIO);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasenya=" + contrasenya + ", fechaCreacion="
				+ fechaCreacion + ", fechaEliminacion=" + fechaEliminacion + ", videoCount=" + videoCount
				+ ", likesCount=" + likesCount + ", rol=" + rol + "]";
	}

	
}
