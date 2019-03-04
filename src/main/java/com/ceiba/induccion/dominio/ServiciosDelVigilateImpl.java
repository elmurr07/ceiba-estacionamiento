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
import com.ceiba.induccion.utilidad.RegistroConstants;
import com.ceiba.induccion.utilidad.VehiculoConstants;

@Transactional
@Service
public class ServiciosDelVigilateImpl implements ServiciosDelVigilante {

	@Autowired
	private CalendarioVigilante calendarioVigilante;

	@Autowired
	private Registro registro;

	@Autowired
	private Pago pago;

	@Autowired
	private VehiculoStrategy vehiculoStrategy;

	@Override
	public RegistroDto registrarIngreso(VehiculoDto vehiculoDto) {
		if (tieneRestriccion(vehiculoDto.getPlaca())) {
			throw new RegistroException(RegistroConstants.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA);
		}

		int numeroVehiculos = registro.contarVehiculosEstacionados(vehiculoDto.getTipo());
		if (!vehiculoStrategy.validarCupo(vehiculoDto.getTipo(), numeroVehiculos)) {
			throw new RegistroException(RegistroConstants.MENSAJE_ERROR_NO_HAY_CUPO);
		}

		return registro.registrarIngresoVehiculo(vehiculoDto);
	}

	@Override
	public PagoDto registrarSalida(long idRegistro) {
		RegistroEntity registroEntity = registro.registrarSalidaVehiculo(idRegistro);
		double costoCalculado = vehiculoStrategy.ejecutarCalculo(registroEntity);
		return pago.registrarPago(registroEntity, costoCalculado);
	}

	@Override
	public Boolean tieneRestriccion(String placa) {
		if (placa == null) {
			return Boolean.FALSE;
		}
		DayOfWeek diaHoy = calendarioVigilante.dayWeekFromDate(new Date());
		return placa.charAt(0) == VehiculoConstants.LETRA_RESTRICCION_ACCESO
				&& !(diaHoy == DayOfWeek.SUNDAY || diaHoy == DayOfWeek.MONDAY);
	}

}
