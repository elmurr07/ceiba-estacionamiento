package com.ceiba.induccion.dominio;

import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.persistencia.entidad.PagoEntity;

@Transactional
public class PagoDominioImpl implements PagoDominio {

	@Override
	public PagoEntity guardarPago(PagoEntity pagoEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
