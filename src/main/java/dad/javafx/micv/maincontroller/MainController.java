package dad.javafx.micv.maincontroller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import dad.javafx.micv.clases.CV;
import dad.javafx.micv.clases.Conocimiento;
import dad.javafx.micv.clases.Contacto;
import dad.javafx.micv.clases.Experiencia;
import dad.javafx.micv.clases.Personal;
import dad.javafx.micv.clases.Titulo;
import dad.javafx.micv.conocimientos.ConocimientosController;
import dad.javafx.micv.contacto.ContactoController;
import dad.javafx.micv.experiencia.ExperienciaController;
import dad.javafx.micv.formacion.FormacionController;
import dad.javafx.micv.personal.PersonalController;
import dad.javafx.micv.utils.JAXBUtil;
import javafx.fxml.Initializable;

public class MainController implements Initializable {
	private MainModel model = new MainModel();
	private ConocimientosController conocimientos = new ConocimientosController();
	private ContactoController contacto = new ContactoController();
	private ExperienciaController experiencia = new ExperienciaController();
	private FormacionController formacion = new FormacionController();
	private PersonalController personal = new PersonalController();
	private File fichero=null;
	@FXML
	private BorderPane viewRoot;

	@FXML
	private MenuItem menuNuevo;

	@FXML
	private MenuItem menuAbrir;

	@FXML
	private MenuItem menuGuardar;

	@FXML
	private MenuItem menuGuardarComo;

	@FXML
	private MenuItem menuSalir;

	@FXML
	private TabPane rootTabPane;

	@FXML
	private Tab personalTab;

	@FXML
	private Tab contactoTab;

	@FXML
	private Tab formacionTab;

	@FXML
	private Tab experienciaTab;

	@FXML
	private Tab conocimientosTab;

	@FXML
	void onMenuAbrirAction(ActionEvent event) throws Exception {
		try {
			Stage stage = new Stage();
			final FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
			fichero = fileChooser.showOpenDialog(stage);
			if (fichero.exists()) {

				model.setCV(JAXBUtil.load(CV.class, fichero));

				Personal personalCV = model.getCV().getPersonal();
				personal.getModel().setPersonal(personalCV);

				Contacto contactoCV = model.getCV().getContacto();
				contacto.getModel().setContacto(contactoCV);

				ObservableList<Titulo> titulocv = model.getCV().getTitulo();
				formacion.getModel().setTitulo(titulocv);

				ObservableList<Experiencia> experienciaCV = model.getCV().getExperiencia();
				experiencia.getModel().setExperiencia(experienciaCV);

				ObservableList<Conocimiento> conocimientoCV = model.getCV().getConocimiento();
				conocimientos.getModel().setConocimientos(conocimientoCV);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	void onMenuGuardarAction(ActionEvent event) {
		try {
			if (fichero != null) {
				CV cv = new CV();
				cv.setPersonal(personal.getModel().getPersonal());
				cv.setContacto(contacto.getModel().getContacto());
				cv.setTitulo(formacion.getModel().getTitulo());
				cv.setExperiencia(experiencia.getModel().getExperiencia());
				cv.setConocimiento(conocimientos.getModel().getConocimientos());
				JAXBUtil.save(cv, fichero);
			} else {
					onMenuGuardarComoAction(null);
				}
			
		} catch (Exception e) { }
	}

	@FXML
	void onMenuGuardarComoAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
			fichero = fileChooser.showSaveDialog(stage);
			if (!fichero.exists()) {
				CV cv = new CV();
				cv.setPersonal(personal.getModel().getPersonal());
				cv.setContacto(contacto.getModel().getContacto());
				cv.setTitulo(formacion.getModel().getTitulo());
				cv.setExperiencia(experiencia.getModel().getExperiencia());
				cv.setConocimiento(conocimientos.getModel().getConocimientos());
				JAXBUtil.save(cv, fichero);
			}
		} catch (Exception e) { }

	}

	@FXML
	void onMenuNuevoAction(ActionEvent event) {
		fichero=null;
		personal.getModel().setPersonal(new Personal());
		personal.cargarPaises();
		contacto.getModel().setContacto(new Contacto());
		ObservableList<Titulo> titulo = null;
		formacion.getModel().setTitulo(titulo);
		ObservableList<Experiencia> experienciaList = null;
		experiencia.getModel().setExperiencia(experienciaList);
		ObservableList<Conocimiento> conocimientoList = null;
		conocimientos.getModel().setConocimientos(conocimientoList);
	}

	@FXML
	void onMenuSalirAction(ActionEvent event) {
		Platform.exit();
	}

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainControllerView.fxml"));
		loader.setController(this);
		loader.load();
		
		model.conocimientosProperty().set(conocimientos.getConocimientosView());
		model.personalProperty().set(personal.getPersonalView());
		model.contactoProperty().set(contacto.getContactoView());
		model.experienciaProperty().set(experiencia.getExperienciaView());
		model.formacionProperty().set(formacion.getFormacionView());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personalTab.contentProperty().bind(model.personalProperty());
		contactoTab.contentProperty().bind(model.contactoProperty());
		formacionTab.contentProperty().bind(model.formacionProperty());
		experienciaTab.contentProperty().bind(model.experienciaProperty());
		conocimientosTab.contentProperty().bind(model.conocimientosProperty());
		menuNuevo.setGraphic(new ImageView("/imagenes/nuevo.gif"));
		menuAbrir.setGraphic(new ImageView("/imagenes/abrir.gif"));
		menuGuardar.setGraphic(new ImageView("/imagenes/guardar.gif"));

	}

	public BorderPane getRootView() {
		return viewRoot;
	}

}
