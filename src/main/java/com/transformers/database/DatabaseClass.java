package com.transformers.database;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.transformers.model.Transformers;


public class DatabaseClass {

	private static ConcurrentHashMap<Integer,Transformers> transformers = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<Integer, Transformers> getTransformers() {
		return transformers;
	}

	public static void setTransformers(ConcurrentHashMap<Integer, Transformers> transformers) {
		DatabaseClass.transformers = transformers;
	}
	
	
}
