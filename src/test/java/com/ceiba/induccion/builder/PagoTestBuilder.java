package com.ceiba.induccion.builder;

import java.util.Date;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

public class PagoTestBuilder {

	private double valor;
	private String usuario;
	private Date fecha;

	private PagoTestBuilder() {
		super();
	}

	public static PagoTestBuilder defaultValues() {
		return new PagoTestBuilder();
	}

	public PagoTestBuilder conValor(double valor) {
		this.valor = valor;
		return this;
	}

	public PagoTestBuilder conUsuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public PagoTestBuilder conFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public PagoEntity build() {
		return new PagoEntity(this.valor, this.usuario, this.fecha);
	}

}
