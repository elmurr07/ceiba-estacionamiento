package com.ceiba.induccion.dominio.integracion;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.Pago;
import com.ceiba.induccion.dominio.Registro;
import com.ceiba.induccion.dominio.dto.PagoDto;
import com.ceiba.induccion.dominio.dto.RegistroDto;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.persistencia.entidad.RegistroEntity;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PagoTest {

	private static final String PLACA_VEHICULO_SIN_RESTRICCION = "FFH134";
	private static final double VALOR_PAGO = 9_000;

	@Autowired
	Pago pago;

	@Autowired
	Registro registro;

	@Test
	public void valorCorrectoRegistrarPagoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();

		// act
		RegistroDto registroDto = registro.registrarIngresoVehiculo(vehiculoDto);
		RegistroEntity registroEntity = registro.registrarSalidaVehiculo(registroDto.getId());
		PagoDto pagoAlmacenado = pago.registrarPago(registroEntity, VALOR_PAGO);

		// assert
		Assert.assertEquals(VALOR_PAGO, pagoAlmacenado.getValor(), 0);
	}

}
