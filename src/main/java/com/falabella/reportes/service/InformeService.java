package com.falabella.reportes.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falabella.reportes.domain.Informe;
import com.falabella.reportes.domain.Totales;
import com.falabella.reportes.mapper.ConsultasMapper;
import com.falabella.reportes.util.Conversiones;

@Service
public class InformeService {

	@Autowired
	ConsultasMapper mapper;
	
	@Autowired
	DrawExcelService excel;
	

	/**
	 * https://picodotdev.github.io/blog-bitix/2016/05/ejemplo-sencillo-de-como-crear-un-excel-o-csv-en-java-con-apache-poi-y-opencsv/
	 * 
	 * @throws IOException
	 */
	public void informe(String fecha) throws IOException {
		String format = "yyyy-MM-dd HH:mm:ss";
		
		List<Informe> informe = mapper.getInforme(fecha);
		for (Informe i : informe) {
			Calendar horaUTC = Conversiones.stringToDateFormat(i.getDt_date(), format);
			horaUTC.add(Calendar.HOUR_OF_DAY, -5);
			System.out.println(horaUTC);
			String horaBogota= Conversiones.dateToString(horaUTC.getTime(), format);
			i.setDt_date(horaBogota);
			System.out.println(i.toString());
		}

		List<Totales> totales = mapper.getTotales(fecha);
		for (Totales t : totales) {
			//System.out.println(t.toString());
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		excel.hojaInforme(informe, workbook);
		excel.hojaTotales(totales, workbook);

		String nombreArchivo = "Resultado " + fecha + ".xls";
		FileOutputStream file = new FileOutputStream(nombreArchivo);
		workbook.write(file);
		file.close();
	}

	
}
