package edu.uptc.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import edu.uptc.logic.Route;

public class JSonHandler {

	public static String[] getPaths(String relativePath) {
		
		File folder = new File(relativePath);
		File[] listOfFiles = folder.listFiles();
		
		String[] aux = new String[listOfFiles.length];
		
		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				aux[i] = listOfFiles[i].getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return aux;
	}
	
	public static String[] getFileNames(String relativePath) {
		
		File folder = new File(relativePath);
		File[] listOfFiles = folder.listFiles();
		
		String[] aux = new String[listOfFiles.length];
		
		for (int i = 0; i < listOfFiles.length; i++) {
			aux[i] = listOfFiles[i].getName();
		}
		return aux;
	}
	
	public static ArrayList<Route> readJson(String path) {
		try {
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<Route>>() {}.getType();
			ArrayList<Route> list;
			list = gson.fromJson(Files.newBufferedReader(Paths.get(path)), listType);
//			System.out.println(gson.toJson(list));
//			for (Route route : list) {
//				System.out.println(route);
//			}
			return list;
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
