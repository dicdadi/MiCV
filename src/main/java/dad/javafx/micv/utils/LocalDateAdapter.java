package dad.javafx.micv.utils;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
public LocalDateAdapter() {
	
}
	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	
	}

	@Override
	public String marshal(LocalDate v) throws Exception {

		return v.toString();
	}

}
