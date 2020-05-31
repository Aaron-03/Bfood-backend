package com.app.util;

import org.json.JSONObject;



public class GobRestUtil {

	
	public static final String urlsunat = "https://api.sunat.cloud/ruc/";
	
	public DatosSunat consultarSunat(String param) {
		DatosSunat r = new DatosSunat();
		String enlace = urlsunat + param;
		NetworkUrl networkUrl = new NetworkUrl();
		
		try {
			r = new DatosSunat();
			String rawJson = networkUrl.request(enlace);
			if (rawJson.length()==0) {
				r=null;
			}
			else {
			JSONObject root = new JSONObject(rawJson);

			r.setRuc(root.getString("ruc"));
			r.setRazon(root.getString("razon_social"));
			r.setCondicion(root.getString("contribuyente_condicion"));
			r.setTipo(root.getString("contribuyente_tipo"));
			r.setEstado(root.getString("contribuyente_estado"));
			r.setNombComercial(root.getString("nombre_comercial"));
			r.setFechInscripcion(root.getString("fecha_inscripcion"));
			r.setDireccion(root.getString("domicilio_fiscal"));
			}

		} catch (Exception e) {
			System.out.println("error" + e.toString());
		}
		return r;
	}
	
}
