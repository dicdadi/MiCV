package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Contacto;
import dad.javafx.micv.clases.Email;
import dad.javafx.micv.clases.Telefono;
import dad.javafx.micv.clases.TipoTelefono;
import dad.javafx.micv.clases.Web;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ContactoController implements Initializable {
	private ContactoModel model= new ContactoModel();
	public ContactoModel getModel() {
		return model;
	}

	public void setModel(ContactoModel model) {
		this.model = model;
	}
	@FXML
    private BorderPane contactoView;

    @FXML
    private TableView<Telefono> telefonoTabla;

    @FXML
    private TableColumn<Telefono, String> numeroColumna;

    @FXML
    private TableColumn<Telefono, TipoTelefono> tipoColumna;

    @FXML
    private TableView<Email> emailTabla;

    @FXML
    private TableColumn<Email, String> emailColumna;

    @FXML
    private TableView<Web> webTabla;

    @FXML
    private TableColumn<Web, String> urlTabla;

    @FXML
    void onAnniadirDireccionAction(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Nuevo e-mail");
    	dialog.setHeaderText("Crear una nueva dirección de correo.");
    	dialog.setContentText("E-mail:");
    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		Email email = new Email(result.get());
    	   model.getContacto().getEmail().add(email);
    	}
    }

    @FXML
    void onAnniadirTelefonoAction(ActionEvent event) {
    	
    	TelefonoDialog dialog = new TelefonoDialog();
    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));

    	
		Optional<Telefono> persona = dialog.showAndWait();
		
		if (persona.isPresent()) {
			model.getContacto().getTelefono().add(persona.get());
			//telefonoTabla.getItems().add(persona.get());
		}
    }

    @FXML
    void onAnniadirWebAction(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog("http://");
    	dialog.setTitle("Nueva web");
    	dialog.setHeaderText("Crear una nueva dirección web.");
    	dialog.setContentText("URL:");
    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		Web web = new Web(result.get());
    	   model.getContacto().getWeb().add(web);
    	}

    }

    @FXML
    void onEliminarDireccionAction(ActionEvent event) {
    	Email emailSeleccionado = emailTabla.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Eliminar direccion ");
		alert.setContentText("¿Está seguro de eliminar el registro?");
    	if(emailSeleccionado!=null) {
    		alert.setHeaderText("Se va a eliminar el siguiente correo: " + emailSeleccionado.getDireccionEmail());
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
    		model.getContacto().getEmail().remove(emailSeleccionado);
    		}
    	}
    }

    @FXML
    void onEliminarNumeroAction(ActionEvent event) {
    	Telefono telefonoSeleccionado = telefonoTabla.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Eliminar telefono ");
		alert.setContentText("¿Está seguro de eliminar el registro?");
    	if(telefonoSeleccionado!=null) {
    		alert.setHeaderText("Se va a eliminar el siguiente telefono: " + telefonoSeleccionado.getNumero());
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
    		model.getContacto().getTelefono().remove(telefonoSeleccionado);
    		}
    	}
    }

    @FXML
    void onEliminarWebAction(ActionEvent event) {
    	Web webSeleccionado = webTabla.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Eliminar email");
		
		alert.setContentText("¿Está seguro de eliminar el registro?");
		
		if(webSeleccionado!=null) {
			alert.setHeaderText("Se va a eliminar la siguiente URL: " + webSeleccionado.getUrl());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			model.getContacto().getWeb().remove( webSeleccionado);
		}
		}
    }
 public ContactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	model.contactoProperty().addListener((o, ol, nv) -> bindeos(ol, nv));
	model.setContacto(new Contacto());
	numeroColumna.setCellValueFactory(v -> v.getValue().numeroProperty());
	tipoColumna.setCellValueFactory(v -> v.getValue().tipoTelefonoProperty());
	emailColumna.setCellValueFactory(v -> v.getValue().direccionEmailProperty());
	urlTabla.setCellValueFactory(v -> v.getValue().urlProperty());
	telefonoTabla.setEditable(true);
	emailTabla.setEditable(true);
	webTabla.setEditable(true);
	numeroColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	tipoColumna.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
	emailColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	urlTabla.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	private void bindeos(Contacto ol, Contacto nv) {

if( ol != null) {
				telefonoTabla.itemsProperty().unbindBidirectional(ol.telefonoProperty());
				emailTabla.itemsProperty().unbindBidirectional(ol.emailProperty());
				webTabla.itemsProperty().unbindBidirectional(ol.webProperty());
		}
		Bindings.bindBidirectional(telefonoTabla.itemsProperty(), nv.telefonoProperty());
		Bindings.bindBidirectional(emailTabla.itemsProperty(), nv.emailProperty());
		Bindings.bindBidirectional(webTabla.itemsProperty(), nv.webProperty());
		
	}

	public BorderPane getContactoView() {
		return contactoView;
	}

}
