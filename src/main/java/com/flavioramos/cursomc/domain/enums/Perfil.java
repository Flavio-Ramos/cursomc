package com.flavioramos.cursomc.domain.enums;

public enum Perfil {

	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public Integer getCod() {
		return this.cod;
	}
	
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(x.getCod().equals(cod)) {
				return x;
			}
		}
		throw new IllegalAccessError("Id inválido " + cod);
	}
}

