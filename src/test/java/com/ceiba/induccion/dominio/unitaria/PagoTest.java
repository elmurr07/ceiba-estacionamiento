package com.ceiba.induccion.dominio.unitaria;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.builder.PagoTestBuilder;
import com.ceiba.induccion.dominio.PagoDominio;
import com.ceiba.induccion.persistencia.entidad.PagoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PagoTest {

	private static final double VALOR_PAGO = 9000;
	private static final String USUARIO_REGISTRO = "user_test";
	
	@Autowired
	PagoDominio pagoDominio;

	@Test
	public void guardarPagoTest() {
		// arrange
		PagoEntity pagoEntity = PagoTestBuilder.defaultValues().conValor(VALOR_PAGO).conUsuario(USUARIO_REGISTRO)
				.conFecha(new Date()).build();

		// act
		pagoDominio.guardarPago(pagoEntity);

		// assert
		assert(true);
	}

}
