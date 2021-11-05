package com.javainuse.instance;
import java.util.Date;
import com.javainuse.model.*;
import com.javainuse.service.*;

public class PartidoInstancia {
  private Date fecha;
  private Integer goles_local;
  private Integer goles_visitante;
  private Integer local;
  private Integer visitante;
  private Integer usuario;



	/**
	* Returns value of fecha
	* @return
	*/
	public Date getFecha() {
		return fecha;
	}

	/**
	* Sets new value of fecha
	* @param
	*/
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	* Returns value of goles_local
	* @return
	*/
	public Integer getGoles_local() {
		return goles_local;
	}

	/**
	* Sets new value of goles_local
	* @param
	*/
	public void setGoles_local(Integer goles_local) {
		this.goles_local = goles_local;
	}

	/**
	* Returns value of goles_visitante
	* @return
	*/
	public Integer getGoles_visitante() {
		return goles_visitante;
	}

	/**
	* Sets new value of goles_visitante
	* @param
	*/
	public void setGoles_visitante(Integer goles_visitante) {
		this.goles_visitante = goles_visitante;
	}

	/**
	* Returns value of local
	* @return
	*/
	public Integer getLocal() {
		return local;
	}

	/**
	* Sets new value of local
	* @param
	*/
	public void setLocal(Integer local) {
		this.local = local;
	}

	/**
	* Returns value of visitante
	* @return
	*/
	public Integer getVisitante() {
		return visitante;
	}

	/**
	* Sets new value of visitante
	* @param
	*/
	public void setVisitante(Integer visitante) {
		this.visitante = visitante;
	}

	/**
	* Returns value of usuario
	* @return
	*/
	public Integer getUsuario() {
		return usuario;
	}

	/**
	* Sets new value of usuario
	* @param
	*/
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
}
