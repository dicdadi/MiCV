package dad.javafx.micv.experiencia;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Experiencia;
import dad.javafx.micv.clases.Titulo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;

public class ExperianciaDialog extends Dialog<Experiencia> implements Initializable{
	private ButtonType ADD_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	private Experiencia experiencia = new Experiencia();
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
public ExperianciaDialog() {
	super();
	setTitle("Nuevo experiencia");
	getDialogPane().getButtonTypes().addAll(
			ADD_BUTTON_TYPE, 
			ButtonType.CANCEL
		);
	getDialogPane().setContent(loadContent("/fxml/NuevaExperienciaView.fxml"));
	setResultConverter(dialogButton -> {
	    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
	    	Experiencia nuevaExperiencia = new Experiencia();
	    	nuevaExperiencia.setDenominacion(experiencia.getDenominacion());
	    	nuevaExperiencia.setEmpleador(experiencia.getEmpleador());
	    	nuevaExperiencia.setDesde(experiencia.getDesde());
	    	nuevaExperiencia.setHasta(experiencia.getHasta());
	   
	        return nuevaExperiencia;
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
		experiencia.denominacionProperty().bind(denominacionText.textProperty());
		experiencia.empleadorProperty().bind(empleadorText.textProperty());
		experiencia.desdeProperty().bind(desdeText.valueProperty());
		experiencia.hastaProperty().bind(hastaText.valueProperty());
		Node addBoton = getDialogPane().lookupButton(ADD_BUTTON_TYPE);
		addBoton.disableProperty().bind(
				experiencia.denominacionProperty().isEmpty().or(experiencia.empleadorProperty().isEmpty()).or(experiencia.desdeProperty().isNull()).or(experiencia.hastaProperty().isNull())
				);
	}


}
