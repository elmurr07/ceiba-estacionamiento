package com.ceiba.induccion.utilidad;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CalendarUtil {

	private CalendarUtil() {
		throw new IllegalStateException("Clase de utilidad");
	}

	public static LocalDate dateToLocalDate(Date fecha) {
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date localDateToDate(LocalDate fecha) {
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static long horasEntreFechas(Date fechaInicio, Date fechaFin) {
		LocalDate fechaInicioLocal = CalendarUtil.dateToLocalDate(fechaInicio);
		LocalDate fechaFinLocal = CalendarUtil.dateToLocalDate(fechaFin);
		return ChronoUnit.HOURS.between(fechaInicioLocal, fechaFinLocal);
	}

}
