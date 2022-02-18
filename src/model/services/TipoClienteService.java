package model.services;

import java.util.List;

import db.DB;
import model.dao.imple.TipoClienteDaoJDBC;
import model.entities.TipoCliente;

public class TipoClienteService {
	TipoClienteDaoJDBC tc = new TipoClienteDaoJDBC(DB.getConnection());
	public List<TipoCliente> findAll(){
		List<TipoCliente> list = tc.findAll();	
		return list;
	}
	
	public void saveOrUpdate(TipoCliente obj) {
		if(obj.getId() == 0) {
			tc.insert(obj);
		} else {
			tc.update(obj);
		}
	}
	
	public void remove(TipoCliente tipoCliente) {
		tc.delete(tipoCliente.getId());
	}
}
