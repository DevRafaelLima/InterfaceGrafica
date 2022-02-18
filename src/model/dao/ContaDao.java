package model.dao;
import model.entities.Conta;

public interface ContaDao {
	public boolean create(Conta obj);
	public boolean validaSenha(Integer Nconta, String senha);
	public Conta findByCod(Integer cod);
	public boolean depositar(Integer Nconta, double valorDeposito);
	public boolean sacar(Integer NConta, double valorSaque);
	public double verificarSaldo(Integer Nconta);
	public Conta correnteMaiorSaldo();
	public Conta CorrenteMenorSaldo();
	public Conta poupancaMaiorSaldo();
	public Conta poupancaMenorSaldo();
	public Conta especialMaiorSaldo();
	public Conta especialMenorSaldo();
	public Conta MaiorSaldo(Integer codCliente);
	public Conta MenorSaldo(Integer codCliente);
}
