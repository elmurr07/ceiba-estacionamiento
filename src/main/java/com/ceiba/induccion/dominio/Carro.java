package com.ceiba.induccion.dominio;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.utilidad.CalendarUtil;
import com.ceiba.induccion.utilidad.RegistroConstants;

@Component("carro")
public class Carro implements Vehiculo {

	private static final double VALOR_HORA = 1_000;
	private static final double VALOR_DIA = 8_000;
	private static final long HORAS_PARQUEADERO_DIA = 8;
	private static final long HORAS_DIA = 24;

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

		return costo;
	}

	@Override
	public boolean existeCupo(int numero) {
		return numero < RegistroConstants.CUPO_CARROS_PARQUEADERO;
	}

}
