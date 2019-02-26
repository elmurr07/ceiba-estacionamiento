package com.ceiba.induccion.dominio;

import java.sql.Date;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

public class VehiculoContext {

	private VehiculoStrategy vehiculoStrategy;

	public VehiculoContext(VehiculoDto vehiculoDto) {
		if (vehiculoDto.getTipo() == TipoVehiculoEnum.CARRO) {
			this.vehiculoStrategy = new CarroStrategy();
		} else {
			this.vehiculoStrategy = new MotoStrategy();
		}
	}

	public boolean validarCupo(int numero) {
		return vehiculoStrategy.existeCupo(numero);
	}

	public double ejecutarCalculo(VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin) {
		return vehiculoStrategy.calcularCosto(vehiculo, fechaInicio, fechaFin);
	}

}
