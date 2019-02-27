package com.ceiba.induccion.dominio;

import java.time.DayOfWeek;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.dto.EstacionamientoDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.EstacionamientoException;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;
import com.ceiba.induccion.persistencia.repositorio.EstacionamientoRepositorio;
import com.ceiba.induccion.utilidad.EstacionamientoConstants;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;
import com.ceiba.induccion.utilidad.VehiculoConstants;

@Transactional
@Service
public class ServiciosDelVigilateImpl implements ServiciosDelVigilante {

	@Autowired
	private CalendarioVigilante calendarioVigilante;

	@Autowired
	private EstacionamientoRepositorio estacionamientoRepositorio;

	@Autowired
	private Registro registro;

	@Autowired
	private VehiculoStrategy vehiculoStrategy;

	@Override
	public EstacionamientoDto registrarIngreso(VehiculoDto vehiculoDto) {
		if (tieneRestriccion(vehiculoDto.getPlaca())) {
			throw new EstacionamientoException(EstacionamientoConstants.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA);
		}

		int numeroVehiculos = contarVehiculos(vehiculoDto.getTipo());
		if (!vehiculoStrategy.validarCupo(vehiculoDto.getTipo(), numeroVehiculos)) {
			throw new EstacionamientoException(EstacionamientoConstants.MENSAJE_ERROR_NO_HAY_CUPO);
		}

		return registro.registrarVehiculo(vehiculoDto);
	}

	@Override
	public PagoEntity registrarSalida(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean tieneRestriccion(String placa) {
		if (placa == null) {
			return Boolean.FALSE;
		}
		DayOfWeek diaHoy = calendarioVigilante.dayWeekFromDate(new Date());
		return placa.charAt(0) == VehiculoConstants.LETRA_RESTRICCION_ACCESO
				&& (diaHoy == DayOfWeek.SUNDAY || diaHoy == DayOfWeek.MONDAY);
	}

	@Override
	public Integer contarVehiculos(TipoVehiculoEnum tipoVehiculo) {
		return estacionamientoRepositorio.contarVehiculosEstacionados(tipoVehiculo);
	}

}
