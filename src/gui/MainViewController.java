package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ClienteService;
import model.services.TipoClienteService;

public class MainViewController implements Initializable{
	@FXML
	private MenuItem clienteCadastrar;
	@FXML
	private MenuItem clienteBuscarInfo;
	@FXML
	private MenuItem clienteMostarTodos;
	@FXML
	private MenuItem About;
	@FXML
	private MenuItem tipoCliente;
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x->{});
	}
	@FXML
	public void onMenuItemCadastrarAction() {
		System.out.println("Cadastrar CLiente");
	}
	@FXML
	public void onMenuItemBuscarInfoAction() {
		System.out.println("Buscar Informações do cliente!!!");
	}
	@FXML
	public void onMenuItemMostrarTodosAction() {
		loadView("/gui/AllClientesView.fxml", (AllClientesViewController controller) -> {
			controller.setCliente(new ClienteService());
			controller.updateTableView();
		});
	}
	@FXML
	public void onMenuItemTipoClienteAction() {
		loadView("/gui/TipoClienteList.fxml", (TipoClienteListController controller)->{
			controller.setTipoCliente(new TipoClienteService());
			controller.updateTableView();
		});
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();	
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
		}catch(IOException e) {
			System.out.println(e.getMessage());
			Alerts.showAlert("IOExeception", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
		}
	}
	
}
