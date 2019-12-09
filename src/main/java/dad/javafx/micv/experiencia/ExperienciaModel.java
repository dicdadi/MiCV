package dad.javafx.micv.experiencia;

import java.util.ArrayList;

import dad.javafx.micv.clases.Experiencia;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExperienciaModel {
	private ListProperty<Experiencia> experiencia = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));

	public final ListProperty<Experiencia> experienciaProperty() {
		return this.experiencia;
	}
	

	public final ObservableList<Experiencia> getExperiencia() {
		return this.experienciaProperty().get();
	}
	

	public final void setExperiencia(final ObservableList<Experiencia> experiencia) {
		this.experienciaProperty().set(experiencia);
	}
	
}
