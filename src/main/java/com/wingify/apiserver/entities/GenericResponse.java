package com.wingify.apiserver.entities;

public class GenericResponse<T> {

	T response;
	String error;

	public GenericResponse(T response) {
		super();
		this.response = response;
	}

	public GenericResponse() {
		super();
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	

}
