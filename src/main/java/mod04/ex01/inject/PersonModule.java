package mod04.ex01.inject;

import mod04.ex01.dao.IPersonDAO;
import mod04.ex01.dao.PersonFileDAO;
import mod04.ex01.domain.IPersonService;
import mod04.ex01.domain.PersonService;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class PersonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPersonService.class).to(PersonService.class);
		bind(String.class).annotatedWith(Names.named("FILENAME")).toInstance("persons");

		bind(IPersonDAO.class).to(PersonFileDAO.class);
	}

}
