package com.falabella.reportes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.falabella.reportes.service.GeneraDatacollect;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerarArchivoTest {

	@Autowired
	GeneraDatacollect dc;

	private static final Logger log = LoggerFactory.getLogger(GenerarArchivoTest.class);

	@Test
	public void generar() throws Exception {
		log.info("INIT");
		dc.generarArchivo("2020-11-12", "2020-11-12-DatacollectPRD-Part1");
		log.info("FINISH");

	}
}
