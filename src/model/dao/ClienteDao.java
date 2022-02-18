package model.dao;

import java.util.List;

import model.entities.Cliente;

public interface ClienteDao {
	public boolean insert(Cliente obj);
	public boolean update(Cliente obj);
	public boolean deleteByCod(Integer cod);
	public Cliente findByCod(Integer cod);
	public List<Cliente> findAll();
}
