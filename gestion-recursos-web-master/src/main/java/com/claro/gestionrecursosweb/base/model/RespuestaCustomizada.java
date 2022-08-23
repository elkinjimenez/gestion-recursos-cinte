package com.claro.gestionrecursosweb.base.model;


public class RespuestaCustomizada<T> extends RespuestaBase {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
