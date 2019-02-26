package com.ceiba.induccion.dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class CalendarioUtil {

	public DayOfWeek dayWeekFromDate(Date fecha) {
		LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return fechaLocal.getDayOfWeek();
	}

}
