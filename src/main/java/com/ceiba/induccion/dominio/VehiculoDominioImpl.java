package com.ceiba.induccion.dominio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.persistencia.repositorio.VehiculoRepositorio;
import com.ceiba.induccion.utilidad.UsuarioConstants;

@Service
@Transactional
public class VehiculoDominioImpl implements VehiculoDominio {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Override
	public VehiculoEntity crearVehiculo(VehiculoDto vehiculoDto) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity(vehiculoDto.getPlaca(), vehiculoDto.getTipo(),
				vehiculoDto.getCilindraje(), UsuarioConstants.USUARIO_SISTEMA, new Date());
		return vehiculoRepositorio.save(vehiculoEntity);
	}

}
