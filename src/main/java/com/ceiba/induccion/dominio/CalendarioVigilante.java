package com.ceiba.induccion.dominio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ceiba.induccion.utilidad.CalendarUtil;

@Service
public class CalendarioVigilante {

	public DayOfWeek dayWeekFromDate(Date fecha) {
		LocalDateTime fechaLocal = CalendarUtil.toLocalDateTime(fecha);
		return fechaLocal.getDayOfWeek();
	}

}
