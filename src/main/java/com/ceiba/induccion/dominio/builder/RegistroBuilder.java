package com.ceiba.induccion.dominio.builder;

import java.util.Date;

import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

public class RegistroBuilder {

	public static final String USUARIO_SISTEMA = "usuario_prueba";
	
	private RegistroBuilder() {
		throw new IllegalStateException("Clase Builder");
	}

	public static RegistroDto toDto(RegistroEntity registroEntity, VehiculoDto vehiculoDto) {
		RegistroDto registroDto = null;

		if (registroEntity != null) {
			registroDto = new RegistroDto(registroEntity.getId(), vehiculoDto, registroEntity.getInicio(),
					registroEntity.getFin());
		}
		return registroDto;
	}

	public static RegistroEntity toEntity(VehiculoEntity vehiculoEntity) {
		RegistroEntity registroEntity = null;

		registroEntity = new RegistroEntity(vehiculoEntity, new Date(), null, USUARIO_SISTEMA,
				new Date());

		return registroEntity;
	}

}
