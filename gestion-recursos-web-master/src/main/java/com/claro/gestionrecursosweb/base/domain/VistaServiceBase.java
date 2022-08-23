package com.claro.gestionrecursosweb.base.domain;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class VistaServiceBase{

	private static <T> T map(Class<T> type, Object[] tuple) {
		List<Class<?>> tupleTypes = new ArrayList<>();
		for (Object field : tuple) {
			tupleTypes.add(field.getClass());
		}
		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
			return ctor.newInstance(tuple);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static <T> List<T> map(Class<T> type, List<Object[]> records) {
		List<T> result = new LinkedList<>();
		try {
			for (Object[] record : records) {
				result.add(map(type, record));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*public static <T> List<T> getResultList(Query query, Class<T> type) {
		@SuppressWarnings("unchecked")
		List<Object[]> records = query.getResultList();
		return map(type, records);
	}*/
	
	public static <T> List<T> wrapper(List<Object[]> records, Class<T> type) {
		//@SuppressWarnings("unchecked")
		//List<Object[]> records = query.getResultList();
		return map(type, records);
	}

}
