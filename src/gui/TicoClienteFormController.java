package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.listeners.DataChangeListener;
import gui.utils.Constraints;
import gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.TipoCliente;
import model.services.TipoClienteService;

public class TicoClienteFormController implements Initializable{
	
	private TipoCliente entity;
	
	private TipoClienteService service;
	
	private List<DataChangeListener> dataChangeListeners  = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Button bntSalve;
	
	@FXML
	private Button btnCancel;
	
	//metodo para atualizar a lista logo após a atualização ou criação de um tipo de conta
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	public void setTipoCliente(TipoCliente c) {
		this.entity = c;
	}
	
	public void setTipoClienteService(TipoClienteService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtnSalveAction(ActionEvent event) {
		entity = getFormData();
		service.saveOrUpdate(entity);
		//notificar que tem item para derem adicionados na lista d tipo de conta
		notifyDataChangeListeners();
		Utils.currentStage(event).close();
	}
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}
	private TipoCliente getFormData() {
		TipoCliente tp = new TipoCliente();
		
		tp.setId(Utils.tryParseToInt(txtId.getText()));
		tp.setName(txtName.getText());
		
		return tp;
	}

	@FXML
	public void onBtnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 70);
	}
	
	public void updateFromData() {
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}
	
}
