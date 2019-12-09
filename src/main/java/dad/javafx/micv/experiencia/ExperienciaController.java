package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Experiencia;
import dad.javafx.micv.clases.Titulo;
import dad.javafx.micv.utils.LocalDateTableCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;


public class ExperienciaController implements Initializable {
		private ExperienciaModel model= new ExperienciaModel();
	 public ExperienciaModel getModel() {
			return model;
		}

		public void setModel(ExperienciaModel model) {
			this.model = model;
		}
	@FXML
	    private BorderPane experienciaView;

	    @FXML
	    private TableView<Experiencia> experienciaTabla;

	    @FXML
	    private TableColumn<Experiencia, LocalDate> desdeColumna;

	    @FXML
	    private TableColumn<Experiencia, LocalDate> hastaColumna;

	    @FXML
	    private TableColumn<Experiencia, String> denominacionColumna;

	    @FXML
	    private TableColumn<Experiencia, String> empleadorColumna;

	    @FXML
	    void onAnniadirExperianciaAction(ActionEvent event) {
	    	ExperianciaDialog dialog = new ExperianciaDialog();
	    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));
	    	Optional<Experiencia> experiencia = dialog.showAndWait();
	    	if (experiencia.isPresent()) {
	    		model.getExperiencia().add(experiencia.get());
	    	}
	    }

	    @FXML
	    void onEliminarExperienciaAction(ActionEvent event) {
	    	Experiencia experienciaSeleccionado = experienciaTabla.getSelectionModel().getSelectedItem();
	    	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Eliminar experiencia");
			alert.setContentText("¿Está seguro de eliminar el registro?");
	    	if(experienciaSeleccionado!=null) {
	    		alert.setHeaderText("Se va a eliminar la siguiente experiencia: " + experienciaSeleccionado.getDenominacion());
	    		Optional<ButtonType> result = alert.showAndWait();
	    		if (result.get() == ButtonType.OK) {
	    		model.getExperiencia().remove(experienciaSeleccionado);
	    		}
	    	}
	    }

public ExperienciaController() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
	loader.setController(this);
	loader.load();
}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		denominacionColumna.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorColumna.setCellValueFactory(v -> v.getValue().empleadorProperty());
		desdeColumna.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumna.setCellValueFactory(v -> v.getValue().hastaProperty());
		experienciaTabla.itemsProperty().bindBidirectional(model.experienciaProperty());	
		experienciaTabla.setEditable(true);
		denominacionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		desdeColumna.setCellFactory(LocalDateTableCell::new);
		hastaColumna.setCellFactory(LocalDateTableCell::new);

	}
	public BorderPane getExperienciaView() {
		return experienciaView;
	}

}
