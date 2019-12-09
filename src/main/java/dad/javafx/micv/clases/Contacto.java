package dad.javafx.micv.clases;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
@XmlType
public class Contacto {

private ListProperty<Telefono> telefono = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
private ListProperty<Email> email = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
private ListProperty<Web> web = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
public Contacto() {

}
public Contacto(ArrayList<Telefono> telefono, ArrayList<Email> email, ArrayList<Web> web) {
	this.telefono.addAll(telefono);
	this.email.addAll(email);
	this.web.addAll(web);
}

public final ListProperty<Telefono> telefonoProperty() {
	return this.telefono;
}

@XmlElement(name = "telefonos")
public final ObservableList<Telefono> getTelefono() {
	return this.telefonoProperty().get();
}


public final void setTelefono(final ObservableList<Telefono> telefono) {
	this.telefonoProperty().set(telefono);
}


public final ListProperty<Email> emailProperty() {
	return this.email;
}

@XmlElement(name = "emails")
public final ObservableList<Email> getEmail() {
	return this.emailProperty().get();
}


public final void setEmail(final ObservableList<Email> email) {
	this.emailProperty().set(email);
}


public final ListProperty<Web> webProperty() {
	return this.web;
}

@XmlElement(name = "webs")
public final ObservableList<Web> getWeb() {
	return this.webProperty().get();
}


public final void setWeb(final ObservableList<Web> web) {
	this.webProperty().set(web);
}



}
