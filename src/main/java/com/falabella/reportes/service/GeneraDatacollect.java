package com.falabella.reportes.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falabella.reportes.domain.datacollect.Datacollect;
import com.falabella.reportes.mapper.ConsultasMapper;
import com.falabella.reportes.util.Utilidades;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeneraDatacollect {

	private static final Logger log = LoggerFactory.getLogger(GeneraDatacollect.class);

	@Autowired
	ConsultasMapper mapper;

	public static final int MAXIMO_CARACTERES_DATACOLLECT = 253;

	/**
	 * 
	 * @param fecha formato yyyy-MM-dd
	 * @throws Exception
	 */
	public void generarArchivo(String fecha, String nombreArchivo) throws Exception {
		List<String> listaOrigen = mapper.getLineasByFecha(fecha);
		StringBuilder texto = new StringBuilder();
		int j=1;
		for (String s : listaOrigen) {
			System.out.println(j);
			j++;
			List<Datacollect> lista = deSerializar(s);
			if (lista != null) {

				List<String> data = armarbodyLinea(lista);

				for (int i = 0; i < data.size(); i++) {
					texto.append(data.get(i));
					texto.append("\n");

				}

			}

		}
		Utilidades.generar(texto.toString(), nombreArchivo);
	}

	private void appendDatacollect(List<Datacollect> lista) throws Exception {

		log.info("registros que se grabarán {}", lista.size());

		List<String> data = armarbodyLinea(lista);

		StringBuilder texto = new StringBuilder();
		for (int i = 0; i < data.size(); i++) {
			texto.append(data.get(i));
			texto.append("\n");

		}
		//Utilidades.generar(texto.toString());
	}

	/**
	 * Método que construye una linea para ser impreza en el archivo
	 * 
	 * @param lista Informacion del archivo
	 * @return Lista de String
	 */
	public List<String> armarbodyLinea(List<Datacollect> lista) {
		List<String> list = new ArrayList<>();
		for (Datacollect dc : lista) {
			if (!dc.getBody().equals("") && dc.getBody().substring(0, 1).equals("-"))
				list.add(dc.getHead() + "-"
						+ StringUtils.leftPad(dc.getBody().substring(1), MAXIMO_CARACTERES_DATACOLLECT - 1, "0"));
			else
				list.add(dc.getHead() + StringUtils.leftPad(dc.getBody(), MAXIMO_CARACTERES_DATACOLLECT, "0"));
		}
		return list;
	}

	/**
	 * Método capaz de convertir el mensaje json de la cola en una lista Datacollect
	 * 
	 * @param json
	 * @return List<Datacollect>, null cuando hay error
	 */
	private List<Datacollect> deSerializar(String json) {

		Datacollect[] arrayDatacollect;
		try {
			arrayDatacollect = new ObjectMapper().readValue(json, Datacollect[].class);
			List<Datacollect> listDatacollect = new ArrayList<>();
			for (int i = 0; i < arrayDatacollect.length; i++) {
				listDatacollect.add(arrayDatacollect[i]);
			}
			return listDatacollect;
		} catch (Exception e) {
			log.error("Error al deserializar {}", e.getMessage());
			return null;
		}

	}

}
