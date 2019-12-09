package dad.javafx.micv.conocimientos;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Conocimiento;
import dad.javafx.micv.clases.Idioma;
import dad.javafx.micv.clases.Nivel;
import dad.javafx.micv.clases.Titulo;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConocimientosController implements Initializable {
		private ConocimientosModel model= new ConocimientosModel();
	   public ConocimientosModel getModel() {
			return model;
		}

		public void setModel(ConocimientosModel model) {
			this.model = model;
		}
	@FXML
	    private BorderPane conocimientosView;

	    @FXML
	    private TableView<Conocimiento> tituloTabla;

	    @FXML
	    private TableColumn<Conocimiento, String> denominacionColumna;

	    @FXML
	    private TableColumn<Conocimiento, Nivel> nivelColumna;

	    @FXML
	    void onAnniadirConocimientoAction(ActionEvent event) {
	    	NuevoConocimientoDialog dialog = new NuevoConocimientoDialog();
	    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));
	    	Optional<Conocimiento> conocimiento = dialog.showAndWait();
	    	if (conocimiento.isPresent()) {
	    		model.getConocimientos().add(conocimiento.get());
	    		
	    	}
	    }

	    @FXML
	    void onAnniadirIdiomaAction(ActionEvent event) {
	    	NuevoIdiomaDialog dialog= new NuevoIdiomaDialog();
	    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));
	    	Optional<Idioma> idioma= dialog.showAndWait();
	    	if(idioma.isPresent()) {
	    		
	    		model.getIdiomas().add(idioma.get());
	    	}
	    	
	    }

	    @FXML
	    void onEliminarTituloAction(ActionEvent event) {
	    	
	    	Conocimiento conocimientoSeleccionado =tituloTabla.getSelectionModel().getSelectedItem();
	    	
	    	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Eliminar conocimiento");
			alert.setContentText("¿Está seguro de eliminar el registro?");
	    	if(conocimientoSeleccionado!=null) {
	    		alert.setHeaderText("Se va a eliminar el siguiente conocimiento: " + conocimientoSeleccionado.getDenominacion());
	    		Optional<ButtonType> result = alert.showAndWait();
	    		if (result.get() == ButtonType.OK) {
	    	model.getConocimientos().remove(conocimientoSeleccionado);
	    		}
	    	}
	    }
	 public ConocimientosController() throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
			loader.setController(this);
			loader.load();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		denominacionColumna.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumna.setCellValueFactory(v -> v.getValue().nivelProperty());
		tituloTabla.itemsProperty().bindBidirectional(model.conocimientosProperty());
		tituloTabla.setEditable(true);
		denominacionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumna.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));
	}
	public BorderPane getConocimientosView() {
		return conocimientosView;
	}

}
