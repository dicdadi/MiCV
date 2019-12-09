package dad.javafx.micv.maincontroller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import dad.javafx.micv.clases.CV;
public class MainModel {
private ObjectProperty<Node> personal= new SimpleObjectProperty<>();
private ObjectProperty<Node> contacto= new SimpleObjectProperty<>();
private ObjectProperty<Node> formacion= new SimpleObjectProperty<>();
private ObjectProperty<Node> experiencia = new SimpleObjectProperty<>();
private ObjectProperty<Node> conocimientos= new SimpleObjectProperty<>();
private ObjectProperty<CV> CV= new SimpleObjectProperty<>();
public final ObjectProperty<Node> personalProperty() {
	return this.personal;
}

public final Node getPersonal() {
	return this.personalProperty().get();
}

public final void setPersonal(final Node personal) {
	this.personalProperty().set(personal);
}

public final ObjectProperty<Node> contactoProperty() {
	return this.contacto;
}

public final Node getContacto() {
	return this.contactoProperty().get();
}

public final void setContacto(final Node contacto) {
	this.contactoProperty().set(contacto);
}

public final ObjectProperty<Node> formacionProperty() {
	return this.formacion;
}

public final Node getFormacion() {
	return this.formacionProperty().get();
}

public final void setFormacion(final Node formacion) {
	this.formacionProperty().set(formacion);
}

public final ObjectProperty<Node> experienciaProperty() {
	return this.experiencia;
}

public final Node getExperiencia() {
	return this.experienciaProperty().get();
}

public final void setExperiencia(final Node experiencia) {
	this.experienciaProperty().set(experiencia);
}

public final ObjectProperty<Node> conocimientosProperty() {
	return this.conocimientos;
}

public final Node getConocimientos() {
	return this.conocimientosProperty().get();
}

public final void setConocimientos(final Node conocimientos) {
	this.conocimientosProperty().set(conocimientos);
}

public final ObjectProperty<CV> CVProperty() {
	return this.CV;
}


public final CV getCV() {
	return this.CVProperty().get();
}


public final void setCV(final CV CV) {
	this.CVProperty().set(CV);
}


}
