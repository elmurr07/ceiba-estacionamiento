package com.ceiba.induccion.dominio;

import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;

public interface Pago {

	PagoDto registrarPago(RegistroEntity registroEntity, Double valor);

}
