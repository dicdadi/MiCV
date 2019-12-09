package dad.javafx.micv.clases;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Nacionalidad {
	private StringProperty denominacion=new SimpleStringProperty();

	public Nacionalidad() {
		// TODO Auto-generated constructor stub
	}
public Nacionalidad(String denominacion) {
	
	this.denominacion.set(denominacion);
	
}

public final StringProperty denominacionProperty() {
	return this.denominacion;
}

@XmlAttribute
public final String getDenominacion() {
	return this.denominacionProperty().get();
}


public final void setDenominacion(final String denominacion) {
	this.denominacionProperty().set(denominacion);
}





}
