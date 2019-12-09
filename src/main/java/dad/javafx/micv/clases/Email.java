package dad.javafx.micv.clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
@XmlType
public class Email {
private StringProperty direccionEmail=new SimpleStringProperty();
public Email() {
	// TODO Auto-generated constructor stub
}
public Email(String direccionEmail) {
	this.direccionEmail.set(direccionEmail);
}
public final StringProperty direccionEmailProperty() {
	return this.direccionEmail;
}
@XmlAttribute(name="direccion")
public final String getDireccionEmail() {
	return this.direccionEmailProperty().get();
}

public final void setDireccionEmail(final String direccionEmail) {
	this.direccionEmailProperty().set(direccionEmail);
}

}
