package com.ceiba.induccion.dominio;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

public interface PagoDominio {

	PagoEntity guardarPago(PagoEntity pagoEntity);

}
