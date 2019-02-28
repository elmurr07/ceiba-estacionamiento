package com.ceiba.induccion.utilidad;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CalendarUtil {

	private CalendarUtil() {
		throw new IllegalStateException("Clase de utilidad");
	}

	public static LocalDateTime toLocalDateTime(Date fecha) {
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date toDate(LocalDateTime fecha) {
		return Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static long horasEntreFechas(Date fechaInicio, Date fechaFin) {
		LocalDateTime fechaInicioLocal = CalendarUtil.toLocalDateTime(fechaInicio);
		LocalDateTime fechaFinLocal = CalendarUtil.toLocalDateTime(fechaFin);
		return ChronoUnit.HOURS.between(fechaInicioLocal, fechaFinLocal);
	}

}
