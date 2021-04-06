package com.flavioramos.cursomc.resources.exeption;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();
	
	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

	public ValidationError(Integer status, String mgs, Long timeStamp) {
		super(status, mgs, timeStamp);
	}

}
