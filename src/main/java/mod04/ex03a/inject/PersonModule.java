package mod04.ex03a.inject;

import javax.inject.Named;

import mod04.ex03a.dao.IPersonDAO;
import mod04.ex03a.dao.PersonJsonFileDAO;
import mod04.ex03a.dao.PersonTxtFileDAO;
import mod04.ex03a.dao.PersonXmlFileDAO;
import mod04.ex03a.domain.IPersonService;
import mod04.ex03a.domain.PersonService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;


public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPersonService.class).to(PersonService.class);
		bind(String.class).annotatedWith(Names.named("FILENAME")).toInstance("persons");
	}

	@Provides
	@TxtFormat
	public IPersonDAO provideTxtFileDAO(@Named("FILENAME") String filename) {
		PersonTxtFileDAO dao = new PersonTxtFileDAO(filename);
		return dao;
	}

	@Provides
	@XmlFormat
	public IPersonDAO provideXmlFileDAO(@Named("FILENAME") String filename) {
		PersonXmlFileDAO dao = new PersonXmlFileDAO(filename);
		return dao;
	}

	@Provides
	@JsonFormat
	public IPersonDAO provideJsonFileDAO(@Named("FILENAME") String filename) {
		PersonJsonFileDAO dao = new PersonJsonFileDAO(filename);
		return dao;
	}
}
