package com.falabella.reportes.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.falabella.reportes.domain.datacollect.Datacollect;
import com.falabella.reportes.mapper.ConsultasMapper;
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
	@Transactional
	public void generarArchivoDiaByCursor(String fecha) throws Exception {

		Iterator<String> iterator = mapper.getLineasByFecha(fecha).iterator();
		int j = 1;
		String nombreArchivo = "dc" + fecha;
		log.info("{}",nombreArchivo);
		String ruta = "C:\\Users\\robby.moyano.toledo\\OneDrive - Accenture\\Falabella\\archivos\\" + nombreArchivo;

		File file = new File(ruta);
		// Si el archivo no existe es creado
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		while (iterator.hasNext()) {

			System.out.println(j);
			j++;
			List<Datacollect> lista = deSerializar(iterator.next());
			if (lista != null) {
				List<String> data = armarbodyLinea(lista);
				for (int i = 0; i < data.size(); i++) {
					try {
						bw.write(data.get(i)+"\n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} // lineas por transacciones
			} //fin if
		}// fin while cursor
		bw.close();
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
