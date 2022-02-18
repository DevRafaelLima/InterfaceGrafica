package model.entities;

public class Juridica extends Cliente{

	public Juridica() {
	
	}

	public Juridica(int cod, String nome, String telefone, double renda, boolean situacao, Endereco end,
			TipoCliente tp) {
		super(cod, nome, telefone, renda, situacao, end, tp);
	}

		
	
}
