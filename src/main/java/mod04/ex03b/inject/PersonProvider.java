package mod04.ex03b.inject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import mod04.ex03b.dao.IPersonDAO;
import mod04.ex03b.dao.PersonJsonFileDAO;
import mod04.ex03b.dao.PersonTxtFileDAO;
import mod04.ex03b.dao.PersonXmlFileDAO;


public class PersonProvider implements Provider<IPersonDAO> {
	private String format;
	private String filename;

	@Inject
	public PersonProvider(@Named("FILENAME") String filename, @Named("FORMAT") String format) {
		this.filename=filename;
		this.format = format;
	}

	@Override
	public IPersonDAO get() {
		switch (format) {
		case "TXT":
			return new PersonTxtFileDAO(filename);
		case "XML":
			return new PersonXmlFileDAO(filename);
		case "JSON":
			return new PersonJsonFileDAO(filename);
		}
		return null;
	}

}
