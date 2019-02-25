package com.ceiba.induccion.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Estacionamiento")
public class EstacionamientoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "vehiculo")
	private VehiculoEntity vehiculo;

	@Column(nullable = false)
	private Date inicio;

	@Column
	private Date fin;

	@Column
	private String usuario;

	@Column
	private Date fecha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
