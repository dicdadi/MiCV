package dad.javafx.micv.conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Conocimiento;
import dad.javafx.micv.clases.Idioma;
import dad.javafx.micv.clases.Nivel;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;

public class NuevoIdiomaDialog extends Dialog<Idioma> implements Initializable{
	private ButtonType ADD_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	private Idioma idioma= new Idioma();
    @FXML
    private BorderPane nuevoConocimientoView;

    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> comboNivel;

    @FXML
    private TextField certificacionLabel;

    @FXML
    void onNullComboAction(ActionEvent event) {
    	comboNivel.getItems().clear();
    	comboNivel.getItems().addAll(Nivel.values());

    }
    public NuevoIdiomaDialog() {
    	super();
    	setTitle("Nuevo conocimiento");
    	getDialogPane().getButtonTypes().addAll(
    			ADD_BUTTON_TYPE, 
    			ButtonType.CANCEL
    		);
    	getDialogPane().setContent(loadContent("/fxml/NuevoIdiomaView.fxml"));
    	setResultConverter(dialogButton -> {
    	    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
    	    	Idioma nuevoIdioma= new Idioma();
    	    	
    	    	nuevoIdioma.setDenominacion(idioma.getDenominacion());
    	    	nuevoIdioma.setNivel(idioma.getNivel());
    	    	nuevoIdioma.setCertificacion(idioma.getCertificacion());

    	        return nuevoIdioma;
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
		idioma.denominacionProperty().bind(denominacionText.textProperty());
		idioma.nivelProperty().bind(comboNivel.valueProperty());
		idioma.certificacionProperty().bind(certificacionLabel.textProperty());
		comboNivel.getItems().addAll(Nivel.values());
		Node addBoton = getDialogPane().lookupButton(ADD_BUTTON_TYPE);
		addBoton.disableProperty().bind(
				idioma.denominacionProperty().isEmpty().or(comboNivel.getSelectionModel().selectedItemProperty().isNull()).or(idioma.certificacionProperty().isEmpty())
				);
		
	}
	

}
