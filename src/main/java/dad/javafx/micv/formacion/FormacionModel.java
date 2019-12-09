package dad.javafx.micv.formacion;


import java.util.ArrayList;

import dad.javafx.micv.clases.Titulo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FormacionModel {

	private ListProperty<Titulo> titulo = new SimpleListProperty<Titulo>(FXCollections.observableArrayList(new ArrayList<Titulo>()));

	public final ListProperty<Titulo> tituloProperty() {
		return this.titulo;
	}
	

	public final ObservableList<Titulo> getTitulo() {
		return this.tituloProperty().get();
	}
	

	public final void setTitulo(final ObservableList<Titulo> titulo) {
		this.tituloProperty().set(titulo);
	}
	

	
	
}
