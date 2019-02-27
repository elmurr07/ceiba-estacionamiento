package com.ceiba.induccion.dominio.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.UsuarioConstants;

public class VehiculoBuilder {
	private VehiculoBuilder() {
		throw new IllegalStateException("Clase Builder");
	}

	public static VehiculoDto toDto(VehiculoEntity vehiculoEntity) {
		VehiculoDto vehiculoDto = null;

		if (vehiculoEntity != null) {
			vehiculoDto = new VehiculoDto(vehiculoEntity.getPlaca(), vehiculoEntity.getTipo(),
					vehiculoEntity.getCilindraje());
		}
		return vehiculoDto;
	}

	public static VehiculoEntity toEntity(VehiculoDto vehiculoDto) {
		VehiculoEntity vehiculoEntity = null;

		if (vehiculoDto != null) {
			vehiculoEntity = new VehiculoEntity(vehiculoDto.getPlaca(), vehiculoDto.getTipo(),
					vehiculoDto.getCilindraje(), UsuarioConstants.USUARIO_SISTEMA, new Date());
		}

		return vehiculoEntity;
	}
}
