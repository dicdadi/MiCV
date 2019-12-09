package dad.javafx.micv.conocimientos;

import java.util.ArrayList;

import dad.javafx.micv.clases.Conocimiento;
import dad.javafx.micv.clases.Idioma;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConocimientosModel {
	private ListProperty<Conocimiento> conocimientos = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
	private ListProperty<Idioma> idiomas = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
	
	public final ListProperty<Conocimiento> conocimientosProperty() {
		return this.conocimientos;
	}
	
	public final ObservableList<Conocimiento> getConocimientos() {
		return this.conocimientosProperty().get();
	}
	
	public final void setConocimientos(final ObservableList<Conocimiento> conocimientos) {
		this.conocimientosProperty().set(conocimientos);
	}
	
	public final ListProperty<Idioma> idiomasProperty() {
		return this.idiomas;
	}
	
	public final ObservableList<Idioma> getIdiomas() {
		return this.idiomasProperty().get();
	}
	
	public final void setIdiomas(final ObservableList<Idioma> idiomas) {
		this.idiomasProperty().set(idiomas);
	}
	
}
