package com.ceiba.induccion.dominio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

@Transactional
@Service
public class PagoImpl implements Pago {

	@Override
	public PagoEntity guardarPago(PagoEntity pagoEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
