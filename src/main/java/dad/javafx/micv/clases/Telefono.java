package dad.javafx.micv.clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
@XmlType
public class Telefono {
private StringProperty numero=new SimpleStringProperty();
private ObjectProperty<TipoTelefono> tipoTelefono=new SimpleObjectProperty<TipoTelefono>();
public Telefono() {};
public Telefono(String numero,TipoTelefono tipoTelefono) {
	this.numero.set(numero);
	this.tipoTelefono.set(tipoTelefono);
	
	// TODO Auto-generated constructor stub
}
public final StringProperty numeroProperty() {
	return this.numero;
}
@XmlAttribute
public final String getNumero() {
	return this.numeroProperty().get();
}

public final void setNumero(final String numero) {
	this.numeroProperty().set(numero);
}

public final ObjectProperty<TipoTelefono> tipoTelefonoProperty() {
	return this.tipoTelefono;
}
@XmlAttribute(name= "tipo")
public final TipoTelefono getTipoTelefono() {
	return this.tipoTelefonoProperty().get();
}

public final void setTipoTelefono(final TipoTelefono tipoTelefono) {
	this.tipoTelefonoProperty().set(tipoTelefono);
}

}
