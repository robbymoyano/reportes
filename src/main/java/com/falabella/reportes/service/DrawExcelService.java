package com.falabella.reportes.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import com.falabella.reportes.domain.Informe;
import com.falabella.reportes.domain.Totales;

@Service
public class DrawExcelService {
	public void hojaInforme(List<Informe> informe, HSSFWorkbook workbook) {

		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "Transacciones");

		String[] headers = new String[] { "Numero Orden", "Status", "Medio de Pago", "Folio", "Secuencia", "Local",
				"Terminal", "Fecha Bogota" };

		CellStyle headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(header);
		}

		for (int i = 0; i < informe.size(); ++i) {
			HSSFRow dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(informe.get(i).getNum_order());
			dataRow.createCell(1).setCellValue(informe.get(i).getCod_status());
			dataRow.createCell(2).setCellValue(informe.get(i).getCod_pay());
			dataRow.createCell(3).setCellValue(String.valueOf(informe.get(i).getNum_folio()));
			dataRow.createCell(4).setCellValue(informe.get(i).getNum_seq());
			dataRow.createCell(5).setCellValue(informe.get(i).getCod_store());
			dataRow.createCell(6).setCellValue(informe.get(i).getTerminal());
			dataRow.createCell(7).setCellValue(informe.get(i).getDt_date());
		}

	}

	public void hojaTotales(List<Totales> totales, HSSFWorkbook workbook) {

		
		CellStyle headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle styleAmount = workbook.createCellStyle();
		styleAmount.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		
		CellStyle totalStyle = workbook.createCellStyle();
		totalStyle.setFont(font);
		totalStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		totalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		totalStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

		
		
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(1, "Totalizacion");

		String[] headers = new String[] { "Mdp", "Cantidad", "Monto", "Local", "Terminal" };



		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(header);
		}

		

		for (int i = 0; i < totales.size(); ++i) {
			HSSFRow dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(totales.get(i).getCod_pay());
			dataRow.createCell(1).setCellValue(totales.get(i).getNum_cnt());
			dataRow.getCell(1).setCellStyle(styleAmount);
			dataRow.createCell(2).setCellValue(totales.get(i).getQ_amount());
			dataRow.getCell(2).setCellStyle(styleAmount);
			dataRow.createCell(3).setCellValue(totales.get(i).getCod_store());
			dataRow.createCell(4).setCellValue(totales.get(i).getCod_ter());
		}

		HSSFRow dataRow = sheet.createRow(1 + totales.size());
		HSSFCell total = dataRow.createCell(1);
		total.setCellType(CellType.FORMULA);
		total.setCellStyle(totalStyle);
		total.setCellFormula(String.format("SUM(B2:B%d)", 1 + totales.size()));

		HSSFCell total2 = dataRow.createCell(2);
		total2.setCellType(CellType.FORMULA);
		total2.setCellStyle(totalStyle);
		total2.setCellFormula(String.format("SUM(C2:C%d)", 1 + totales.size()));

	}
}
