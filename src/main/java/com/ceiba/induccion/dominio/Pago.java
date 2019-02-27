package com.ceiba.induccion.dominio;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

public interface Pago {

	PagoEntity guardarPago(PagoEntity pagoEntity);

}
