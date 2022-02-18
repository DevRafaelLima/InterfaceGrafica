package model.entities;

public class Endereco {
	private String cidade;
	private String rua;
	private int num;
	private int cep;
	
	public Endereco() {}
	public Endereco(String c, String rua, int num, int cep) {
		this.cidade = c;
		this.rua = rua;
		this.num = num;
		this.cep = cep;
	}
	
	public void setCidade(String c) {this.cidade = c;}
	public void setRua(String r) {this.rua = r;}
	public void setNum(int n) {this.num = n;}
	public void setCep(int n) {this.cep = n;}
	
	public String getCidade() {return this.cidade;}
	public String getRua() {return this.rua;}
	public int getNum() {return this.num;}
	public int getCep() {return this.cep;}
	@Override
	public String toString() {
		return "Endereco [cidade=" + cidade + ", rua=" + rua + ", num=" + num + ", cep=" + cep + "]";
	}
	
}
