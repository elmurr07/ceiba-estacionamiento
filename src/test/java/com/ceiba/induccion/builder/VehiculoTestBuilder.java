package com.ceiba.induccion.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public class VehiculoTestBuilder {

	private String placa;
	private TipoVehiculoEnum tipo;
	private Integer cilindraje;
	private String usuario;
	private Date fecha;

	private VehiculoTestBuilder() {
		super();
	}

	public static VehiculoTestBuilder defaultValues() {
		return new VehiculoTestBuilder();
	}

	public VehiculoTestBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoTestBuilder conTipo(TipoVehiculoEnum tipo) {
		this.tipo = tipo;
		return this;
	}

	public VehiculoTestBuilder conCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoTestBuilder setUsuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public VehiculoTestBuilder setFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public VehiculoEntity build() {
		return new VehiculoEntity(this.placa, this.tipo, this.cilindraje, this.usuario, this.fecha);
	}

	public VehiculoDto buildDto() {
		return new VehiculoDto(this.placa, this.tipo, this.cilindraje);
	}

}
