package com.ceiba.induccion.dominio;

import java.time.DayOfWeek;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.EstacionamientoException;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.persistencia.repositorio.EstacionamientoRepositorio;
import com.ceiba.induccion.persistencia.repositorio.VehiculoRepositorio;
import com.ceiba.induccion.utilidad.EstacionamientoConstants;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;
import com.ceiba.induccion.utilidad.UsuarioConstants;
import com.ceiba.induccion.utilidad.VehiculoConstants;

@Transactional
@Service
public class EstacionamientoDominioImpl implements EstacionamientoDominio {

	@Autowired
	private CalendarioUtil calendarioUtil;

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Autowired
	private EstacionamientoRepositorio estacionamientoRepositorio;

	@Autowired
	private VehiculoContext vehiculoContext;

	@Override
	public Integer contarVehiculos(TipoVehiculoEnum tipoVehiculo) {
		return estacionamientoRepositorio.contarVehiculosEstacionados(tipoVehiculo);
	}

	@Override
	public Boolean existeCupoMoto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeCupoCarro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarIngreso(VehiculoDto vehiculoDto) {
		if (tieneRestriccion(vehiculoDto.getPlaca())) {
			throw new EstacionamientoException(EstacionamientoConstants.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA);
		}

		if (vehiculoDto.getTipo() == TipoVehiculoEnum.CARRO) {
			vehiculoContext.setVehiculoStrategy(new CarroStrategy());
		} else {
			vehiculoContext.setVehiculoStrategy(new MotoStrategy());
		}

		int numeroVehiculos = contarVehiculos(vehiculoDto.getTipo());
		if (!vehiculoContext.validarCupo(numeroVehiculos)) {
			throw new EstacionamientoException(EstacionamientoConstants.MENSAJE_ERROR_NO_HAY_CUPO);
		}

		VehiculoEntity vehiculoEntity = new VehiculoEntity(vehiculoDto.getPlaca(), vehiculoDto.getTipo(),
				vehiculoDto.getCilindraje(), UsuarioConstants.USUARIO_SISTEMA, new Date());
		EstacionamientoEntity estacionamientoEntity = new EstacionamientoEntity(vehiculoEntity, new Date(), null,
				UsuarioConstants.USUARIO_SISTEMA, new Date());
		vehiculoRepositorio.save(vehiculoEntity);
		estacionamientoRepositorio.save(estacionamientoEntity);
	}

	@Override
	public Boolean tieneRestriccion(String placa) {
		if (placa == null) {
			return Boolean.FALSE;
		}
		DayOfWeek diaHoy = calendarioUtil.dayWeekFromDate(new Date());
		return placa.charAt(0) == VehiculoConstants.LETRA_RESTRICCION_ACCESO
				&& (diaHoy == DayOfWeek.SUNDAY || diaHoy == DayOfWeek.MONDAY);
	}

}
