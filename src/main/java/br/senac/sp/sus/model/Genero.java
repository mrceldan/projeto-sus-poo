package br.senac.sp.sus.model;

public enum Genero {
	MASCULINO ("Masculino"), FEMININO ("Feminino");
	
	private String desc;
	
	private Genero(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {	
		return this.desc;
	}
}
