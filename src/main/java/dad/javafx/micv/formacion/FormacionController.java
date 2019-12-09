package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Titulo;
import dad.javafx.micv.clases.Web;
import dad.javafx.micv.utils.LocalDateTableCell;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FormacionController implements Initializable {
	private FormacionModel model= new FormacionModel();
	public FormacionModel getModel() {
		return model;
	}

	public void setModel(FormacionModel model) {
		this.model = model;
	}

	@FXML
    private BorderPane formacionView;

    @FXML
    private TableView<Titulo> formacionTable;

    @FXML
    private TableColumn<Titulo, LocalDate> desdeColumna;

    @FXML
    private TableColumn<Titulo, LocalDate> hastaColumna;

    @FXML
    private TableColumn<Titulo, String> denominacionColumna;

    @FXML
    private TableColumn<Titulo, String> organizadorColumna;

    @FXML
    void onAnniadirFormacionAction(ActionEvent event) {
    	FormacionDialog dialog= new FormacionDialog();
    	Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/imagenes/cv64x64.png").toString()));
    	Optional<Titulo> titulo = dialog.showAndWait();
    	if (titulo.isPresent()) {
    		model.getTitulo().add(titulo.get());
    	}
    }

    @FXML
    void onEliminarFormacionAction(ActionEvent event) {
    	Titulo tituloSeleccionado = formacionTable.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Eliminar formacion");
		alert.setContentText("¿Está seguro de eliminar el registro?");
    	if(tituloSeleccionado!=null) {
    		alert.setHeaderText("Se va a eliminar el siguiente correo: " + tituloSeleccionado.getDenominacion());
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
    		model.getTitulo().remove(tituloSeleccionado);
    		}
    	}

    }
public FormacionController() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
	loader.setController(this);
	loader.load();
}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.tituloProperty().addListener((o, ol, nv) -> bindeos(ol, nv));
//		model.setTitulo(FXCollections.observableArrayList(new ArrayList<Titulo>()));
		denominacionColumna.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumna.setCellValueFactory(v -> v.getValue().organizadorProperty());
		desdeColumna.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumna.setCellValueFactory(v -> v.getValue().hastaProperty());
		formacionTable.setEditable(true);
		denominacionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		desdeColumna.setCellFactory(LocalDateTableCell::new);
		hastaColumna.setCellFactory(LocalDateTableCell::new);
		

	}
	private void bindeos(ObservableList<Titulo> ol, ObservableList<Titulo> nv) {
		if( ol != null) {
			formacionTable.itemsProperty().unbindBidirectional(model.tituloProperty());
	}
	model.setTitulo(nv);
	Bindings.bindBidirectional(formacionTable.itemsProperty(), model.tituloProperty());
	
	}

	public BorderPane getFormacionView() {
		return formacionView;
	}

}
