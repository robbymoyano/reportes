package com.falabella.reportes;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.falabella.reportes.service.InformeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComFalabellaReportesApplicationTests {

	@Autowired
	private InformeService service;
	
	@Test
	public void contextLoads() throws IOException {	
		String fecha = "2020-12-14";
		service.informe(fecha);
		System.out.println("Test finalizado " + fecha);
	}

}
