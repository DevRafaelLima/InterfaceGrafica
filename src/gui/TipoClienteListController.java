package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.utils.Alerts;
import gui.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.TipoCliente;
import model.services.TipoClienteService;

public class TipoClienteListController implements Initializable, DataChangeListener{
	
	private TipoClienteService service;
	
	
	@FXML
	private Button btnNewTC;
	
	@FXML
	private TableView<TipoCliente> tableViewTipoCliente;
	
	@FXML
	private TableColumn<TipoCliente, Integer> tableColumnID;
	
	@FXML
	private TableColumn<TipoCliente, String> tableColumnName;

	@FXML
	private TableColumn<TipoCliente, TipoCliente> tableColumnEDIT;
	
	@FXML
	private TableColumn<TipoCliente, TipoCliente> tableColumnREMOVE;
	
	private ObservableList<TipoCliente> obsList;
	
	public void setTipoCliente(TipoClienteService tcs) {
		this.service = tcs;
	}
	
	@FXML
	public void noBtnNewTCAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		TipoCliente obj = new TipoCliente();
		createDialogForm(obj, "/gui/TicoClienteForm.fxml", parentStage);
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		inicializeNodes();
	}
	private void inicializeNodes() {
		System.out.println("1111");
		tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		System.out.println("1111");
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewTipoCliente.prefHeightProperty().bind(stage.heightProperty());
	}
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Cliente está null");
		}
		
		List<TipoCliente> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewTipoCliente.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}
	
	private void createDialogForm(TipoCliente obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
//			//pega o controlador da tela que chamou
			TicoClienteFormController controller = loader.getController();
			controller.setTipoCliente(obj);
			controller.setTipoClienteService(new TipoClienteService());
			controller.subscribeDataChangeListener(this);
			controller.updateFromData();
			

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com os dados do tipo de cliente");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<TipoCliente, TipoCliente>() {
			private final Button button = new Button("edit");
			
			@Override
			protected void updateItem(TipoCliente obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/TicoClienteForm.fxml",Utils.currentStage(event)));
			}
		});
	}
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<TipoCliente, TipoCliente>() {
		private final Button button = new Button("remove");
		
		@Override
		protected void updateItem(TipoCliente obj, boolean empty) {
		super.updateItem(obj, empty);
		
			if (obj == null) {
				setGraphic(null);
				return;
			}
			setGraphic(button);
			button.setOnAction(event -> removeEntity(obj));
			}
		});
	}
	private void removeEntity(TipoCliente obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Certeza que quer deletar");
		
		if(result.get() == ButtonType.OK) {
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			
			service.remove(obj);
			updateTableView();
			
		}
	}
	
}
