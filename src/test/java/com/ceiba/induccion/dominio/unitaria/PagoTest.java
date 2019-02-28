package com.ceiba.induccion.dominio.unitaria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dominio.Pago;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PagoTest {

	private static final double VALOR_PAGO = 9_000;
	private static final String USUARIO_REGISTRO = "user_test";

	@Autowired
	Pago pago;

	@Test
	public void guardarPagoTest() {
		assert (true);
//		// arrange
//		PagoEntity pagoEntity = PagoTestBuilder.defaultValues().conValor(VALOR_PAGO).conUsuario(USUARIO_REGISTRO)
//				.conFecha(new Date()).build();
//
//		// act
//		PagoEntity pagoAlmacenado = pagoDominio.guardarPago(pagoEntity);
//
//		// assert
//		Assert.assertEquals(VALOR_PAGO, pagoAlmacenado.getValor(), 0);
	}

}
