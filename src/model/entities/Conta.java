package model.entities;

import java.util.Date;

public class Conta {
	private int cod;
	private Date dataAbertura;
	private Date dataFechamento;
	private boolean situacao;
	private double saldo;
	private String senha;
	
	private Cliente cliente;
	
	public Conta() {}

	public Conta(int cod, Date dataAbertura, Date dataFechamento, boolean situacao, double saldo, String senha,
			Cliente cliente) {
		super();
		this.cod = cod;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.situacao = situacao;
		this.saldo = saldo;
		this.senha = senha;
		this.cliente = cliente;
	}

	public void setCod(int cod) {this.cod = cod;}
	public void setDataAbertura(Date dataAbertura) {this.dataAbertura = dataAbertura;}
	public void setDataFechamento(Date dataFechamento) {this.dataFechamento = dataFechamento;}
	public void setSituacao(boolean situacao) {this.situacao = situacao;}
	public void setSaldo(double saldo) {this.saldo = saldo;}
	public void setSenha(String senha) {this.senha = senha;}
	public void setCliente(Cliente cliente) {this.cliente = cliente;}
	
	public int getCod() {return cod;}
	public Date getDataAbertura() {return dataAbertura;}
	public Date getDataFechamento() {return dataFechamento;}
	public boolean isSituacao() {return situacao;}
	public double getSaldo() {return saldo;}
	public String getSenha() {return senha;}
	public Cliente getCliente() {return cliente;}

	@Override
	public String toString() {
		return "Conta [cod=" + cod + ", dataAbertura=" + dataAbertura + ", dataFechamento=" + dataFechamento
				+ ", situacao=" + situacao + ", saldo=" + saldo + ", senha=" + senha + ", cliente=" + cliente + "]";
	}
	
	
}
