package dad.javafx.micv.personal;

import dad.javafx.micv.clases.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PersonalModel {
	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>();

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}
	

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}
	

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}
	
	
	
	

}
