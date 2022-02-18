package model.entities;

import java.util.Date;

public class Corrente extends Conta{
	
	public Corrente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Corrente(int cod, Date dataAbertura, Date dataFechamento, boolean situacao, double saldo, String senha,
			Cliente cliente) {
		super(cod, dataAbertura, dataFechamento, situacao, saldo, senha, cliente);
		// TODO Auto-generated constructor stub
	}
	public boolean abrir() {
		return false;
	}
	public boolean fechar() {
		return false;
	}
	public boolean validarSenha(String senha) {
		return false;
	}
	public double verificarSaldo() {
		return 0;
	}
	public boolean sacar(double x) {
		return false;
	}
	public boolean depositar(double x) {
		return false;
	}
}
