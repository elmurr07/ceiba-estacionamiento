package com.ceiba.induccion.dominio;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.CalendarUtil;
import com.ceiba.induccion.utilidad.RegistroConstants;

@Component("moto")
public class Moto implements Vehiculo {

	private static final double VALOR_HORA = 500;
	private static final double VALOR_DIA = 4_000;
	private static final double VALOR_ADICIONAL_CILINDRAJE = 2_000;
	private static final long HORAS_PARQUEADERO_DIA = 8;
	private static final long HORAS_DIA = 24;
	private static final long CILINDRAJE_COBRO_ADICIONAL = 500;

	@Override
	public double calcularCosto(VehiculoEntity vehiculo, Date fechaInicio, Date fechaFin) {
		double costo = 0;
		long totalHoras = CalendarUtil.horasEntreFechas(fechaInicio, fechaFin);

		long diasParqueo = totalHoras / HORAS_DIA;
		long horasParqueo = totalHoras % HORAS_DIA;

		costo = diasParqueo * VALOR_DIA;

		if (horasParqueo <= HORAS_PARQUEADERO_DIA) {
			costo += totalHoras * VALOR_HORA;
		} else {
			costo += VALOR_DIA;
		}

		if (vehiculo.getCilindraje() > CILINDRAJE_COBRO_ADICIONAL) {
			costo += VALOR_ADICIONAL_CILINDRAJE;
		}

		return costo;
	}

	@Override
	public boolean existeCupo(int numero) {
		return numero < RegistroConstants.CUPO_MOTOS_PARQUEADERO;
	}

}
