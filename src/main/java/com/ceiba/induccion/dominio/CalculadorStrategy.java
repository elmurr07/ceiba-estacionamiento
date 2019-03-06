package com.ceiba.induccion.dominio;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@Component
public class CalculadorStrategy {

	@Resource(name = "carro")
	private Calculador carro;

	@Resource(name = "moto")
	private Calculador moto;

	public boolean validarCupo(TipoVehiculoEnum tipo, int numeroVehiculos) {
		boolean existeCupo;
		if (tipo == TipoVehiculoEnum.CARRO) {
			existeCupo = carro.existeCupo(numeroVehiculos);
		} else {
			existeCupo = moto.existeCupo(numeroVehiculos);
		}
		return existeCupo;
	}

	public Double ejecutarCalculo(RegistroEntity registroEntity) {
		Double costo = null;
		if (registroEntity != null) {
			if (registroEntity.getVehiculo().getTipo() == TipoVehiculoEnum.CARRO) {
				costo = carro.calcularCosto(registroEntity);
			} else {
				costo = moto.calcularCosto(registroEntity);
			}
		}
		return costo;
	}

}
