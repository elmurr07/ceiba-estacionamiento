package com.ceiba.induccion.dominio;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@Component
public class VehiculoStrategy {

	@Resource(name = "carro")
	private Vehiculo carro;

	@Resource(name = "moto")
	private Vehiculo moto;

	public boolean validarCupo(TipoVehiculoEnum tipo, int numeroVehiculos) {
		boolean existeCupo;
		if (tipo == TipoVehiculoEnum.CARRO) {
			existeCupo = carro.existeCupo(numeroVehiculos);
		} else {
			existeCupo = moto.existeCupo(numeroVehiculos);
		}
		return existeCupo;
	}

	public double ejecutarCalculo(TipoVehiculoEnum tipo, VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin) {
		double costo;
		if (tipo == TipoVehiculoEnum.CARRO) {
			costo = carro.calcularCosto(vehiculo, fechaInicio, fechaFin);
		} else {
			costo = moto.calcularCosto(vehiculo, fechaInicio, fechaFin);
		}
		return costo;
	}

}
