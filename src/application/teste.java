package application;

import java.util.List;

import model.entities.Cliente;
import model.entities.TipoCliente;
import model.services.ClienteService;
import model.services.TipoClienteService;

public class teste {

	public static void main(String[] args) {
		TipoClienteService tcs = new TipoClienteService();
		List<TipoCliente> list = tcs.findAll();
		
		for(TipoCliente obj:list) {
			System.out.println(obj.toString());
		}
		
		ClienteService cService = new ClienteService();
		List<Cliente> list2 = cService.findAll();
		
		for(Cliente obj : list2) {
			System.out.println(obj.toString());
		}
	}

}
