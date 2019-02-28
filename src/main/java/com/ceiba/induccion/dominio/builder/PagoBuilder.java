package com.ceiba.induccion.dominio.builder;

import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;

public class PagoBuilder {

	private PagoBuilder() {
		throw new IllegalStateException("Clase Builder");
	}

	public static PagoDto toDto(PagoEntity pagoEntity) {
		PagoDto pagoRetorno = null;
		if (pagoEntity != null) {
			pagoRetorno = new PagoDto(pagoEntity.getId(), pagoEntity.getValor(),
					pagoEntity.getRegistroEntity().getVehiculo().getPlaca(), pagoEntity.getRegistroEntity().getInicio(),
					pagoEntity.getRegistroEntity().getFin());
		}
		return pagoRetorno;
	}

}
