package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Telefono;
import dad.javafx.micv.clases.Titulo;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class FormacionDialog extends Dialog<Titulo> implements Initializable {
	private ButtonType ADD_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	private Titulo titulo = new Titulo();
	
	@FXML
    private BorderPane nuevaExperienciaView;

    @FXML
    private TextField denominacionText;

    @FXML
    private TextField empleadorText;

    @FXML
    private DatePicker desdeText;

    @FXML
    private DatePicker hastaText;
    public FormacionDialog() {
    	super();
		setTitle("Nuevo teléfono");
		//setGraphic(new ImageView(getClass().getResource("/images/nueva-persona.png").toString()));
		getDialogPane().getButtonTypes().addAll(
				ADD_BUTTON_TYPE, // botón personalizado
				ButtonType.CANCEL
			);
		//getDialogPane().setContent(loadContent);
		getDialogPane().setContent(loadContent("/fxml/NuevoTituloView.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	Titulo nuevoTitulo = new Titulo();
		    	nuevoTitulo.setDenominacion(titulo.getDenominacion());
		    	nuevoTitulo.setOrganizador(titulo.getOrganizador());
		    	nuevoTitulo.setDesde(titulo.getDesde());
		    	nuevoTitulo.setHasta(titulo.getHasta());
		    	
		        return nuevoTitulo;
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
		titulo.denominacionProperty().bind(denominacionText.textProperty());
		titulo.organizadorProperty().bind(empleadorText.textProperty());
		titulo.desdeProperty().bind(desdeText.valueProperty());
		titulo.hastaProperty().bind(hastaText.valueProperty());
		Node addBoton = getDialogPane().lookupButton(ADD_BUTTON_TYPE);
		addBoton.disableProperty().bind(
				titulo.denominacionProperty().isEmpty().or(titulo.organizadorProperty().isEmpty()).or(titulo.desdeProperty().isNull()).or(titulo.hastaProperty().isNull())
				);
	
	}

}
