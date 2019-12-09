package dad.javafx.micv.contacto;

import dad.javafx.micv.clases.Contacto;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ContactoModel {
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>();

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}
	

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	
}
