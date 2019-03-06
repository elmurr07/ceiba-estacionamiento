package com.ceiba.induccion.dominio;

import java.time.DayOfWeek;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.RegistroException;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;

@Transactional
@Service
public class ServiciosDelVigilanteImpl implements ServiciosDelVigilante {

	public static final String MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA = "El vehiculo no puede ingresar en semana";
	public static final String MENSAJE_ERROR_NO_HAY_CUPO = "El estacionamiento no cuenta con cupos para el vehiculo";
	public static final String MENSAJE_ERROR_VEHICULO_ESTACIONADO = "El vehiculo ya se encuentra estacionado";

	@Autowired
	private CalendarioVigilante calendarioVigilante;

	@Autowired
	private LibretaVigilante libretaVigilante;

	@Autowired
	private ReglasEstacionamiento reglasEstacionamiento;

	@Override
	public RegistroDto registrarIngreso(VehiculoDto vehiculoDto) {
		if (tieneRestriccion(vehiculoDto.getPlaca())) {
			throw new RegistroException(MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA);
		}

		int numeroVehiculos = libretaVigilante.contarVehiculosEstacionados(vehiculoDto.getTipo());
		if (!reglasEstacionamiento.validarCupo(vehiculoDto.getTipo(), numeroVehiculos)) {
			throw new RegistroException(MENSAJE_ERROR_NO_HAY_CUPO);
		}

		if (libretaVigilante.existeVehiculoEnEstacionamiento(vehiculoDto.getPlaca())) {
			throw new RegistroException(MENSAJE_ERROR_VEHICULO_ESTACIONADO);
		}

		return libretaVigilante.registrarIngresoVehiculo(vehiculoDto);
	}

	@Override
	public PagoDto registrarSalida(long idRegistro) {
		RegistroEntity registroEntity = libretaVigilante.registrarSalidaVehiculo(idRegistro);
		Double costoCalculado = reglasEstacionamiento.ejecutarCalculo(registroEntity);
		return libretaVigilante.registrarPago(registroEntity, costoCalculado);
	}

	@Override
	public Boolean tieneRestriccion(String placa) {
		if (placa == null) {
			return Boolean.FALSE;
		}
		DayOfWeek diaHoy = calendarioVigilante.dayWeekFromDate(new Date());
		return placa.charAt(0) == ReglasEstacionamiento.LETRA_RESTRICCION_ACCESO
				&& !(diaHoy == DayOfWeek.SUNDAY || diaHoy == DayOfWeek.MONDAY);
	}

}
