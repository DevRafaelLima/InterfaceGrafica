package model.services;

import java.util.List;

import db.DB;
import model.dao.imple.ClienteDaoJDBC;
import model.entities.Cliente;

public class ClienteService {
	public List<Cliente> findAll() {
		ClienteDaoJDBC cliente = new ClienteDaoJDBC(DB.getConnection());
		List<Cliente> listCliente = cliente.findAll();
		return listCliente;
	}
}
