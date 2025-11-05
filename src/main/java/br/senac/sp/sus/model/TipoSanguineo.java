package br.senac.sp.sus.model;

public enum TipoSanguineo {
	A_POSITIVO("A+", 0.27), A_NEGATIVO("A-", 0.02), B_POSITIVO("B+", 0.23), B_NEGATIVO("B-", 0.01),
	AB_POSITIVO("AB+", 0.06), AB_NEGATIVO("AB-", 0.005), O_POSITIVO("O+", 0.37), O_NEGATIVO("O-", 0.025);

	private String desc;
	private double freq;

	private TipoSanguineo(String desc, double freq) {
		this.desc = desc;
		this.freq = freq;
	}

	@Override
	public String toString() {
		String descricao = String.format("%-5s | %5.2f%%", desc, freq * 100);
		return descricao;
	}
}
