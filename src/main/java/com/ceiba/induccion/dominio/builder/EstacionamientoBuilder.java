package com.ceiba.induccion.dominio.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.EstacionamientoDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.UsuarioConstants;

public class EstacionamientoBuilder {

	private EstacionamientoBuilder() {
		throw new IllegalStateException("Clase Builder");
	}

	public static EstacionamientoDto toDto(EstacionamientoEntity estacionamientoEntity, VehiculoDto vehiculoDto) {
		EstacionamientoDto estacionamientoDto = null;

		if (estacionamientoEntity != null) {
			estacionamientoDto = new EstacionamientoDto(estacionamientoEntity.getId(), vehiculoDto,
					estacionamientoEntity.getInicio());
		}
		return estacionamientoDto;
	}

	public static EstacionamientoEntity toEntity(VehiculoEntity vehiculoEntity) {
		EstacionamientoEntity estacionamientoEntity = null;

		estacionamientoEntity = new EstacionamientoEntity(vehiculoEntity, new Date(), null,
				UsuarioConstants.USUARIO_SISTEMA, new Date());

		return estacionamientoEntity;
	}

}
