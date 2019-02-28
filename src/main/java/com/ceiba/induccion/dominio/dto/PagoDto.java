package com.ceiba.induccion.dominio.dto;

import java.util.Date;

public class PagoDto {

	private long id;
	private double valor;
	private String placa;
	private Date fechaInicio;
	private Date fechaFin;

	public PagoDto() {
		super();
	}

	public PagoDto(long id, double valor, String placa, Date fechaInicio, Date fechaFin) {
		super();
		this.id = id;
		this.valor = valor;
		this.placa = placa;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
