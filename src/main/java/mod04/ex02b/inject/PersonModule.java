package mod04.ex02b.inject;

import mod04.ex02b.dao.IPersonDAO;
import mod04.ex02b.dao.PersonJsonFileDAO;
import mod04.ex02b.dao.PersonTxtFileDAO;
import mod04.ex02b.dao.PersonXmlFileDAO;
import mod04.ex02b.domain.IPersonService;
import mod04.ex02b.domain.PersonService;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPersonService.class).to(PersonService.class);
		bind(String.class).annotatedWith(Names.named("FILENAME")).toInstance("persons");

		bind(IPersonDAO.class).annotatedWith(TxtFormat.class).to(PersonTxtFileDAO.class);
		bind(IPersonDAO.class).annotatedWith(XmlFormat.class).to(PersonXmlFileDAO.class);
		bind(IPersonDAO.class).annotatedWith(JsonFormat.class).to(PersonJsonFileDAO.class);
	}

}
