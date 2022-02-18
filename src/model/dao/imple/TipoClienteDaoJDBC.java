package model.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.TipoClienteDao;
import model.entities.Cliente;
import model.entities.Fisica;
import model.entities.TipoCliente;

public class TipoClienteDaoJDBC implements TipoClienteDao{
	private Connection conn = null;
	private ClienteDaoJDBC clienteDao = null;
	
	public TipoClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public List<TipoCliente> findAll() {
		PreparedStatement sp = null;
		ResultSet rs = null;
		try {
			sp = conn.prepareStatement("SELECT * FROM tipoCliente");
			rs = sp.executeQuery();
			
			List<TipoCliente> list = new ArrayList<>();
			
			while(rs.next()) {
					TipoCliente tp = instanceTipoCliente(rs);
					list.add(tp);
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
	public TipoCliente instanceTipoCliente(ResultSet rs) throws SQLException{
		TipoCliente c = new TipoCliente();
		c.setId(rs.getInt("cod"));
		c.setName(rs.getString("nome"));
		
		return c;
	}
	@Override
	public boolean insert(TipoCliente obj) {
		
		PreparedStatement ps = null;
		
			try {
				ps = conn.prepareStatement("INSERT INTO tipoCliente "
						+"(cod, nome) "
						+"VALUES "
						+"(?, ?)");	
				
				
				ps.setInt(1, obj.getId());
				ps.setString(2, obj.getName());
				int rowAffects = ps.executeUpdate();
				
				if(rowAffects>0) {
					return true;
				} else {
					return false;
				}
							
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DB.cloesePreparedStatement(ps);
			}
			return false;
	}
	@Override
	public boolean update(TipoCliente obj) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE tipoCliente SET nome = ? WHERE cod = ?");	
			
			
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			int rowAffects = ps.executeUpdate();
			
			if(rowAffects>0) {
				return true;
			} else {
				return false;
			}
						
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.cloesePreparedStatement(ps);
		}
		return false;
	}
	@Override
	public void delete(Integer id) {
PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM tipoCliente WHERE cod = ?");	
			
			
			ps.setInt(1, id);
			int rowAffects = ps.executeUpdate();
							
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DB.cloesePreparedStatement(ps);
		}
	}
}
