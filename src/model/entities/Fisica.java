package model.entities;

public class Fisica extends Cliente{
	
	private int rg;
	
	public Fisica() {}

	

	public Fisica(int cod, String nome, String telefone, double renda, boolean situacao, Endereco end, TipoCliente tp,
			int rg) {
		super(cod, nome, telefone, renda, situacao, end, tp);
		this.rg = rg;
	}



	public int getRg() {
		return rg;
	}

	public void setRg(int rg) {
		this.rg = rg;
	}
	
	
	
}
