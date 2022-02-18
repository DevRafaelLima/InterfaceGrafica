package model.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.ClienteDao;
import model.entities.Cliente;
import model.entities.Endereco;
import model.entities.Fisica;
import model.entities.Juridica;

public class ClienteDaoJDBC implements ClienteDao{
	private Connection conn = null;
	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	public void setConn(Connection c) {
		this.conn = c;
	}
	@Override
	public boolean insert(Cliente obj) {
		PreparedStatement ps = null;
		if(obj.getClass() == Fisica.class) {
			Fisica pf = (Fisica) obj;
			try {
				ps = conn.prepareStatement("INSERT INTO clientes "
						+"(cod, rg, nome, telefone, renda, situacao, codTipoCliente, cidade, rua, num, cep) "
						+"VALUES "
						+"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
				
				
				ps.setLong(1, pf.getCod());
				ps.setInt(2, pf.getRg());
				ps.setString(3, pf.getNome());
				ps.setString(4, pf.getTelefone());
				ps.setDouble(5, pf.getRenda());
				ps.setBoolean(6, pf.isSituacao());
				ps.setInt(7, 1);
				ps.setString(8, pf.getEnd().getCidade());
				ps.setString(9, pf.getEnd().getRua());
				ps.setInt(10, pf.getEnd().getNum());
				ps.setInt(11, pf.getEnd().getCep());
				int rowAffects = ps.executeUpdate();
				
				if(rowAffects > 0) {
					return true;
				} else {
					return false;
				}
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DB.cloesePreparedStatement(ps);
			}
		} else {
			Juridica pf = (Juridica) obj;
			try {
				ps = conn.prepareStatement("INSERT INTO clientes "
						+"(cod, nome, telefone, renda, situacao, codTipoCliente, cidade, rua, num, cep) "
						+"VALUES "
						+"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
				
				
				
				ps.setInt(1, pf.getCod());
				ps.setString(2, pf.getNome());
				ps.setString(3, pf.getTelefone());
				ps.setDouble(4, pf.getRenda());
				ps.setBoolean(5, pf.isSituacao());
				ps.setInt(6, 2);
				ps.setString(7, pf.getEnd().getCidade());
				ps.setString(8, pf.getEnd().getRua());
				ps.setInt(9, pf.getEnd().getNum());
				ps.setInt(10, pf.getEnd().getCep());
				int rowAffects = ps.executeUpdate();
				
				if(rowAffects > 0) {
					return true;
				} else {
					return false;
				}
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DB.cloesePreparedStatement(ps);
			}
		}
		return false;
		
	}

	@Override
	public boolean update(Cliente obj) {
		PreparedStatement ps = null;
		if(obj.getClass() == Fisica.class) {
				Fisica pf = (Fisica) obj;
				try {
					ps = conn.prepareStatement("UPDATE clientes SET rg=?, nome=?, telefone=?, renda = ?, situacao=?, cidade=?, rua=?, num=?, cep=? WHERE cod = ?");
		
				ps.setInt(1, pf.getRg());
				ps.setString(2, pf.getNome());
				ps.setString(3, pf.getTelefone());
				ps.setDouble(4, pf.getRenda());
				ps.setBoolean(5, pf.isSituacao());
				ps.setString(6, pf.getEnd().getCidade());
				ps.setString(7, pf.getEnd().getRua());
				ps.setInt(8, pf.getEnd().getNum());
				ps.setInt(9, pf.getEnd().getCep());
				ps.setLong(10, pf.getCod());
				
				int rowAffects = ps.executeUpdate();
				if(rowAffects > 0) {
	
					return true;
				} else {
					return false;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DB.cloesePreparedStatement(ps);
			}
		} else {
			Juridica pj = (Juridica) obj;
			try {
				ps = conn.prepareStatement("UPDATE clientes SET nome=?, telefone=?, renda = ?, situacao=?, cidade=?, rua=?, num=?, cep=? WHERE cod = ?");
				
				ps.setString(1,	pj.getNome());
				ps.setString(2, pj.getTelefone());
				ps.setDouble(3, pj.getRenda());
				ps.setBoolean(4, pj.isSituacao());
				ps.setString(5, pj.getEnd().getCidade());
				ps.setString(6, pj.getEnd().getRua());
				ps.setInt(7, pj.getEnd().getNum());
				ps.setInt(8, pj.getEnd().getCep());
				ps.setInt(9, pj.getCod());
				
				int rowAffects = ps.executeUpdate();
				if(rowAffects > 0) {
					return true;
				}
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			} 
			finally {
				DB.cloesePreparedStatement(ps);
			}
		}
		return false;
		
	}

	@Override
	public boolean deleteByCod(Integer cod) {
		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM clientes WHERE cod = ?");
			ps.setLong(1, cod);
			int rowAffects = ps.executeUpdate();
			if(rowAffects > 0) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.cloesePreparedStatement(ps);
		}
		return false;
	}

	@Override
	public Cliente findByCod(Integer cod) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM clientes WHERE cod = ?");
			ps.setLong(1, cod);
			rs = ps.executeQuery();
			if(rs.next()) {
				int tipoCliente = rs.getInt("codTipoCliente");
				if(tipoCliente == 1) {
					//instancia pessao fisica
					Fisica c = (Fisica) instanceFisica(rs);
					return c;
				} else {
					//instancia pessoa juridica
					Juridica c = (Juridica) instanceJuridica(rs);
					return c;
				}

			} else {
				return null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.cloesePreparedStatement(ps);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Cliente> findAll() {
		PreparedStatement sp = null;
		ResultSet rs = null;
		try {
			sp = conn.prepareStatement("SELECT * FROM clientes");
			rs = sp.executeQuery();
			
			List<Cliente> list = new ArrayList<>();
			
			while(rs.next()) {
				int tipoCliente = rs.getInt("codTipoCliente");
				
				if(tipoCliente == 1) {
					Cliente c = instanceFisica(rs);
					list.add(c);
					
				} else {
					Cliente c = instanceJuridica(rs);
					list.add(c);
				}
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.cloesePreparedStatement(sp);
			DB.closeResultSet(rs);
		}
		return null;
	}
	public Cliente instanceFisica(ResultSet rs) throws SQLException{
		Endereco end = new Endereco();
		Fisica c = new Fisica();
		c.setCod(rs.getInt("cod"));
		c.setRg(rs.getInt("rg"));
		c.setNome(rs.getString("nome"));
		c.setTelefone(rs.getString("telefone"));
		c.setRenda(rs.getDouble("renda"));
		c.setSituacao(rs.getBoolean("situacao"));
		end.setCidade(rs.getString("cidade"));
		end.setRua(rs.getString("rua"));
		end.setNum(rs.getInt("num"));
		end.setCep(rs.getInt("cep"));
		c.setEnd(end);
		return c;
	}
	public Cliente instanceJuridica(ResultSet rs) throws SQLException{
		Endereco end = new Endereco();
		Juridica c = new Juridica();
		c.setCod(rs.getInt("cod"));
		c.setNome(rs.getString("nome"));
		c.setTelefone(rs.getString("telefone"));
		c.setRenda(rs.getDouble("renda"));
		c.setSituacao(rs.getBoolean("situacao"));
		end.setCidade(rs.getString("cidade"));
		end.setRua(rs.getString("rua"));
		end.setNum(rs.getInt("num"));
		end.setCep(rs.getInt("cep"));
		c.setEnd(end);
		return c;
	}

}
