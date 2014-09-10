package mod04.ex02a.inject;

import mod04.ex02a.dao.IPersonDAO;
import mod04.ex02a.dao.PersonJsonFileDAO;
import mod04.ex02a.dao.PersonTxtFileDAO;
import mod04.ex02a.dao.PersonXmlFileDAO;
import mod04.ex02a.domain.IPersonService;
import mod04.ex02a.domain.PersonService;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPersonService.class).to(PersonService.class);
		bind(String.class).annotatedWith(Names.named("FILENAME")).toInstance("persons");

		bind(IPersonDAO.class).annotatedWith(Names.named("TXT")).to(PersonTxtFileDAO.class);
		bind(IPersonDAO.class).annotatedWith(Names.named("XML")).to(PersonXmlFileDAO.class);
		bind(IPersonDAO.class).annotatedWith(Names.named("JSON")).to(PersonJsonFileDAO.class);
	}

}
