package model.dao;

import java.util.List;

import model.entities.TipoCliente;

public interface TipoClienteDao {
	public List<TipoCliente> findAll();
	public boolean insert(TipoCliente obj);
	public boolean update(TipoCliente obj);
	public void delete(Integer id);
}
