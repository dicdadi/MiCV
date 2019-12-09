package dad.javafx.micv.conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Conocimiento;
import dad.javafx.micv.clases.Nivel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;

public class NuevoConocimientoDialog extends Dialog<Conocimiento> implements Initializable {
	private ButtonType ADD_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);
	private Conocimiento conocimiento = new Conocimiento();
    @FXML
    private BorderPane nuevoConocimientoView;

    @FXML
    private TextField denominacionText;

    @FXML
    private ComboBox<Nivel> comboNivel;

    @FXML
    void onNullComboAction(ActionEvent event) {
    	comboNivel.getItems().clear();
    	comboNivel.getItems().addAll(Nivel.values());
    }
    
    public NuevoConocimientoDialog() {
    	super();
    	setTitle("Nuevo conocimiento");
    	getDialogPane().getButtonTypes().addAll(
    			ADD_BUTTON_TYPE, 
    			ButtonType.CANCEL
    		);
    	getDialogPane().setContent(loadContent("/fxml/NuevoConocimientoView.fxml"));
    	setResultConverter(dialogButton -> {
    	    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
    	    	Conocimiento nuevoConocimiento= new Conocimiento();
    	    	nuevoConocimiento.setDenominacion(conocimiento.getDenominacion());
    	    	nuevoConocimiento.setNivel(conocimiento.getNivel());

    	        return nuevoConocimiento;
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

	conocimiento.denominacionProperty().bind(denominacionText.textProperty());
	conocimiento.nivelProperty().bind(comboNivel.valueProperty());
	comboNivel.getItems().addAll(Nivel.values());
	Node addBoton = getDialogPane().lookupButton(ADD_BUTTON_TYPE);
	addBoton.disableProperty().bind(
			conocimiento.denominacionProperty().isEmpty().or(comboNivel.getSelectionModel().selectedItemProperty().isNull())
			);
		
	}

}
