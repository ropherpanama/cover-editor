package com.ropherpanama.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser {
	private static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

	public static String objetoAJson(Object o) {
		return gson.toJson(o);
	}

	public static Object jsonAObjeto(String str, Object obj) {
		return gson.fromJson(str, obj.getClass());
	}
}
