package com.falabella.reportes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Conversiones {
	
	private static final Logger LOG = LoggerFactory.getLogger(Conversiones.class);

	
	/**
	 * 
	 * @param date     Fecha
	 * @param formato  Formato de salida, por ejemplo yyyy-MM-dd HH:mm:ss:SSS
	 * @param timeZone Zona horaria: America/Bogota, America/Santiago
	 * @return Fecha representada por un String
	 */
	public static String getFechabyTimeZone(Date date, String formato, String timeZone) {

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(formato);
			final TimeZone zona = TimeZone.getTimeZone(timeZone);
			sdf.setTimeZone(zona);
			return sdf.format(date);
		} catch (Exception e) {
			LOG.error("Erro al transformar la fecha {}", e.getMessage());
			return "";
		}
	}
	
	/* funcion de formato de fecha */
	public static Calendar stringToDateFormat(String strDate, String format) {
LOG.info("fecha {}, formato{}", strDate, format);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		TimeZone bogota = TimeZone.getTimeZone("America/Bogota");

		
		Date date;
		try {
			date = sdf.parse(strDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.setTimeZone(bogota);
			return cal;
		} catch (ParseException e) {
			LOG.error("{}", e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * Permite convertir un Date a String de acuerdo al formato indicado
	 * 
	 * @param fecha  Date que se desea convetir
	 * @param format String con formato, ejemplo yyyyMMdd
	 * @return fecha formateada a String
	 */
	public static String dateToString(Date fecha, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(fecha);
	}
}
