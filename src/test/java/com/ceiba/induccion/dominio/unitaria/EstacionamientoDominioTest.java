package com.ceiba.induccion.dominio.unitaria;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.builder.VehiculoTestBuilder;
import com.ceiba.induccion.dominio.CalendarioUtil;
import com.ceiba.induccion.dominio.EstacionamientoDominioImpl;
import com.ceiba.induccion.dominio.VehiculoContext;
import com.ceiba.induccion.dominio.VehiculoDominio;
import com.ceiba.induccion.dominio.builder.EstacionamientoBuilder;
import com.ceiba.induccion.dominio.builder.VehiculoBuilder;
import com.ceiba.induccion.dominio.dto.VehiculoDto;
import com.ceiba.induccion.dominio.excepcion.EstacionamientoException;
import com.ceiba.induccion.persistencia.entidad.EstacionamientoEntity;
import com.ceiba.induccion.persistencia.entidad.VehiculoEntity;
import com.ceiba.induccion.persistencia.repositorio.EstacionamientoRepositorio;
import com.ceiba.induccion.utilidad.EstacionamientoConstants;
import com.ceiba.induccion.utilidad.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EstacionamientoDominioTest {

	private static final String PLACA_VEHICULO_CON_RESTRICCION = "ALG139";
	private static final String PLACA_VEHICULO_SIN_RESTRICCION = "NNL677";
	private static final Integer CILINDRAJE_MOTO = 550;
	private static final int CARROS_EN_PARQUEADERO = 5;
	private static final int MOTOS_EN_PARQUEADERO = 3;

	@Mock
	private CalendarioUtil calendarioUtil;

	@Mock
	private EstacionamientoRepositorio estacionamientoRepositorio;

	@Mock
	private VehiculoContext vehiculoContext;

	@Mock
	private VehiculoDominio vehiculoDominio;

	@InjectMocks
	private EstacionamientoDominioImpl estacionamientoDominio;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(EstacionamientoDominioTest.class);
	}

	@Test
	public void registrarMotoConCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		when(estacionamientoRepositorio.contarVehiculosEstacionados(TipoVehiculoEnum.MOTO))
				.thenReturn(MOTOS_EN_PARQUEADERO);

		when(vehiculoContext.validarCupo(MOTOS_EN_PARQUEADERO)).thenReturn(Boolean.TRUE);

		VehiculoEntity vehiculoEntity = VehiculoBuilder.toEntity(vehiculoDto);
		when(vehiculoDominio.crearVehiculo(any())).thenReturn(vehiculoEntity);

		EstacionamientoEntity estacionamientoEntity = EstacionamientoBuilder.toEntity(vehiculoEntity);
		when(estacionamientoRepositorio.save(any())).thenReturn(estacionamientoEntity);

		// act
		EstacionamientoEntity estacionamientoAlmacenado = estacionamientoDominio.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION, estacionamientoAlmacenado.getVehiculo().getPlaca());
		Assert.assertEquals(CILINDRAJE_MOTO, estacionamientoAlmacenado.getVehiculo().getCilindraje());
		Assert.assertEquals(TipoVehiculoEnum.MOTO, estacionamientoAlmacenado.getVehiculo().getTipo());
	}

	@Test
	public void registrarCarroConCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();

		when(estacionamientoRepositorio.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO))
				.thenReturn(CARROS_EN_PARQUEADERO);

		when(vehiculoContext.validarCupo(CARROS_EN_PARQUEADERO)).thenReturn(Boolean.TRUE);

		VehiculoEntity vehiculoEntity = VehiculoBuilder.toEntity(vehiculoDto);
		when(vehiculoDominio.crearVehiculo(any())).thenReturn(vehiculoEntity);

		EstacionamientoEntity estacionamientoEntity = EstacionamientoBuilder.toEntity(vehiculoEntity);
		when(estacionamientoRepositorio.save(any())).thenReturn(estacionamientoEntity);

		// act
		EstacionamientoEntity estacionamientoAlmacenado = estacionamientoDominio.registrarIngreso(vehiculoDto);

		// assert
		Assert.assertEquals(PLACA_VEHICULO_SIN_RESTRICCION, estacionamientoAlmacenado.getVehiculo().getPlaca());
		Assert.assertEquals(TipoVehiculoEnum.CARRO, estacionamientoAlmacenado.getVehiculo().getTipo());
	}

	@Test
	public void registrarMotoSinCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		when(estacionamientoRepositorio.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO))
				.thenReturn(EstacionamientoConstants.CUPO_MOTOS_PARQUEADERO);

		when(vehiculoContext.validarCupo(EstacionamientoConstants.CUPO_MOTOS_PARQUEADERO)).thenReturn(Boolean.FALSE);

		// act
		try {
			estacionamientoDominio.registrarIngreso(vehiculoDto);
		} catch (EstacionamientoException e) {
			Assert.assertEquals(EstacionamientoConstants.MENSAJE_ERROR_NO_HAY_CUPO, e.getMessage());
			return;
		}

		// assert
		fail();
	}

	@Test
	public void registrarCarroSinCupoTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_SIN_RESTRICCION)
				.conTipo(TipoVehiculoEnum.CARRO).buildDto();

		when(estacionamientoRepositorio.contarVehiculosEstacionados(TipoVehiculoEnum.CARRO))
				.thenReturn(EstacionamientoConstants.CUPO_CARROS_PARQUEADERO);

		when(vehiculoContext.validarCupo(EstacionamientoConstants.CUPO_CARROS_PARQUEADERO)).thenReturn(Boolean.FALSE);

		// act
		try {
			estacionamientoDominio.registrarIngreso(vehiculoDto);
		} catch (EstacionamientoException e) {
			Assert.assertEquals(EstacionamientoConstants.MENSAJE_ERROR_NO_HAY_CUPO, e.getMessage());
			return;
		}

		// assert
		fail();
	}

	@Test
	public void registrarVehiculoConRestriccionTest() {
		// arrange
		VehiculoDto vehiculoDto = VehiculoTestBuilder.defaultValues().conPlaca(PLACA_VEHICULO_CON_RESTRICCION)
				.conCilindraje(CILINDRAJE_MOTO).conTipo(TipoVehiculoEnum.MOTO).buildDto();

		when(calendarioUtil.dayWeekFromDate(any())).thenReturn(DayOfWeek.SUNDAY);

		// act
		try {
			estacionamientoDominio.registrarIngreso(vehiculoDto);
		} catch (EstacionamientoException e) {
			Assert.assertEquals(EstacionamientoConstants.MENSAJE_ERROR_NO_INGRESO_FIN_SEMANA, e.getMessage());
			return;
		}

		// assert
		fail();
	}

}
