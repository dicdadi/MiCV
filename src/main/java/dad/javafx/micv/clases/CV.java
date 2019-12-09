package dad.javafx.micv.clases;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement
public class CV {
private ObjectProperty<Personal> personal= new SimpleObjectProperty<Personal>();
private ObjectProperty<Contacto> contacto= new SimpleObjectProperty<Contacto>();
private ListProperty<Titulo> titulo = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
private ListProperty<Experiencia> experiencia = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
private ListProperty<Conocimiento> conocimiento = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
public CV() {
	// TODO Auto-generated constructor stub
}
public CV(Personal personal,Contacto contacto,ArrayList<Titulo> titulo,ArrayList<Experiencia> experiencia,ArrayList<Conocimiento> conocimiento) {
this.personal.set(personal);
this.contacto.set(contacto);
this.titulo.addAll(titulo);
this.experiencia.addAll(experiencia);
this.conocimiento.addAll(conocimiento);

}

public final ObjectProperty<Personal> personalProperty() {
	return this.personal;
}

@XmlElement
public final Personal getPersonal() {
	return this.personalProperty().get();
}


public final void setPersonal(final Personal personal) {
	this.personalProperty().set(personal);
}


public final ObjectProperty<Contacto> contactoProperty() {
	return this.contacto;
}

@XmlElement
public final Contacto getContacto() {
	return this.contactoProperty().get();
}


public final void setContacto(final Contacto contacto) {
	this.contactoProperty().set(contacto);
}


public final ListProperty<Titulo> tituloProperty() {
	return this.titulo;
}

@XmlElement(name="formacion")
public final ObservableList<Titulo> getTitulo() {
	return this.tituloProperty().get();
}


public final void setTitulo(final ObservableList<Titulo> titulo) {
	this.tituloProperty().set(titulo);
}


public final ListProperty<Experiencia> experienciaProperty() {
	return this.experiencia;
}

@XmlElement(name= "experiencias")
public final ObservableList<Experiencia> getExperiencia() {
	return this.experienciaProperty().get();
}


public final void setExperiencia(final ObservableList<Experiencia> experiencia) {
	this.experienciaProperty().set(experiencia);
}


public final ListProperty<Conocimiento> conocimientoProperty() {
	return this.conocimiento;
}

@XmlElement(name = "habilidades")
public final ObservableList<Conocimiento> getConocimiento() {
	return this.conocimientoProperty().get();
}


public final void setConocimiento(final ObservableList<Conocimiento> conocimiento) {
	this.conocimientoProperty().set(conocimiento);
}





}
