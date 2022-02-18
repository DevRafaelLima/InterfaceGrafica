package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Cliente;
import model.entities.Fisica;
import model.entities.Juridica;
import model.services.ClienteService;

public class ClienteFormController implements Initializable {
	
	private Cliente entity;
	
	private ClienteService service;
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TextField txtRenda;
	
	@FXML
	private TextField txtRg;
	
	@FXML 
	private TextField txtSituacao;
	
	@FXML
	private TextField txtTipoCliente;
	
	@FXML
	private TextField txtCidade;
	
	@FXML
	private TextField txtRua;
	
	@FXML
	private TextField txtNumero;
	
	@FXML
	private TextField txtCep;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnEditar;

	
	public void setEntity(Cliente cliente) {
		
		if(cliente.getClass() == Fisica.class) {
			System.out.println("Pessoa fisica");

		} else if(cliente.getClass() == Juridica.class){
			this.entity = (Juridica) cliente;
		}
		
		this.entity = cliente;
		
	
	}
	@FXML
	public void onBtnSalvatAction() {
		System.out.println("Salvar");
	}
	public void onBtnEditarAction() {
		System.out.println("Editar");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity está null");
		}
		txtId.setText(String.valueOf(entity.getCod() ));
		txtName.setText(entity.getNome());
		txtTelefone.setText(entity.getTelefone());
		txtRenda.setText(Double.toString(entity.getRenda()));
		txtSituacao.setText(Boolean.toString(entity.isSituacao()));
		if(entity.getClass() == Fisica.class) {
			Fisica pf = (Fisica) entity;
			txtRg.setText(Integer.toString(pf.getCod()));
		}
		if(entity.getClass() == Fisica.class) {
			txtTipoCliente.setText("Cliente Pessoa Fisica"); 
		} else if(entity.getClass() == Juridica.class){
			txtTipoCliente.setText("Cliente Pessoa Jurídica");
		}
//		txtCidade.setText(entity.getEnd().getCidade() == nul);
//		txtRua.setText(entity.getEnd().getRua());
//		txtNumero.setText(Integer.toString(entity.getEnd().getNum()));
//		txtCep.setText(Integer.toString(entity.getEnd().getNum()));
	}
	
	
	
	
}
