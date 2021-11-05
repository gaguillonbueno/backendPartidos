package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.javainuse.instance.PartidoInstancia;


@Entity
@Table(name = "partidos")
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Date fecha;
	@Column
	private Integer goles_local;
	@Column
	private Integer goles_visitante;
	@JoinColumn(name = "local", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Equipo local;
	@JoinColumn(name = "visitante", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Equipo visitante;
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Usuario usuario;

	/**
	* Returns value of id
	* @return
	*/
	public int getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(int id) {
		this.id = id;
	}

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
	public Equipo getLocal() {
		return local;
	}

	/**
	* Sets new value of local
	* @param
	*/
	public void setLocal(Equipo local) {
		this.local = local;
	}

	/**
	* Returns value of visitante
	* @return
	*/
	public Equipo getVisitante() {
		return visitante;
	}

	/**
	* Sets new value of visitante
	* @param
	*/
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	/**
	* Returns value of usuario
	* @return
	*/
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	* Sets new value of usuario
	* @param
	*/
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	/**
	* Default empty Partido constructor
	*/
	public Partido() {
		super();
	}

	/**
	* Default Partido constructor
	*/
	public Partido(int id, Date fecha, Integer goles_local, Integer goles_visitante, Equipo local, Equipo visitante, Usuario usuario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.goles_local = goles_local;
		this.goles_visitante = goles_visitante;
		this.local = local;
		this.visitante = visitante;
		this.usuario = usuario;
	}
}
