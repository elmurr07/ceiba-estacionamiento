package com.ceiba.induccion.dominio.dto;

import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public class VehiculoDto {

	private long id;
	private String placa;
	private TipoVehiculoEnum tipo;
	private Integer cilindraje;

	public VehiculoDto(String placa, TipoVehiculoEnum tipo, Integer cilindraje) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVehiculoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculoEnum tipo) {
		this.tipo = tipo;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

}
