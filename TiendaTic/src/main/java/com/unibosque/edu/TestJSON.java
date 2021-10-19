package com.unibosque.edu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection; //esta libreria es para realizar la conexion
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestJSON {
	private static URL url;
	private static String sitio = "http://localhost:5000/";

	// agregar informacion a la tabla usuario

	public static ArrayList<Usuarios> parsingUsuarios(String json) throws ParseException {// devulve un arraylist
		JSONParser jsonParser = new JSONParser();
		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator(); // recorrer la tabla usuario
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Usuarios usuario = new Usuarios();
			usuario.setCedula_usuario(Long.parseLong(innerObj.get("cedula_usuario").toString())); // convertir de String // a Long
			usuario.setEmail_usuario(innerObj.get("email_usuario").toString());
			usuario.setNombre_usuario(innerObj.get("nombre_usuario").toString());
			usuario.setPassword(innerObj.get("password").toString());
			usuario.setUsuario(innerObj.get("usuario").toString());
			lista.add(usuario);
		}
		return lista;
	}

	// listar la informacion
	public static ArrayList<Usuarios> getJSON() throws IOException, ParseException { // devolver un listado JSON

		url = new URL(sitio + "usuarios/listar"); // trae el metodo de Usuarios.API
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}

		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		lista = parsingUsuarios(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON(Usuarios usuario) throws IOException {

		url = new URL(sitio + "usuarios/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"cedula_usuario\":\"" + String.valueOf(usuario.getCedula_usuario())
				+ "\",\"email_usuario\": \"" + usuario.getEmail_usuario() + "\",\"nombre_usuario\": \""
				+ usuario.getNombre_usuario() + "\",\"password\":\"" + usuario.getPassword() + "\",\"usuario\":\""
				+ usuario.getUsuario() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON(Usuarios usuario, Long id) throws IOException {

		url = new URL(sitio + "usuarios/actualizar");
		HttpURLConnection http; // una variable de tipo conexion
		http = (HttpURLConnection) url.openConnection(); // abrimos la conexion

		try {
			http.setRequestMethod("PUT"); // el put es estrictamente para actualizar la informacion, viene
											// predeterminada en el Json
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json"); // se realiza una peticion tipo aplicacion json
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" + "\"cedula_usuario\":\"" + id //estos son los datos que lleva desde el front al back
				+ "\",\"email_usuario\": \"" + usuario.getEmail_usuario()
				+ "\",\"nombre_usuario\": \"" + usuario.getNombre_usuario() 
				+ "\",\"password\":\"" 	+ usuario.getPassword() 
				+ "\",\"usuario\":\"" + usuario.getUsuario() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta; //el valor de respuesta correcta es 200
	}

	public static int deleteJSON(Long id) throws IOException {

		url = new URL(sitio + "usuarios/eliminar/" + id); //se concatena la variable del id
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;  //el valor de respuesta correcta es 200
	}

		
	
}
