package com.ceiba.induccion.controlador.integracion;

import static org.junit.Assert.fail;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.controlador.TRMController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TRMControllerTest {

	@Autowired
	private TRMController trmController;

	@Test
	public void obtenerActualTest() {
		// arrage

		// act
		Float trmActual = null;
		try {
			trmActual = trmController.obtenerActual();
		} catch (RemoteException e) {
			fail();
		}

		// assert
		Assert.assertNotNull(trmActual);
	}

}
