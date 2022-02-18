package model.entities;

public class Cliente {
	private int cod;
	private String nome;
	private String telefone;
	private double renda;
	private boolean situacao;
	
	private Endereco end;
	private TipoCliente tipoCliente;
	

	public Cliente() {}

	public Cliente(int cod, String nome, String telefone, double renda, boolean situacao, Endereco end, TipoCliente tp) {
		this.cod = cod;
		this.nome = nome;
		this.telefone = telefone;
		this.renda = renda;
		this.situacao = situacao;
		this.end = end;
		this.tipoCliente = tp;
	}
	
	public void setTipoCliente(TipoCliente tipoCliente) {this.tipoCliente = tipoCliente;}
	public void setCod(int cod) {this.cod = cod;}
	public void setNome(String nome) {this.nome = nome;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public void setRenda(double renda) {this.renda = renda;}
	public void setSituacao(boolean situacao) {this.situacao = situacao;}
	public void setEnd(Endereco end) {this.end = end;}

	
	public TipoCliente getTipoCliente() {return tipoCliente;}
	public int getCod() {return cod;}
	public String getNome() {return nome;}
	public String getTelefone() {return telefone;}
	public double getRenda() {return renda;}
	public boolean isSituacao() {return situacao;}
	public Endereco getEnd() {return end;}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", renda=" + renda + ", situacao=" + situacao
				+ ", end=" + end + "]";
	}
	
	
}
