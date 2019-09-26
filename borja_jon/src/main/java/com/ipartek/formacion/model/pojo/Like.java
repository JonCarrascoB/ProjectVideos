package com.ipartek.formacion.model.pojo;

import java.sql.Date;

public class Like {
	
	private Date fecha;
	
	private Usuario usuario;
	private Video video;
	
	public Like() {
		super();
		this.fecha = null;
		this.usuario = new Usuario();
		this.video = new Video();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "Like [fecha=" + fecha + ", usuario=" + usuario + ", video=" + video + "]";
	}
	
	
	
	

}
