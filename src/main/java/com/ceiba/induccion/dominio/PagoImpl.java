package com.ceiba.induccion.dominio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.builder.PagoBuilder;
import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.persistencia.repositorio.PagoRepositorio;
import com.ceiba.induccion.utilidad.UsuarioConstants;

@Transactional
@Service
public class PagoImpl implements Pago {

	@Autowired
	private PagoRepositorio pagoRepositorio;

	@Override
	public PagoDto registrarPago(RegistroEntity registroEntity, Double valor) {
		PagoDto pagoRetorno = null;
		if (registroEntity != null) {
			PagoEntity pagoEntity = new PagoEntity(valor, registroEntity, UsuarioConstants.USUARIO_SISTEMA, new Date());
			pagoEntity = pagoRepositorio.save(pagoEntity);
			pagoRetorno = PagoBuilder.toDto(pagoEntity);
		}
		return pagoRetorno;
	}

}
