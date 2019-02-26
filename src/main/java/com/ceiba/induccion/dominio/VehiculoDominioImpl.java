package com.ceiba.induccion.dominio;

import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;

@Transactional
public class VehiculoDominioImpl implements VehiculoDominio {

	@Override
	public VehiculoEntity crearVehiculo(VehiculoDto vehiculoDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
