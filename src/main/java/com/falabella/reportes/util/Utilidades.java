package com.falabella.reportes.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Utilidades {
	public static void generar(String contenido, String nombreArchivo) {
		System.out.println("Creando el archivo");
		try {
			String ruta = "C:\\Users\\robby.moyano.toledo\\OneDrive - Accenture\\Falabella\\archivos\\"+nombreArchivo;

			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
