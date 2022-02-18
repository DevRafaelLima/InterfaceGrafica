package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Cliente;
import model.services.ClienteService;

public class AllClientesViewController implements Initializable{
		private ClienteService cliente;
		@FXML
		private Button btnNovoCliente;
		@FXML
		private TableView<Cliente> tableViewCliente;
		@FXML
		private TableColumn<Cliente, Integer> tableColumnID;
		@FXML
		private TableColumn<Cliente, String> tableColumnName;
		@FXML
		private TableColumn<Cliente, String> tebleColumnTell;
		@FXML
		private TableColumn<Cliente, Double> tableColumnRenda;
		@FXML
		private TableColumn<Cliente, Boolean> tableColumnSituacao;
		
		private ObservableList<Cliente> obsList;
		public void setCliente(ClienteService service) {
			this.cliente = service;
		}
		@FXML
		public void onBtnNovoClienteAction(ActionEvent event) {
	
			Stage parentStage = Utils.currentStage(event);
			Cliente obj = new Cliente();
			createDialogForm(obj, "/gui/ClienteForm.fxml", parentStage);
		}
		@Override
		public void initialize(URL url, ResourceBundle rb) {
			initializeNodes();
		}
		private void initializeNodes() {
			
			tableColumnID.setCellValueFactory(new PropertyValueFactory<>("cod"));
			tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
			tebleColumnTell.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			tableColumnRenda.setCellValueFactory(new PropertyValueFactory<>("renda"));
			tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
			
			
			Stage stage = (Stage) Main.getMainScene().getWindow();
			tableViewCliente.prefHeightProperty().bind(stage.heightProperty());
		}
		public void updateTableView() {
			if(cliente == null) {
				throw new IllegalStateException("Cliente está null");
			}
			
			List<Cliente> list = cliente.findAll();
			obsList = FXCollections.observableArrayList(list);
			tableViewCliente.setItems(obsList);
		}
		
		private void createDialogForm(Cliente obj, String absoluteName, Stage parentStage) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
				Pane pane = loader.load();
//				//pega o controlador da tela que chamou
				ClienteFormController controller = loader.getController();
				controller.setEntity(obj);
				controller.updateFormData();

				Stage dialogStage = new Stage();
				dialogStage.setTitle("Enter Department data");
				dialogStage.setScene(new Scene(pane));
				dialogStage.setResizable(false);
				dialogStage.initOwner(parentStage);
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


}
