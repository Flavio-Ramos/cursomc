package com.flavioramos.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDto {

	@NotEmpty
	@Email(message = "Email inválido!")
	private String email;

	public EmailDto() {
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
