package dad.javafx.micv.contacto;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Telefono;
import dad.javafx.micv.clases.TipoTelefono;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class TelefonoDialog extends Dialog<Telefono> implements Initializable {
	private ButtonType ADD_BUTTON_TYPE = new ButtonType("Añadir", ButtonData.OK_DONE);
	private Telefono telefono= new Telefono();
	 @FXML
	    private BorderPane vistaTelefono;
	 @FXML
	    private ComboBox<TipoTelefono> comboTipo;

	    @FXML
	    private TextField numeroText;
	
	public TelefonoDialog() {
		super();
		setTitle("Nuevo teléfono");
		setHeaderText("Introduzca el número de teléfono.");
		//setGraphic(new ImageView(getClass().getResource("/images/nueva-persona.png").toString()));
		getDialogPane().getButtonTypes().addAll(
				ADD_BUTTON_TYPE, // botón personalizado
				ButtonType.CANCEL
			);
		//getDialogPane().setContent(loadContent);
		getDialogPane().setContent(loadContent("/fxml/NuevoTelefono.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	Telefono nuevoTelefono = new Telefono();
		    	nuevoTelefono.setNumero(telefono.getNumero());
		    	nuevoTelefono.setTipoTelefono(telefono.getTipoTelefono());
		        return nuevoTelefono;
		    }
		    return null;
		});
	
	}
	private Node loadContent(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			loader.setController(this);
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	telefono.numeroProperty().bind(numeroText.textProperty());
	telefono.tipoTelefonoProperty().bind(comboTipo.valueProperty());
	comboTipo.setPromptText("Seleccione un tipo");
	comboTipo.getItems().addAll(TipoTelefono.values());
	Node addBoton = getDialogPane().lookupButton(ADD_BUTTON_TYPE);
	addBoton.disableProperty().bind(
			telefono.numeroProperty().isEmpty().or(telefono.tipoTelefonoProperty().isNull())
			);
		
	}

}
