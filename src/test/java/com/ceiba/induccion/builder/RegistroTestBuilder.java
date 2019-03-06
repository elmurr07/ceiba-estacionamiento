package com.ceiba.induccion.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public class RegistroTestBuilder {

	private VehiculoDto vehiculoDto;
	private VehiculoEntity vehiculoEntity;
	private Date inicio;
	private Date fin;
	private String usuario;
	private Date fecha;

	private RegistroTestBuilder() {
		super();
	}

	public static RegistroTestBuilder defaultValues() {
		return new RegistroTestBuilder();
	}

	public RegistroTestBuilder conVehiculoDto(VehiculoDto vehiculo) {
		this.vehiculoDto = vehiculo;
		return this;
	}

	public RegistroTestBuilder conVehiculoEntity(VehiculoEntity vehiculoEntity) {
		this.vehiculoEntity = vehiculoEntity;
		return this;
	}

	public RegistroTestBuilder conInicio(Date inicio) {
		this.inicio = inicio;
		return this;
	}

	public RegistroTestBuilder conFin(Date fin) {
		this.fin = fin;
		return this;
	}

	public RegistroTestBuilder conUsuario(String usuario) {
		this.usuario = usuario;
		return this;
	}

	public RegistroTestBuilder conFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public RegistroEntity build() {
		return new RegistroEntity(vehiculoEntity, inicio, fin, usuario, fecha);
	}

	public RegistroDto buildDto() {
		return new RegistroDto(0, vehiculoDto, inicio, fin);
	}

}
