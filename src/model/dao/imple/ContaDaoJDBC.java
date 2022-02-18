package model.dao.imple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import model.dao.ContaDao;
import model.entities.Conta;
import model.entities.Corrente;
import model.entities.Endereco;
import model.entities.Especial;
import model.entities.Fisica;
import model.entities.Juridica;
import model.entities.Poupanca;

public class ContaDaoJDBC implements ContaDao {
	private Connection conn = null;
	private ClienteDaoJDBC clienteDao = null;

	public ContaDaoJDBC(Connection conn) {
		this.conn = conn;
		clienteDao = new ClienteDaoJDBC(conn);
	}

	@Override
	public boolean create(Conta obj) {
		PreparedStatement ps = null;
		if (obj.getClass() == Corrente.class) {
			if (obj.getCliente().getClass() == Fisica.class) {
				Fisica cf = (Fisica) obj.getCliente();
				try {
					ps = conn.prepareStatement(
							"INSERT INTO contas (cod, dataAbertura, dataFechamento, situacao, saldo, senha, codCliente, codTipoConta) "
									+ "VALUES (NULL, ?, NULL, ?, ?, ?, ?, ?) ");
					// new Date(x.getAbrirConta().getTime())
					ps.setDate(1, new Date(obj.getDataAbertura().getTime()));
					ps.setBoolean(2, true);
					ps.setDouble(3, obj.getSaldo());
					ps.setString(4, obj.getSenha());
					ps.setLong(5, cf.getCod());
					ps.setInt(6, 1);

					int rowAffects = ps.executeUpdate();
					if (rowAffects > 0) {
						cf.setSituacao(true);
						clienteDao.update(cf);
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DB.cloesePreparedStatement(ps);
				}
			} else {
				// instacia conta para pessoa juricia
				if (obj.getCliente().getClass() == Juridica.class) {
					Juridica cj = (Juridica) obj.getCliente();
					try {
						ps = conn.prepareStatement(
								"INSERT INTO contas (cod, dataAbertura, dataFechamento, situacao, saldo, senha, codCliente, codTipoConta) "
										+ "VALUES (NULL, ?, NULL, ?, ?, ?, ?, ?) ");
						// new Date(x.getAbrirConta().getTime())
						ps.setDate(1, new Date(obj.getDataAbertura().getTime()));
						ps.setBoolean(2, true);
						ps.setDouble(3, obj.getSaldo());
						ps.setString(4, obj.getSenha());
						ps.setLong(5, cj.getCod());
						ps.setInt(6, 1);

						int rowAffects = ps.executeUpdate();
						if (rowAffects > 0) {
							cj.setSituacao(true);
							clienteDao.update(cj);
							return true;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DB.cloesePreparedStatement(ps);
					}
				}
			}
		} else if (obj.getClass() == Especial.class) {
			// veriricar se é um cliente pessoa fisica ou juridica
			if (obj.getCliente().getClass() == Fisica.class) {
				// criar conta para cliente pessoa fisica
				Fisica cf = (Fisica) obj.getCliente();
				try {
					ps = conn.prepareStatement(
							"INSERT INTO contas (cod, dataAbertura, dataFechamento, situacao, saldo, senha, codCliente, codTipoConta) "
									+ "VALUES (NULL, ?, NULL, ?, ?, ?, ?, ?) ");
					// new Date(x.getAbrirConta().getTime())
					ps.setDate(1, new Date(obj.getDataAbertura().getTime()));
					ps.setBoolean(2, true);
					ps.setDouble(3, obj.getSaldo());
					ps.setString(4, obj.getSenha());
					ps.setLong(5, cf.getCod());
					ps.setInt(6, 2);

					int rowAffects = ps.executeUpdate();
					if (rowAffects > 0) {
						cf.setSituacao(true);
						clienteDao.update(cf);
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DB.cloesePreparedStatement(ps);
				}
			} else {
				// criar conta cleitne pessoa juridica
				Juridica cj = (Juridica) obj.getCliente();
				try {
					ps = conn.prepareStatement(
							"INSERT INTO contas (cod, dataAbertura, dataFechamento, situacao, saldo, senha, codCliente, codTipoConta) "
									+ "VALUES (NULL, ?, NULL, ?, ?, ?, ?, ?) ");
					// new Date(x.getAbrirConta().getTime())
					ps.setDate(1, new Date(obj.getDataAbertura().getTime()));
					ps.setBoolean(2, true);
					ps.setDouble(3, obj.getSaldo());
					ps.setString(4, obj.getSenha());
					ps.setLong(5, cj.getCod());
					ps.setInt(6, 2);

					int rowAffects = ps.executeUpdate();
					if (rowAffects > 0) {
						cj.setSituacao(true);
						clienteDao.update(cj);
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DB.cloesePreparedStatement(ps);
				}
			}

		} else if (obj.getClass() == Poupanca.class) {
			if (obj.getCliente().getClass() == Fisica.class) {
				// criar conta como pessoa fisica
				Fisica cf = (Fisica) obj.getCliente();
				try {
					ps = conn.prepareStatement(
							"INSERT INTO contas (cod, dataAbertura, dataFechamento, situacao, saldo, senha, codCliente, codTipoConta) "
									+ "VALUES (NULL, ?, NULL, ?, ?, ?, ?, ?) ");
					// new Date(x.getAbrirConta().getTime())
					ps.setDate(1, new Date(obj.getDataAbertura().getTime()));
					ps.setBoolean(2, true);
					ps.setDouble(3, obj.getSaldo());
					ps.setString(4, obj.getSenha());
					ps.setLong(5, cf.getCod());
					ps.setInt(6, 3);

					int rowAffects = ps.executeUpdate();
					if (rowAffects > 0) {
						cf.setSituacao(true);
						clienteDao.update(cf);
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DB.cloesePreparedStatement(ps);
				}
			} else {
				// cria conta como pesso juridica
				Juridica cj = (Juridica) obj.getCliente();
				try {
					ps = conn.prepareStatement(
							"INSERT INTO contas (cod, dataAbertura, dataFechamento, situacao, saldo, senha, codCliente, codTipoConta) "
									+ "VALUES (NULL, ?, NULL, ?, ?, ?, ?, ?) ");
					// new Date(x.getAbrirConta().getTime())
					ps.setDate(1, new Date(obj.getDataAbertura().getTime()));
					ps.setBoolean(2, true);
					ps.setDouble(3, obj.getSaldo());
					ps.setString(4, obj.getSenha());
					ps.setInt(5, cj.getCod());
					ps.setInt(6, 3);

					int rowAffects = ps.executeUpdate();
					if (rowAffects > 0) {
						cj.setSituacao(true);
						clienteDao.update(cj);
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DB.cloesePreparedStatement(ps);
				}
			}
		}

		return false;
	}

	@Override
	public Conta findByCod(Integer cod) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM contas WHERE contas.cod = ?");
			ps.setInt(1, cod);
			rs = ps.executeQuery();
			if (rs.next()) {
				Conta c = instanceCorrente(rs);
				return c;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.cloesePreparedStatement(ps);
			DB.closeResultSet(rs);
		}
		return null;
	}

	public Conta instanceCorrente(ResultSet rs) throws SQLException {
		Endereco end = new Endereco();
		Conta c = new Conta();
		c.setCod(rs.getInt("cod"));
		c.setDataAbertura(rs.getDate("dataAbertura"));
		c.setDataFechamento(rs.getDate("dataFechamento"));
		c.setSituacao(rs.getBoolean("situacao"));
		c.setSaldo(rs.getDouble("saldo"));
		c.setCliente(clienteDao.findByCod(rs.getInt("codCliente")));
		return c;
	}

	@Override
	public boolean validaSenha(Integer Nconta, String senha) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM contas WHERE contas.cod = ?");
			ps.setInt(1, Nconta);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("senha").equals(senha)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean depositar(Integer Nconta, double valorDeposito) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM contas WHERE contas.cod = ?");
			ps.setInt(1, Nconta);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getBoolean("situacao"));
				if (rs.getBoolean("situacao")) {
					double saldo = rs.getDouble("saldo") + valorDeposito;
					ps = conn.prepareStatement("UPDATE contas SET saldo = ? WHERE contas.cod = ?");
					ps.setDouble(1, saldo);
					ps.setInt(2, Nconta);
					ps.executeUpdate();
					return true;
				} else {
					System.out.println("Não pode depositar a conta está desativiada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean sacar(Integer Nconta, double valorSaque) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM contas WHERE contas.cod = ?");
			ps.setInt(1, Nconta);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					if (rs.getDouble("saldo") >= valorSaque) {
						double saldo = rs.getDouble("saldo") - valorSaque;
						ps = conn.prepareStatement("UPDATE contas SET saldo = ? WHERE contas.cod = ?");
						ps.setDouble(1, saldo);
						ps.setInt(2, Nconta);
						ps.executeUpdate();
						return true;
					} else {
						System.out.println("saldo insuficiente");
					}
				} else {
					System.out.println("Não pode depositar a conta está desativiada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public double verificarSaldo(Integer Nconta) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM contas WHERE contas.cod = ?");
			ps.setInt(1, Nconta);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					double saldo = rs.getDouble("saldo");
					return saldo;
				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Conta correnteMaiorSaldo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT contas.cod, contas.dataAbertura, contas.situacao, contas.codCliente, MAX(contas.saldo) as saldo FROM `contas` WHERE codTipoConta = 1");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					double saldo = rs.getDouble("saldo");
					int codCliente = rs.getInt("codCliente");
					c.setSaldo(saldo);
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;

				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta CorrenteMenorSaldo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT contas.cod, contas.dataAbertura, contas.situacao, contas.codCliente, MIN(contas.saldo) as saldo FROM `contas` WHERE codTipoConta = 1");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					double saldo = rs.getDouble("saldo");
					int codCliente = rs.getInt("codCliente");
					c.setSaldo(saldo);
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;

				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta poupancaMaiorSaldo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT contas.cod, contas.dataAbertura, contas.situacao, contas.codCliente, MAX(contas.saldo) as saldo FROM `contas` WHERE codTipoConta = 3");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					double saldo = rs.getDouble("saldo");
					int codCliente = rs.getInt("codCliente");
					c.setSaldo(saldo);
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;

				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta poupancaMenorSaldo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT contas.cod, contas.dataAbertura, contas.situacao, contas.codCliente, MIN(contas.saldo) as saldo FROM `contas` WHERE codTipoConta = 3");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					double saldo = rs.getDouble("saldo");
					int codCliente = rs.getInt("codCliente");
					c.setSaldo(saldo);
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;

				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta especialMaiorSaldo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT contas.cod, contas.dataAbertura, contas.situacao, contas.codCliente, MAX(contas.saldo) as saldo FROM `contas` WHERE codTipoConta = 2");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					double saldo = rs.getDouble("saldo");
					int codCliente = rs.getInt("codCliente");
					c.setSaldo(saldo);
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;

				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta especialMenorSaldo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT contas.cod, contas.dataAbertura, contas.situacao, contas.codCliente, MIN(contas.saldo) as saldo FROM `contas` WHERE codTipoConta = 2");
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					double saldo = rs.getDouble("saldo");
					int codCliente = rs.getInt("codCliente");
					c.setSaldo(saldo);
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;

				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta MaiorSaldo(Integer codCliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT MAX(saldo) AS saldo, situacao FROM contas WHERE contas.codCliente = ?");
			ps.setInt(1, codCliente);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					c.setSaldo(rs.getDouble("saldo"));
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;
				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Conta MenorSaldo(Integer codCliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT MIN(saldo) AS saldo, situacao FROM contas WHERE contas.codCliente = ?");
			ps.setInt(1, codCliente);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("situacao")) {
					Conta c = new Conta();
					c.setSaldo(rs.getDouble("saldo"));
					c.setCliente(clienteDao.findByCod(codCliente));
					return c;
				} else {
					System.out.println("Conta desativada");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
