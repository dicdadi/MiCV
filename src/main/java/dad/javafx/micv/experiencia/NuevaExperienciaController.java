package dad.javafx.micv.experiencia;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class NuevaExperienciaController implements Initializable {
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

	    @FXML
	    void onCancelarConocimientoAction(ActionEvent event) {

	    }

	    @FXML
	    void onCrearConocimientoAction(ActionEvent event) {

	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	

	}

}
