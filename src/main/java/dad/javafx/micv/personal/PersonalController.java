package dad.javafx.micv.personal;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.clases.Nacionalidad;
import dad.javafx.micv.clases.Personal;
import dad.javafx.micv.utils.Persistencia;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {
	private PersonalModel model= new PersonalModel();
	public PersonalModel getModel() {
		return model;
	}
	private List<String> nacionalidades= Persistencia.cargarNacionalidades();

	@FXML
    private GridPane personalView;

    @FXML
    private TextField dniText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField apellidosText;

    @FXML
    private TextArea direccionArea;

    @FXML
    private TextField codigoPostalText;

    @FXML
    private TextField localidadText;

    @FXML
    private ComboBox<String> comboPais;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private ListView<Nacionalidad> nacionalidadView;

    @FXML
    private Button masNacionalidadBoton;

    @FXML
    private Button menosNacionalidadBoton;

    @FXML
    void onFechaNacimientoAction(ActionEvent event) {

    }

    @FXML
    void onMasNacionalidadAction(ActionEvent event) {
    	ChoiceDialog<String> dialog = new ChoiceDialog<>();
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");
		dialog.getItems().addAll(nacionalidades);
		dialog.setSelectedItem(nacionalidades.get(0));
		
	
		Optional<String> response = dialog.showAndWait();
		
		if( response.isPresent() && response.get() != null ) {
			model.getPersonal().nacionalidadProperty().add(new Nacionalidad(response.get()));
		
		}
    }

    @FXML
    void onMenosNacionalidadAction(ActionEvent event) {
    	Nacionalidad nacionalidadSeleccionada = nacionalidadView.getSelectionModel().getSelectedItem();
    	if(nacionalidadSeleccionada!=null) {
    		model.getPersonal().getNacionalidad().remove(nacionalidadSeleccionada);
    	}

    }
public PersonalController() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
	loader.setController(this);
	loader.load();
}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		
		model.personalProperty().addListener((o, ol, nv) -> bindeos(ol, nv));
		model.setPersonal(new Personal());
		nacionalidadView.setCellFactory(param -> new ListCell<Nacionalidad>() {
			 @Override
	            protected void updateItem(Nacionalidad item, boolean empty) {
	                super.updateItem(item, empty);

	                if (empty || item == null || item.getDenominacion() == null) {
	                    setText(null);
	                } else {
	                    setText(item.getDenominacion());
	                }
	            }
      });
		cargarPaises();

	}
	
	
	//model.personalProperty().addListener((o, ol, nv) -> bindeos(ol, nv));
	private void bindeos(Personal ol, Personal nv) {
		if( ol != null) {
			
			dniText.textProperty().unbindBidirectional(ol.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ol.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ol.apellidosProperty());
			fechaPicker.valueProperty().unbindBidirectional(ol.fechaNacimientoProperty());
			direccionArea.textProperty().unbindBidirectional(ol.direccionProperty());
			codigoPostalText.textProperty().unbindBidirectional(ol.codigoPostalProperty());
			localidadText.textProperty().unbindBidirectional(ol.localidadProperty());
			comboPais.valueProperty().unbindBidirectional(ol.paisProperty());
	
		}
		Bindings.bindBidirectional(dniText.textProperty(), nv.identificacionProperty());
		Bindings.bindBidirectional(nombreText.textProperty(),nv.nombreProperty());
		Bindings.bindBidirectional(apellidosText.textProperty(),nv.apellidosProperty());
		Bindings.bindBidirectional(direccionArea.textProperty(),nv.direccionProperty());
		Bindings.bindBidirectional(codigoPostalText.textProperty(),nv.codigoPostalProperty());
		Bindings.bindBidirectional(localidadText.textProperty(),nv.localidadProperty());
		Bindings.bindBidirectional(comboPais.valueProperty(),nv.paisProperty());
		Bindings.bindBidirectional(fechaPicker.valueProperty(),nv.fechaNacimientoProperty());
		nacionalidadView.itemsProperty().bind(model.getPersonal().nacionalidadProperty());
		
		

	}

	public void cargarPaises() {
		
		try {
			comboPais.getItems().addAll(Persistencia.cargarPaises());
			comboPais.getSelectionModel().selectFirst();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	public GridPane getPersonalView() {
		return personalView;
	}

}
